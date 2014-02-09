package org.kuali.common.devops.metadata.logic;

import static java.lang.String.format;
import static org.kuali.common.util.base.Precondition.checkNotBlank;
import static org.kuali.common.util.base.Precondition.checkNotNull;

import java.util.List;
import java.util.Properties;

import org.kuali.common.devops.cache.PersistToFileSystemLoader;
import org.kuali.common.devops.cache.PersistToFileSystemLoaderFactory;
import org.kuali.common.devops.metadata.function.FirstGCTimestampFunction;
import org.kuali.common.devops.metadata.function.ManifestFunction;
import org.kuali.common.devops.metadata.function.ProjectConfigUrlFragmentFunction;
import org.kuali.common.devops.metadata.function.ProjectFunction;
import org.kuali.common.devops.metadata.function.ProjectPropertiesUrlFragmentFunction;
import org.kuali.common.devops.metadata.function.RemoteEnvironmentFunction;
import org.kuali.common.devops.metadata.function.RicePropertiesFunction;
import org.kuali.common.devops.metadata.function.TomcatVersionFunction;
import org.kuali.common.devops.metadata.model.EnvironmentMetadata;
import org.kuali.common.devops.metadata.model.MetadataUrl;
import org.kuali.common.devops.metadata.model.RemoteEnvironment;
import org.kuali.common.http.model.HttpContext;
import org.kuali.common.util.log.LoggerUtils;
import org.kuali.common.util.project.model.Project;
import org.slf4j.Logger;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

public class DefaultEnvironmentMetadataService implements EnvironmentMetadataService {

	private static final Logger logger = LoggerUtils.make();
	private static final String PREFIX = "http://";
	private static final String VERSION_SUFFIX = "/tomcat";
	private static final String JSP_SUFFIX = "/tomcat/logs/env.jsp";
	private static final String MANIFEST_SUFFIX = "/tomcat/webapps/ROOT/META-INF/MANIFEST.MF";
	private static final String HEAP_LOG_SUFFIX = "/tomcat/logs/heap.log";
	private final LoadingCache<String, Optional<String>> httpContentCache = getFastFileSystemCacher();

	@Override
	public EnvironmentMetadata getMetadata(String fqdn) {
		return getMetadata(ImmutableList.of(fqdn)).get(0);
	}

	@Override
	public List<EnvironmentMetadata> getMetadata(List<String> fqdns) {
		List<EnvironmentMetadata> list = Lists.newArrayList();
		for (String fqdn : fqdns) {
			logger.debug(format("examining -> [%s]", fqdn));
			EnvironmentMetadata meta = build(fqdn, httpContentCache);
			list.add(meta);
		}
		return list;
	}

	protected EnvironmentMetadata build(String fqdn, LoadingCache<String, Optional<String>> httpContentCache) {
		MetadataUrlHelper helper = new MetadataUrlHelper(PREFIX, fqdn, httpContentCache);
		EnvironmentMetadata.Builder builder = EnvironmentMetadata.builder();
		builder.tomcatVersion(build(helper, VERSION_SUFFIX, TomcatVersionFunction.create()));
		builder.tomcatStartupTime(build(helper, HEAP_LOG_SUFFIX, new FirstGCTimestampFunction()));
		builder.remoteEnvironment(build(helper, JSP_SUFFIX, new RemoteEnvironmentFunction()));
		builder.manifest(build(helper, MANIFEST_SUFFIX, new ManifestFunction()));
		addProject(helper, builder);
		addConfig(helper, builder);
		return builder.build();
	}

	protected void addProject(MetadataUrlHelper helper, EnvironmentMetadata.Builder builder) {
		Optional<Properties> manifest = builder.getManifest().getMetadata();
		if (manifest.isPresent()) {
			Function<Properties, Optional<String>> function = new ProjectPropertiesUrlFragmentFunction();
			Optional<String> suffix = function.apply(manifest.get());
			if (suffix.isPresent()) {
				builder.project(build(helper, suffix.get(), new ProjectFunction()));
			}
		} else {
			builder.projectIsAbsent();
		}
	}

	protected void addConfig(MetadataUrlHelper helper, EnvironmentMetadata.Builder builder) {
		Optional<MetadataUrl<Project>> optionalProjectUrl = builder.getProject();
		if (!optionalProjectUrl.isPresent()) {
			builder.configIsAbsent();
			return;
		}
		Optional<Project> optionalProject = optionalProjectUrl.get().getMetadata();
		if (!optionalProject.isPresent()) {
			builder.configIsAbsent();
			return;
		}
		Optional<RemoteEnvironment> env = builder.getRemoteEnvironment().getMetadata();
		if (optionalProject.isPresent()) {
			Function<Project, Optional<String>> function = new ProjectConfigUrlFragmentFunction(env);
			Optional<String> suffix = function.apply(optionalProject.get());
			if (suffix.isPresent()) {
				builder.config(build(helper, suffix.get(), new RicePropertiesFunction()));
			} else {
				builder.configIsAbsent();
			}
		}
	}

	public static <T> MetadataUrl<T> build(MetadataUrlHelper helper, Function<String, T> converter) {
		return build(helper, Optional.<String> absent(), converter);
	}

	public static <T> MetadataUrl<T> build(MetadataUrlHelper helper, String suffix, Function<String, T> converter) {
		return build(helper, Optional.of(suffix), converter);
	}

	private static <T> MetadataUrl<T> build(MetadataUrlHelper helper, Optional<String> suffix, Function<String, T> converter) {
		checkNotNull(helper, "helper");
		checkNotBlank(suffix, "suffix");
		checkNotNull(converter, "converter");
		String url = helper.prefix + helper.fqdn + (suffix.isPresent() ? suffix.get() : "");
		Optional<String> content = helper.httpContentCache.getUnchecked(url);
		Optional<T> metadata = content.isPresent() ? Optional.of(converter.apply(content.get())) : Optional.<T> absent();
		MetadataUrl.Builder<T> builder = MetadataUrl.builder();
		return builder.url(url).content(content).converter(converter).metadata(metadata).build();
	}

	/**
	 * Grabs the first 25k in content from an http url and stashes it onto the local file system. Times out after 5 seconds, no re-tries.
	 */
	protected LoadingCache<String, Optional<String>> getFastFileSystemCacher() {
		HttpContext context = HttpContext.builder().quiet(true).asynchronousClose(true).maxBytes("25k").maxRetries(0).overallTimeout("5s").build();
		PersistToFileSystemLoader<String, String> loader = PersistToFileSystemLoaderFactory.createHttpUrlCacher(context);
		return CacheBuilder.newBuilder().build(loader);
	}

	private static class MetadataUrlHelper {

		private final String prefix;
		private final String fqdn;
		private final LoadingCache<String, Optional<String>> httpContentCache;

		public MetadataUrlHelper(String prefix, String fqdn, LoadingCache<String, Optional<String>> httpContentCache) {
			this.prefix = checkNotBlank(prefix, "prefix");
			this.fqdn = checkNotBlank(fqdn, "fqdn");
			this.httpContentCache = checkNotNull(httpContentCache, "httpContentCache");
		}
	}
}
