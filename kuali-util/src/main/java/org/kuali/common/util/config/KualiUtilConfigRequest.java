package org.kuali.common.util.config;

import org.kuali.common.util.ProjectConstants;

public class KualiUtilConfigRequest extends ConfigRequest {

	public KualiUtilConfigRequest() {
		this(null);
	}

	public KualiUtilConfigRequest(String contextId) {
		super(ProjectConstants.COMMON_GROUP_ID, ProjectConstants.UTIL_ARTIFACT_ID);
	}

}
