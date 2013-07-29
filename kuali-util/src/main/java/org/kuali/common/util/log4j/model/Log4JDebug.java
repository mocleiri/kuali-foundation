package org.kuali.common.util.log4j.model;

import org.codehaus.plexus.util.StringUtils;

public enum Log4JDebug {

	TRUE, FALSE, NULL;

	@Override
	public String toString() {
		return StringUtils.lowerCase(name());
	}

}
