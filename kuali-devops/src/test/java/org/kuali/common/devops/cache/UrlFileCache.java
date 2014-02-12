package org.kuali.common.devops.cache;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Long.parseLong;
import static org.kuali.common.util.Encodings.UTF8;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.kuali.common.http.model.HttpRequestResult;
import org.kuali.common.util.PropertyUtils;

import com.google.common.base.Optional;

public final class UrlFileCache extends PersistentCache<File, HttpRequestResult> {

	private final static String MAGIC_ABSENT_TOKEN = "${optional.absent}";

	private final String encoding = UTF8;

	@Override
	public void persist(File file, HttpRequestResult result) {
		Properties props = new Properties();
		props.setProperty("statusCode", toString(result.getStatusCode()));
		props.setProperty("responseBody", toString(result.getResponseBody()));
		props.setProperty("statusText", result.getStatusText());
		props.setProperty("exception", toString(result.getException()));
		props.setProperty("start", result.getStart() + "");
		props.setProperty("stop", result.getStop() + "");
		props.setProperty("elapsed", result.getElapsed() + "");
		PropertyUtils.storeSilently(props, file);
	}

	@Override
	public HttpRequestResult load(File file) {
		checkArgument(file.exists(), "[%s] does not exist", file);
		Properties props = PropertyUtils.loadOrCreateSilently(file.getAbsolutePath());
		Optional<Integer> statusCode = toOptionalInteger(props.getProperty("statusCode"));
		Optional<String> responseBody = toOptionalString(props.getProperty("responseBody"));
		String statusText = props.getProperty("statusText");
		Optional<IOException> exception = toOptionalIOException(props.getProperty("exception"));
		long start = parseLong(props.getProperty("start"));
		long stop = parseLong(props.getProperty("stop"));
		long elapsed = parseLong(props.getProperty("elapsed"));
		if (exception.isPresent()) {
			return HttpRequestResult.builder(exception.get(), start).stop(stop).elapsed(elapsed).build();
		} else {
			return HttpRequestResult.builder(statusText, statusCode.get(), responseBody, start).stop(stop).elapsed(elapsed).build();
		}
	}

	protected Optional<IOException> toOptionalIOException(String string) {
		if (MAGIC_ABSENT_TOKEN.equals(string)) {
			return absent();
		} else {
			return Optional.of(new IOException(string));
		}
	}

	protected Optional<Long> toOptionalLong(String string) {
		if (MAGIC_ABSENT_TOKEN.equals(string)) {
			return absent();
		} else {
			return Optional.of(Long.parseLong(string));
		}
	}

	protected Optional<Integer> toOptionalInteger(String string) {
		if (MAGIC_ABSENT_TOKEN.equals(string)) {
			return absent();
		} else {
			return Optional.of(Integer.parseInt(string));
		}
	}

	protected Optional<String> toOptionalString(String string) {
		if (MAGIC_ABSENT_TOKEN.equals(string)) {
			return absent();
		} else {
			return Optional.of(string);
		}
	}

	protected String toString(Optional<?> optional) {
		if (optional.isPresent()) {
			return optional.get() + "";
		} else {
			return MAGIC_ABSENT_TOKEN;
		}
	}

}
