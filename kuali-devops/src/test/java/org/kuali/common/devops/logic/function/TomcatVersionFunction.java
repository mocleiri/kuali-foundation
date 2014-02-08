package org.kuali.common.devops.logic.function;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.apache.commons.lang.StringUtils.substringBetween;

import com.google.common.base.Function;

public final class TomcatVersionFunction implements Function<String, String> {

	@Override
	public String apply(String htmlContent) {
		checkNotNull(htmlContent);
		return substringBetween(htmlContent, "<h3>Apache Tomcat/", "</h3>");
	}

}
