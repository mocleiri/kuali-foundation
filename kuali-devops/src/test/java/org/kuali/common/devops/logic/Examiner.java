package org.kuali.common.devops.logic;

import static java.lang.String.format;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.common.http.model.HttpContext;
import org.kuali.common.http.model.HttpWaitResult;
import org.kuali.common.http.service.DefaultHttpService;
import org.kuali.common.http.service.HttpService;
import org.kuali.common.util.base.Exceptions;
import org.kuali.common.util.log.Loggers;
import org.slf4j.Logger;

import com.google.common.base.Optional;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;

public class Examiner {

	protected static final String PROTOCOL = "http://";
	private static final Logger logger = Loggers.make();
	protected static final String SCM_URL_KEY = "project.scm.url";
	protected static final String SCM_REVISION_KEY = "project.scm.revision";
	public static final String ENV_JSP_FRAGMENT = "/tomcat/logs/env.jsp";

	public static final String getEnvJspUrl(String fqdn) {
		return getUrl(fqdn, ENV_JSP_FRAGMENT);
	}

	public static Optional<String> getJavaVersion(String fqdn) {
		return getSystemProperty(fqdn, "java.version");
	}

	protected static String getUrl(String fqdn, String fragment) {
		return PROTOCOL + fqdn + fragment;
	}

	public static Optional<String> getSystemProperty(String fqdn, String property) {
		List<String> lines = readLines(getEnvJspUrl(fqdn));
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
		HttpService service = new DefaultHttpService();
		HttpContext context = new HttpContext.Builder(location).overallTimeout("3s").sleepIntervalMillis(500).requestTimeout("3s").build();
		try {
			HttpWaitResult result = service.wait(context);
			return Splitter.on('\n').splitToList(result.getFinalRequestResult().getResponseBody().get());
		} catch (Exception e) {
			logger.debug(format("unexpected error reading from [%s]", location));
			return ImmutableList.of();
		}
	}

}
