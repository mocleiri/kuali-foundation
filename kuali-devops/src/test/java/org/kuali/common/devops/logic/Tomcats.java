package org.kuali.common.devops.logic;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.common.devops.model.Tomcat;
import org.kuali.common.util.Encodings;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.base.Exceptions;
import org.kuali.common.util.log.Loggers;
import org.slf4j.Logger;

import com.google.common.base.Optional;
import com.google.common.base.Splitter;

public class Tomcats extends Examiner {

	private static final Logger logger = Loggers.make();
	private static final SimpleDateFormat PARSER = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZZZZZ");
	public static final String HEAP_FRAGMENT = "/tomcat/logs/heap.log";
	public static final String RELEASE_NOTES_FRAGMENT = "/tomcat/RELEASE-NOTES";

	public static String getHeapUrl(String fqdn) {
		return getUrl(fqdn, HEAP_FRAGMENT);
	}

	public static String getReleaseNotesUrl(String fqdn) {
		return getUrl(fqdn, RELEASE_NOTES_FRAGMENT);
	}

	public static Optional<Tomcat> getTomcat(String fqdn) {
		Optional<String> version = getTomcatVersion(fqdn);
		if (!version.isPresent()) {
			return Optional.absent();
		}
		Optional<Long> startup = getTomcatStartupTime(fqdn, PARSER);
		return Optional.of(Tomcat.create(version.get(), startup));
	}

	/**
	 * time format is -> 2014-01-06T21:23:15.299+0000: 0.957: [GC
	 */
	protected static Optional<Long> getTomcatStartupTime(String fqdn, SimpleDateFormat parser) {
		Optional<String> string = getTomcatStartupString(fqdn);
		if (!string.isPresent()) {
			return Optional.absent();
		}
		String s = string.get();
		String token = ": "; // A colon followed by a space signifies the end of the timestamp
		int pos = s.indexOf(token);
		String time = s.substring(0, pos);
		try {
			Date date = parser.parse(time);
			return Optional.of(date.getTime());
		} catch (ParseException e) {
			throw Exceptions.illegalState(e, "date parse error -> [%s]", time);
		}
	}

	protected static Optional<String> getTomcatStartupString(String fqdn) {
		Optional<String> heap = read(getHeapUrl(fqdn), 4096);
		if (!heap.isPresent()) {
			return Optional.absent();
		}
		String gc = StringUtils.substringBetween(heap.get(), "{", "}");
		List<String> lines = Splitter.on('\n').splitToList(gc);
		for (String line : lines) {
			String trimmed = line.trim();
			if (trimmed.startsWith("201")) { // This will only work for the next 6 years :)
				return Optional.of(trimmed);
			}
		}
		return Optional.absent();
	}

	protected static Optional<String> read(String location, int maxBytes) {
		InputStream in = null;
		try {
			StringBuilder sb = new StringBuilder();
			in = LocationUtils.getInputStream(location);
			byte[] buffer = new byte[4096];
			int len = in.read(buffer);
			while (len != -1) {
				String string = new String(buffer, 0, len, Encodings.UTF8);
				sb.append(string);
				if (sb.length() >= maxBytes) {
					break;
				}
				len = in.read(buffer);
			}
			String result = sb.toString();
			String trimmed = result.trim();
			if (!StringUtils.isBlank(trimmed)) {
				return Optional.of(trimmed);
			} else {
				return Optional.absent();
			}
		} catch (IOException e) {
			logger.debug(String.format("error reading -> [%s]", location));
		} finally {
			IOUtils.closeQuietly(in);
		}
		return Optional.absent();
	}

	protected static Optional<String> getTomcatVersion(String fqdn) {
		List<String> lines = readLines(getReleaseNotesUrl(fqdn));
		if (lines.isEmpty()) {
			return Optional.absent();
		}
		String token = "Apache Tomcat Version";
		for (String line : lines) {
			if (line.contains(token)) {
				int pos = line.indexOf(token) + token.length();
				String version = line.substring(pos);
				return Optional.of(StringUtils.trim(version));
			}
		}
		throw Exceptions.illegalState("could not locate tomcat version -> %s", fqdn);
	}

}
