package org.kuali.common.devops.logic;

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

	public static File cache(String url) {
		File cacheFile = getCacheFile(url);
		Optional<String> content = getContent(url);
		cache(cacheFile, content);
		return cacheFile;
	}

	public static File getCacheFile(String url) {
		return new CanonicalFile(CACHE_DIR, url);
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

	protected static Optional<String> getContent(String url) {
		HttpContext context = HttpContext.builder(url).overallTimeout("10s").requestTimeout("3s").quiet(true).build();
		HttpWaitResult result = SERVICE.wait(context);
		if (result.getStatus().equals(HttpStatus.SUCCESS)) {
			return result.getFinalRequestResult().getResponseBody();
		} else {
			return Optional.absent();
		}
	}

}
