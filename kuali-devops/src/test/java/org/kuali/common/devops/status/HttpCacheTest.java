package org.kuali.common.devops.status;

import static java.lang.String.format;

import java.util.List;

import org.junit.Test;
import org.kuali.common.devops.cache.HttpLoader;
import org.kuali.common.http.model.HttpContext;
import org.kuali.common.util.log.Loggers;
import org.slf4j.Logger;

import com.google.common.base.Optional;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.ImmutableList;

public class HttpCacheTest {

	private static final Logger logger = Loggers.make();

	@Test
	public void test() {
		try {
			List<String> urls = ImmutableList.of("http://www.yahoo.com/", "http://www.google.com/");
			HttpContext context = HttpContext.builder().quiet(true).asynchronousClose(true).maxBytes(25 * 1024).maxRetries(0).overallTimeout("5s").build();
			LoadingCache<String, Optional<String>> cache = CacheBuilder.newBuilder().build(HttpLoader.create(context));
			for (String url : urls) {
				Optional<String> content = cache.get(url);
				logger.info(format("[%s] content: %s", url, content.isPresent() ? content.get().length() : "absent"));
			}

		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}
