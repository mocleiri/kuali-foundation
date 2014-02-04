package org.kuali.common.devops.logic;

import static java.lang.String.format;

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
import org.kuali.common.util.log.Loggers;
import org.slf4j.Logger;

import com.google.common.base.Optional;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;

public class Fqdns {

	private static final String PROTOCOL = "http://";
	private static final Logger logger = Loggers.make();
	private static final String NOT_AVAILABLE = "na";
	private static final SimpleDateFormat PARSER = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZZZZZ");

	public static String getJavaVersion(String fqdn) {
		return getSystemProperty(fqdn, "java.version");
	}

	public static String getSystemProperty(String fqdn, String property) {
		String fragment = "/tomcat/logs/env.jsp";
		String location = PROTOCOL + fqdn + fragment;
		List<String> lines = readLines(location);
		if (lines.isEmpty()) {
			return NOT_AVAILABLE;
		}
		for (String line : lines) {
			String token = "<td>" + property + "</td>";
			if (line.contains(token)) {
				int pos = line.indexOf(token) + token.length();
				String substring = line.substring(pos);
				String value = StringUtils.substringBetween(substring, "<td>", "</td>");
				return StringUtils.trim(value);
			}
		}
		throw Exceptions.illegalState("unable to locate system property -> [%s]", property);
	}

	protected static List<String> readLines(String location) {
		try {
			return LocationUtils.readLines(location);
		} catch (Exception e) {
			logger.debug(format("unexpected error reading from [%s]", location));
			return ImmutableList.of();
		}
	}

	protected static Tomcat getTomcat(String fqdn) {
		String version = getTomcatVersion(fqdn);
		long startup = getTomcatStartupTime(fqdn, PARSER);
		return Tomcat.create(version, startup);
	}

	/**
	 * time format is -> 2014-01-06T21:23:15.299+0000: 0.957: [GC
	 */
	protected static long getTomcatStartupTime(String fqdn, SimpleDateFormat parser) {
		Optional<String> string = getTomcatStartupString(fqdn);
		if (!string.isPresent()) {
			return -1;
		}
		String s = string.get();
		int pos = s.indexOf(' ');
		String time = s.substring(0, pos - 1);
		try {
			Date date = parser.parse(time);
			return date.getTime();
		} catch (ParseException e) {
			throw Exceptions.illegalState(e, "date parse error -> [%s]", time);
		}
	}

	protected Optional<String> getTomcatStartupString(String fqdn) {
		String protocol = "http://";
		String fragment = "/tomcat/logs/heap.log";
		String location = protocol + fqdn + fragment;
		Optional<String> heap = read(location, 4096);
		if (!heap.isPresent()) {
			return Optional.absent();
		}
		String gc = StringUtils.substringBetween(heap.get(), "{", "}");
		List<String> lines = Splitter.on('\n').splitToList(gc);
		for (String line : lines) {
			if (line.startsWith("201")) { // This will only work for the next 6 years :)
				return Optional.of(line);
			}
		}
		return Optional.absent();
	}

	protected Optional<String> read(String location, int maxBytes) {
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
			return Optional.of(sb.toString());
		} catch (IOException e) {
			logger.warn(String.format("error reading -> [%s]", location));
		} finally {
			IOUtils.closeQuietly(in);
		}
		return Optional.absent();
	}
}
