package org.kuali.common.util.config;

import org.kuali.common.util.KualiProjectConstants;

public class KualiUtilConfigRequest extends ConfigRequest {

	public KualiUtilConfigRequest() {
		this(null);
	}

	public KualiUtilConfigRequest(String contextId) {
		super(KualiProjectConstants.UTIL_ARTIFACT_ID, contextId);
	}

}
