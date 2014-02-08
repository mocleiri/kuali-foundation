package org.kuali.common.devops.status;

import org.junit.Test;
import org.kuali.common.devops.cache.PersistToFileSystemLoader;
import org.kuali.common.devops.cache.PersistToFileSystemLoaderFactory;
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
	private static final String HEAP_SUFFIX = "/tomcat/logs/heap.log";

	@Test
	public void test() {
		LoadingCache<String, Optional<String>> httpContentCache = getCache();
		String fqdn = "env1.rice.kuali.org";
		EnvironmentMetadata.Builder builder = new EnvironmentMetadata.Builder();
		fillIn(builder, fqdn, httpContentCache);
	}

	protected static void fillIn(EnvironmentMetadata.Builder builder, String fqdn, LoadingCache<String, Optional<String>> httpContentCache) {
		TomcatVersionFunction versionConverter = new TomcatVersionFunction();
		String versionUrl = PREFIX + fqdn + VERSION_SUFFIX;
		builder.tomcatVersion(create(versionUrl, httpContentCache, versionConverter));

		TomcatVersionFunction versionConverter = new TomcatVersionFunction();
		String versionUrl = PREFIX + fqdn + VERSION_SUFFIX;
		builder.tomcatVersion(create(versionUrl, httpContentCache, versionConverter));
	}

	protected static <T> MetadataUrl<T> create(String url, LoadingCache<String, Optional<String>> httpContentCache, Function<String, T> converter) {
		return MetadataUrl.create(url, httpContentCache.getUnchecked(url), converter);
	}

	protected static LoadingCache<String, Optional<String>> getCache() {
		HttpContext context = HttpContext.builder().quiet(true).asynchronousClose(true).maxBytes("25k").maxRetries(0).overallTimeout("5s").build();
		PersistToFileSystemLoader<String, String> loader = PersistToFileSystemLoaderFactory.createHttpUrlCacher(context);
		return CacheBuilder.newBuilder().build(loader);
	}

}
