package org.kuali.common.devops.logic;

import static java.lang.String.format;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.log.Loggers;
import org.slf4j.Logger;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

public class Examiner {

	public static final String PROTOCOL = "http://";
	private static final Logger logger = Loggers.make();

	public static Optional<String> getJavaVersion(String fqdn) {
		return getSystemProperty(fqdn, "java.version");
	}

	public static Optional<String> getSystemProperty(String fqdn, String property) {
		String fragment = "/tomcat/logs/env.jsp";
		String location = PROTOCOL + fqdn + fragment;
		List<String> lines = readLines(location);
		if (lines.isEmpty()) {
			return Optional.absent();
		}
		for (String line : lines) {
			String token = "<td>" + property + "</td>";
			if (line.contains(token)) {
				int pos = line.indexOf(token) + token.length();
				String substring = line.substring(pos);
				String value = StringUtils.substringBetween(substring, "<td>", "</td>");
				return Optional.of(StringUtils.trim(value));
			}
		}
		throw Exceptions.illegalState("unable to locate system property -> [%s]", property);
	}

	public static List<String> readLines(String location) {
		try {
			return LocationUtils.readLines(location);
		} catch (Exception e) {
			logger.debug(format("unexpected error reading from [%s]", location));
			return ImmutableList.of();
		}
	}

}
