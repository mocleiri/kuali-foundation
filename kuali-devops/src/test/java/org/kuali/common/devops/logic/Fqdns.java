package org.kuali.common.devops.logic;

import static java.lang.String.format;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.log.Loggers;
import org.slf4j.Logger;

import com.google.common.collect.ImmutableList;

public class Fqdns {

	private static final String PROTOCOL = "http://";
	private static final Logger logger = Loggers.make();
	private static final String NOT_AVAILABLE = "na";

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
			logger.warn(format("unexpected error reading from [%s]", location));
			return ImmutableList.of();
		}
	}
}
