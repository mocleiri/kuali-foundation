package org.kuali.common.devops.status;

import static java.lang.String.format;
import static org.apache.commons.lang3.StringUtils.rightPad;
import static org.kuali.common.util.Encodings.UTF8;
import static org.kuali.common.util.FormatUtils.getTime;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.junit.Test;
import org.kuali.common.devops.cache.FileCache;
import org.kuali.common.devops.cache.HttpLoader;
import org.kuali.common.devops.cache.LocationLoader;
import org.kuali.common.http.model.HttpContext;
import org.kuali.common.util.log.Loggers;
import org.slf4j.Logger;

import com.google.common.base.Optional;
import com.google.common.base.Stopwatch;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.ImmutableList;

public class GuavaCacheTest {

	private static final Logger logger = Loggers.make();

	@Test
	public void test() {
		try {
			HttpContext context = HttpContext.builder().quiet(true).asynchronousClose(true).maxBytes("250k").maxRetries(0).overallTimeout("5s").build();
			LoadingCache<String, Optional<String>> cache1 = CacheBuilder.newBuilder().build(HttpLoader.create(context));
			LoadingCache<String, Optional<String>> cache2 = CacheBuilder.newBuilder().build(new LocationLoader(UTF8));
			LoadingCache<String, Optional<String>> cache3 = CacheBuilder.newBuilder().build(FileCache.createUrlCacher());
			// cache(cache1);
			// cache(cache2);
			cache(cache3);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	protected static void cache(LoadingCache<String, Optional<String>> cache) throws ExecutionException {
		List<String> urls = ImmutableList.of("http://www.google.com/", "http://cnn.com", "http://www.microsoft.com", "http://www.yahoo.com/");
		for (String url : urls) {
			Stopwatch stopwatch = Stopwatch.createStarted();
			Optional<String> optional = cache.get(url);
			String content = optional.isPresent() ? optional.get().length() + "" : "absent";
			logger.info(format("%s content: %s - %s", rightPad(url, 25), content, getTime(stopwatch)));
		}
	}

}
