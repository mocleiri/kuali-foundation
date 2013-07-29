package org.kuali.common.util.log4j.model;

import org.codehaus.plexus.util.StringUtils;

public enum Log4JThreshold {

	ALL, TRACE, DEBUG, INFO, WARN, ERROR, FATAL, OFF, NULL;

	@Override
	public String toString() {
		return StringUtils.lowerCase(name());
	}
}
