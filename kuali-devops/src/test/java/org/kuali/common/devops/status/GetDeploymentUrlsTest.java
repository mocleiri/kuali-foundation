package org.kuali.common.devops.status;

import org.junit.Test;
import org.kuali.common.devops.cache.PersistToFileSystemLoader;
import org.kuali.common.devops.cache.PersistToFileSystemLoaderFactory;
import org.kuali.common.http.model.HttpContext;
import org.kuali.common.util.log.LoggerUtils;
import org.slf4j.Logger;

import com.google.common.base.Optional;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.LoadingCache;

public class GetDeploymentUrlsTest {

	private static final Logger logger = LoggerUtils.make();

	@Test
	public void test() {
		LoadingCache<String, Optional<String>> httpContentCache = getCache();
		String fqdn = "env1.rice.kuali.org";
		DeployEnvironmentUrls.Builder builder = DeployEnvironmentUrls.b
	}

	protected static LoadingCache<String, Optional<String>> getCache() {
		HttpContext context = HttpContext.builder().quiet(true).asynchronousClose(true).maxBytes("25k").maxRetries(0).overallTimeout("5s").build();
		PersistToFileSystemLoader<String, String> loader = PersistToFileSystemLoaderFactory.createHttpUrlCacher(context);
		return CacheBuilder.newBuilder().build(loader);
	}

}
