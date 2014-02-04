package org.kuali.common.devops.logic;

import static java.lang.String.format;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.log.Loggers;
import org.slf4j.Logger;

public class Fqdns {

	private static final String PROTOCOL = "http://";
	private static final Logger logger = Loggers.make();
	private static final String NOT_AVAILABLE = "na";

	public String getJavaVersion(String fqdn) {
		return getSystemProperty(fqdn, "java.version");
	}

	public String getSystemProperty(String fqdn, String property) {
		String fragment = "/tomcat/logs/env.jsp";
		String location = PROTOCOL + fqdn + fragment;
		try {
			List<String> lines = LocationUtils.readLines(location);
			for (String line : lines) {
				String token = "<td>" + property + "</td>";
				if (line.contains(token)) {
					int pos = line.indexOf(token) + token.length();
					String substring = line.substring(pos);
					String value = StringUtils.substringBetween(substring, "<td>", "</td>");
					return StringUtils.trim(value);
				}
			}
		} catch (Exception e) {
			logger.warn(format("error getting system property [%s] -> [%s]", property, location));
		}
		return NOT_AVAILABLE;
	}
}
