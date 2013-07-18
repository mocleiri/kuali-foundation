package org.kuali.common.util.metainf;

import org.kuali.common.util.KualiProjectConstants;
import org.kuali.common.util.project.ImmutableProject;

public enum MpxMetaInfConfig {

	DEFAULT("metainf:mpx"), BUILD("metainf:mpx:build");

	private ImmutableProject project = KualiProjectConstants.KUALI_UTIL;
	private String contextId;

	private MpxMetaInfConfig(String contextId) {
		this.contextId = contextId;
	}

	public String getId() {
		return project.getId() + ":" + contextId;
	}
}
