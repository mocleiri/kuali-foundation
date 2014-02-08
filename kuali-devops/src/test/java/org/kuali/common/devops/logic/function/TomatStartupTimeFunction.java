package org.kuali.common.devops.logic.function;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Preconditions.checkNotNull;
import static org.apache.commons.lang.StringUtils.substringBetween;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.kuali.common.util.base.Exceptions;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Splitter;

public final class TomatStartupTimeFunction implements Function<Optional<String>, Optional<Long>> {

	@Override
	public Optional<Long> apply(Optional<String> content) {
		checkNotNull(content);
		if (content.isPresent()) {
			return getStartupTime(content.get());
		} else {
			return absent();
		}
	}

	/**
	 * time format is -> 2014-01-06T21:23:15.299+0000: 0.957: [GC
	 */
	protected static Optional<Long> getStartupTime(String content) {
		Optional<String> string = getTimestampLine(content);
		if (!string.isPresent()) {
			return absent();
		}
		String s = string.get();
		String token = ": "; // A colon followed by a space signifies the end of the timestamp
		int pos = s.indexOf(token);
		String time = s.substring(0, pos);
		try {
			SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZZZZZ");
			Date date = parser.parse(time);
			return Optional.of(date.getTime());
		} catch (ParseException e) {
			throw Exceptions.illegalState(e, "date parse error -> [%s]", time);
		}
	}

	protected static Optional<String> getTimestampLine(String content) {
		String gc = substringBetween(content, "{", "}");
		List<String> lines = Splitter.on('\n').splitToList(gc);
		for (String line : lines) {
			String trimmed = line.trim();
			if (trimmed.startsWith("201")) { // This will only work for the next 6 years :)
				return Optional.of(trimmed);
			}
		}
		return absent();
	}

}
