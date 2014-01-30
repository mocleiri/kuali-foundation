package org.kuali.common.devops.util;

import org.kuali.common.util.log.LoggerUtils;
import org.kuali.common.util.project.model.Project;
import org.slf4j.Logger;

public class Databases {

	private static final Logger logger = LoggerUtils.make();

	public static Database getDatabase(String fqdn, Project project) {
		return new Database();
	}

}
