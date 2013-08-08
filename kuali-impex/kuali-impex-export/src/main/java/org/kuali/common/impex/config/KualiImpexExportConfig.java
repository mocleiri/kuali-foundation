package org.kuali.common.impex.config;


/**
 * @deprecated
 */
@Deprecated
public enum KualiImpexExportConfig implements org.kuali.common.util.config.ProjectConfig {

	DUMP("dump"), //
	DUMP_BUILD("dump:build"), //
	STAGING("staging"), //
	STAGING_BUILD("staging:build"); //

	private final String contextId;
	private final String configId;

	private KualiImpexExportConfig(String contextId) {
		this.contextId = contextId;
		this.configId = org.kuali.common.util.config.ConfigUtils.getConfigId(this);
	}

	@Override
	public String getGroupId() {
		return ExportProjectConstants.GROUP_ID;
	}

	@Override
	public String getArtifactId() {
		return ExportProjectConstants.ARTIFACT_ID;
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
