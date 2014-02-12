package org.kuali.common.devops.cache;

import static com.google.common.base.Optional.absent;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.kuali.common.http.model.HttpRequestResult;
import org.kuali.common.util.Encodings;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.spring.format.CsvStringFormatter;

import com.google.common.base.Optional;

public final class UrlFileCache extends PersistentCache<File, HttpRequestResult> {

	private final static String MAGIC_ABSENT_TOKEN = "${optional.absent}";

	private final CsvStringFormatter formatter = CsvStringFormatter.create();
	private final String encoding = Encodings.UTF8;

	@Override
	public void persist(File file, HttpRequestResult result) {
		Properties props = new Properties();
		props.setProperty("statusCode", toString(result.getStatusCode()));
		props.setProperty("responseBody", formatter.print(toString(result.getResponseBody()), null));
		props.setProperty("statusText", result.getStatusText());
		props.setProperty("exception", toString(result.getException()));
		props.setProperty("start", result.getStart() + "");
		props.setProperty("stop", result.getStop() + "");
		props.setProperty("elapsed", result.getElapsed() + "");
		PropertyUtils.store(props, file, encoding);
	}

	@Override
	public HttpRequestResult load(File file) {
		Properties props = PropertyUtils.load(file, encoding);
		Optional<Integer> statusCode = toOptionalInteger(props.getProperty("statusCode"));
		Optional<String> responseBody = toOptionalString(formatter.parse(props.getProperty("responseBody"), null));
		String statusText = props.getProperty("statusText");
		Optional<IOException> exception = toOptionalIOException(props.getProperty("exception"));
		long start = Long.parseLong(props.getProperty("start"));
		long stop = Long.parseLong(props.getProperty("stop"));
		long elapsed = Long.parseLong(props.getProperty("elapsed"));
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
