package org.kuali.common.impex.config;

import org.kuali.common.util.config.ConfigUtils;
import org.kuali.common.util.config.ProjectConfig;
import org.kuali.common.util.project.KualiConstants;

public enum KualiImpexExportConfig implements ProjectConfig {

	DUMP("dump"), //
	DUMP_BUILD("dump:build"), //
	STAGING("staging"), //
	STAGING_BUILD("staging:build"); //

	public static final String ARTIFACT_ID = "kuali-impex-export";
	private final String contextId;
	private final String configId;

	private KualiImpexExportConfig(String contextId) {
		this.contextId = contextId;
		this.configId = ConfigUtils.getConfigId(this);
	}

	@Override
	public String getGroupId() {
		return KualiConstants.COMMON_GROUP_ID;
	}

	@Override
	public String getArtifactId() {
		return ARTIFACT_ID;
	}

	@Override
	public String getContextId() {
		return contextId;
	}

	@Override
	public String getConfigId() {
		return configId;
	}

}
