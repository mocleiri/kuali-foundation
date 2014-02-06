package org.kuali.common.devops.logic;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.String.format;
import static org.apache.commons.lang3.StringUtils.startsWith;
import static org.kuali.common.util.base.Assertions.assertNotBlank;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.kuali.common.devops.model.FileCache;
import org.kuali.common.http.model.HttpContext;
import org.kuali.common.http.model.HttpStatus;
import org.kuali.common.http.model.HttpWaitResult;
import org.kuali.common.http.service.DefaultHttpService;
import org.kuali.common.http.service.HttpService;
import org.kuali.common.util.Encodings;
import org.kuali.common.util.base.Exceptions;
import org.kuali.common.util.file.CanonicalFile;
import org.kuali.common.util.log.Loggers;
import org.slf4j.Logger;

import com.google.common.base.Optional;

public class HttpCacher {

	private static final HttpService SERVICE = new DefaultHttpService();
	private static final File CACHE_DIR = new CanonicalFile("./target/http/cache");
	private static final String PROTOCOL = "http://";
	private static final Logger logger = Loggers.make();

	public static FileCache cache(String url) {
		assertNotBlank(url, "url");
		checkArgument(startsWith(url, "http://"), "[%s] must start with [%s]", url, PROTOCOL);
		File cacheFile = getCacheFile(url);
		Optional<String> content = getContent(url);
		cache(cacheFile, content);
		return FileCache.builder().url(url).cache(cacheFile).content(content).build();
	}

	public static File getCacheFile(String url) {
		String fragment = url.substring(PROTOCOL.length());
		return new CanonicalFile(CACHE_DIR, fragment);
	}

	protected static Optional<String> getContent(String url) {
		int maxBytes = 25 * 1024;
		boolean quiet = true;
		HttpContext context = HttpContext.builder(url).asynchronousClose(true).overallTimeout("5s").requestTimeout("5s").quiet(quiet).maxRetries(0).maxResponseBodyBytes(maxBytes)
				.build();
		HttpWaitResult result = SERVICE.wait(context);
		if (result.getStatus().equals(HttpStatus.SUCCESS)) {
			return result.getFinalRequestResult().getResponseBody();
		} else {
			return Optional.absent();
		}
	}

	protected static void cache(File file, Optional<String> data) {
		try {
			if (!data.isPresent()) {
				logger.debug(format("deleting -> [%s]", file));
				if (file.exists()) {
					FileUtils.forceDelete(file);
				}
			} else {
				logger.debug(format("creating -> [%s]", file));
				FileUtils.write(file, data.get(), Encodings.UTF8);
			}
		} catch (IOException e) {
			throw Exceptions.illegalState(e, "unexpected io error -> %s", file);
		}
	}

}
