package org.kuali.common.devops.logic;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.StringUtils.startsWith;
import static org.kuali.common.util.base.Assertions.assertNotBlank;
import static org.kuali.common.util.base.Assertions.assertPositive;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.kuali.common.http.model.HttpContext;
import org.kuali.common.http.model.HttpStatus;
import org.kuali.common.http.model.HttpWaitResult;
import org.kuali.common.http.service.DefaultHttpService;
import org.kuali.common.http.service.HttpService;
import org.kuali.common.util.Encodings;
import org.kuali.common.util.base.Exceptions;
import org.kuali.common.util.file.CanonicalFile;

import com.google.common.base.Optional;

public class HttpCacher {

	private static final HttpService SERVICE = new DefaultHttpService();
	private static final File CACHE_DIR = new CanonicalFile("./target/http/cache");
	private static final String PROTOCOL = "http://";

	public static File cache(String url, int maxBytes) {
		assertPositive(maxBytes, "maxBytes");
		assertNotBlank(url, "url");
		return cache(url, Optional.of(maxBytes));
	}

	protected static File cache(String url, Optional<Integer> maxBytes) {
		checkArgument(startsWith(url, "http://"), "[%s] must start with [%s]", url, PROTOCOL);
		File cacheFile = getCacheFile(url);
		Optional<String> content = getContent(url, maxBytes);
		cache(cacheFile, content);
		return cacheFile;
	}

	public static File cache(String url) {
		return cache(url, Optional.<Integer> absent());
	}

	protected static File getCacheFile(String url) {
		String fragment = url.substring(PROTOCOL.length());
		return new CanonicalFile(CACHE_DIR, fragment);
	}

	protected static void cache(File file, Optional<String> data) {
		try {
			if (!data.isPresent()) {
				FileUtils.forceDelete(file);
			} else {
				FileUtils.write(file, data.get(), Encodings.UTF8);
			}
		} catch (IOException e) {
			throw Exceptions.illegalState("unexpected io error -> %s", file);
		}
	}

	protected static Optional<String> getContent(String url, Optional<Integer> maxBytes) {
		HttpContext context = HttpContext.builder(url).overallTimeout("5s").requestTimeout("5s").quiet(true).maxRetries(0).maxResponseBodyBytes(maxBytes).build();
		HttpWaitResult result = SERVICE.wait(context);
		if (result.getStatus().equals(HttpStatus.SUCCESS)) {
			return result.getFinalRequestResult().getResponseBody();
		} else {
			return Optional.absent();
		}
	}

}
