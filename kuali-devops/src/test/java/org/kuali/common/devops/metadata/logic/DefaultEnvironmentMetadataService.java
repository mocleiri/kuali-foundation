package org.kuali.common.devops.metadata.logic;

import static com.google.common.base.Optional.absent;
import static com.google.common.collect.Lists.newArrayList;
import static java.lang.String.format;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.kuali.common.http.model.HttpStatus.SUCCESS;
import static org.kuali.common.util.base.Precondition.checkNotBlank;
import static org.kuali.common.util.base.Precondition.checkNotNull;

import java.util.List;
import java.util.Properties;

import org.kuali.common.devops.cache.Caches;
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
import org.kuali.common.http.model.HttpRequestResult;
import org.kuali.common.http.model.HttpWaitResult;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.log.LoggerUtils;
import org.kuali.common.util.project.model.Project;
import org.slf4j.Logger;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Stopwatch;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.ImmutableList;

public class DefaultEnvironmentMetadataService implements EnvironmentMetadataService {

	private static final Logger logger = LoggerUtils.make();
	private static final String PREFIX = "http://";
	private static final String VERSION_SUFFIX = "/tomcat";
	private static final String JSP_SUFFIX = "/tomcat/logs/env.jsp";
	private static final String MANIFEST_SUFFIX = "/tomcat/webapps/ROOT/META-INF/MANIFEST.MF";
	private static final String HEAP_LOG_SUFFIX = "/tomcat/logs/heap.log";
	private final LoadingCache<String, HttpWaitResult> urlCache = getFastFileSystemCacher();

	@Override
	public EnvironmentMetadata getMetadata(String fqdn) {
		return getMetadata(ImmutableList.of(fqdn)).get(0);
	}

	@Override
	public List<EnvironmentMetadata> getMetadata(List<String> fqdns) {
		List<EnvironmentMetadata> list = newArrayList();
		for (String fqdn : fqdns) {
			logger.debug(format("examining -> [%s]", fqdn));
			EnvironmentMetadata meta = build(fqdn, urlCache);
			list.add(meta);
		}
		return list;
	}

	protected EnvironmentMetadata build(String fqdn, LoadingCache<String, HttpWaitResult> urlCache) {
		MetadataUrlHelper helper = new MetadataUrlHelper(PREFIX, fqdn, urlCache);
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
			Function<Project, Optional<String>> function1 = new ProjectConfigUrlFragmentFunction(env);
			Function<Project, Optional<String>> function2 = new ProjectConfigUrlFragmentFunction(env, Optional.of("tomcat"));
			Optional<String> suffix1 = function1.apply(optionalProject.get());
			Optional<String> suffix2 = function2.apply(optionalProject.get());
			if (suffix1.isPresent() || suffix2.isPresent()) {
				builder.config(build(helper, suffix1, suffix2, new RicePropertiesFunction()));
			} else {
				builder.configIsAbsent();
			}
		}
	}

	public static <T> MetadataUrl<T> build(MetadataUrlHelper helper, Function<String, T> converter) {
		return build(helper, Optional.<String> absent(), Optional.<String> absent(), converter);
	}

	public static <T> MetadataUrl<T> build(MetadataUrlHelper helper, String suffix1, Function<String, T> converter) {
		return build(helper, Optional.of(suffix1), Optional.<String> absent(), converter);
	}

	private static <T> MetadataUrl<T> build(MetadataUrlHelper helper, Optional<String> suffix1, Optional<String> suffix2, Function<String, T> converter) {
		checkNotNull(helper, "helper");
		checkNotBlank(suffix1, "suffix1");
		checkNotBlank(suffix2, "suffix2");
		checkNotNull(converter, "converter");
		String url = helper.prefix + helper.fqdn + (suffix1.isPresent() ? suffix1.get() : "");
		if (url.equals("http://dev.docstore.ole.kuali.org/home/kuali/main/dev/common-config.xml")) {
			System.out.println("yo");
		}
		Stopwatch sw = Stopwatch.createStarted();
		HttpWaitResult result = helper.urlCache.getUnchecked(url);
		logger.info(String.format("[%s] - %s", url, FormatUtils.getTime(sw)));
		Optional<String> content = getContent(result);
		if (!content.isPresent()) {
			sw = Stopwatch.createStarted();
			url = helper.prefix + helper.fqdn + (suffix2.isPresent() ? suffix2.get() : "");
			logger.info(String.format("[%s] - %s", url, FormatUtils.getTime(sw)));
			result = helper.urlCache.getUnchecked(url);
			content = getContent(result);
		}
		Optional<T> metadata = content.isPresent() ? Optional.of(converter.apply(content.get())) : Optional.<T> absent();
		MetadataUrl.Builder<T> builder = MetadataUrl.builder();
		return builder.url(url).content(content).converter(converter).metadata(metadata).build();
	}

	private static Optional<String> getContent(HttpWaitResult result) {
		if (!SUCCESS.equals(result.getStatus())) {
			return absent();
		}
		HttpRequestResult request = result.getFinalRequestResult();
		Optional<String> responseBody = request.getResponseBody();
		if (!responseBody.isPresent() || isBlank(responseBody.get())) {
			return absent();
		} else {
			return responseBody;
		}
	}

	/**
	 * Grabs the first 25k in content from a URL and stashes it onto the local file system. Times out after 5 seconds, no re-tries.
	 */
	protected LoadingCache<String, HttpWaitResult> getFastFileSystemCacher() {
		HttpContext context = HttpContext.builder().quiet(true).maxBytes("25k").maxRetries(0).requestTimeout("5s").overallTimeout("5s").build();
		CacheLoader<String, HttpWaitResult> loader = Caches.buildUrlCache(context);
		return CacheBuilder.newBuilder().build(loader);
	}

	private static class MetadataUrlHelper {

		private final String prefix;
		private final String fqdn;
		private final LoadingCache<String, HttpWaitResult> urlCache;

		public MetadataUrlHelper(String prefix, String fqdn, LoadingCache<String, HttpWaitResult> urlCache) {
			this.prefix = checkNotBlank(prefix, "prefix");
			this.fqdn = checkNotBlank(fqdn, "fqdn");
			this.urlCache = checkNotNull(urlCache, "urlCache");
		}
	}
}
