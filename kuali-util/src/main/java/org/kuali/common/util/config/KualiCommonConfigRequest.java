package org.kuali.common.util.config;

import org.kuali.common.util.KualiProjectConstants;

public class KualiCommonConfigRequest extends ConfigRequest {

	public KualiCommonConfigRequest() {
		this(null);
	}

	public KualiCommonConfigRequest(String artifactId) {
		this(artifactId, null);
	}

	public KualiCommonConfigRequest(String artifactId, String contextId) {
		super(KualiProjectConstants.COMMON_GROUP_ID, artifactId, contextId);
	}

}
