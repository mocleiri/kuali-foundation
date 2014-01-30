package org.kuali.common.devops.util;

import java.util.Properties;

import org.kuali.common.util.log.LoggerUtils;
import org.kuali.common.util.project.model.Project;
import org.slf4j.Logger;

public class Configuration {

	private static final Logger logger = LoggerUtils.make();

	public static Properties getProperties(String fqdn, Project project) {
		return new Properties();
	}

}
