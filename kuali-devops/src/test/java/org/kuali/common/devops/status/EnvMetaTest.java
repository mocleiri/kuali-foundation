package org.kuali.common.devops.status;

import static org.kuali.common.util.base.Precondition.checkNotBlank;
import static org.kuali.common.util.base.Precondition.checkNotNull;

import org.junit.Test;
import org.kuali.common.devops.cache.PersistToFileSystemLoader;
import org.kuali.common.devops.cache.PersistToFileSystemLoaderFactory;
import org.kuali.common.devops.logic.function.FirstGCTimestampFunction;
import org.kuali.common.devops.logic.function.TomcatVersionFunction;
import org.kuali.common.devops.model.metadata.EnvironmentMetadata;
import org.kuali.common.devops.model.metadata.MetadataUrl;
import org.kuali.common.http.model.HttpContext;
import org.kuali.common.util.log.LoggerUtils;
import org.slf4j.Logger;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.LoadingCache;

public class EnvMetaTest {

	private static final Logger logger = LoggerUtils.make();
	private static final String PREFIX = "http://";
	private static final String VERSION_SUFFIX = "/tomcat";
	private static final String JSP_SUFFIX = "/tomcat/logs/env.jsp";
	private static final String MANIFEST_SUFFIX = "/tomcat/webapps/ROOT/META-INF/MANIFEST.MF";
	private static final String HEAP_LOG_SUFFIX = "/tomcat/logs/heap.log";

	@Test
	public void test() {
		LoadingCache<String, Optional<String>> httpContentCache = getFastFileSystemCacher();
		String fqdn = "env1.rice.kuali.org";
		EnvironmentMetadata meta = build(fqdn, httpContentCache);
	}

	protected static EnvironmentMetadata build(String fqdn, LoadingCache<String, Optional<String>> httpContentCache) {

		MetadataUrlHelper helper = new MetadataUrlHelper(PREFIX, fqdn, httpContentCache);

		EnvironmentMetadata.Builder builder = EnvironmentMetadata.builder();
		builder.tomcatVersion(build(helper, VERSION_SUFFIX, TomcatVersionFunction.create()));
		builder.tomcatStartupTime(build(helper, HEAP_LOG_SUFFIX, new FirstGCTimestampFunction()));
		return builder.build();
	}

	public static <T> MetadataUrl<T> build(MetadataUrlHelper helper, Function<String, Optional<T>> converter) {
		return build(helper, Optional.<String> absent(), converter);
	}

	public static <T> MetadataUrl<T> build(MetadataUrlHelper helper, String suffix, Function<String, Optional<T>> converter) {
		return build(helper, Optional.of(suffix), converter);
	}

	public static <T> MetadataUrl<T> build(MetadataUrlHelper helper, Optional<String> suffix, Function<String, Optional<T>> converter) {
		checkNotNull(helper, "helper");
		checkNotBlank(suffix, "suffix");
		checkNotNull(converter, "converter");
		String url = helper.prefix + helper.fqdn + suffix;
		Optional<String> content = helper.httpContentCache.getUnchecked(url);
		Optional<T> metadata = content.isPresent() ? converter.apply(content.get()) : Optional.<T> absent();
		MetadataUrl.Builder<T> builder = MetadataUrl.builder();
		return builder.url(url).content(content).converter(converter).metadata(metadata).build();
	}

	/**
	 * Grabs the first 25k in content from a url and stashes it onto the local file system. Times out after 5 seconds, no re-tries.
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
			this.prefix = checkNotBlank(fqdn, "prefix");
			this.fqdn = checkNotBlank(fqdn, "fqdn");
			this.httpContentCache = checkNotNull(httpContentCache, "httpContentCache");
		}
	}
}
