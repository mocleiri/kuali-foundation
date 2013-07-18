package org.kuali.common.impex.config;

import org.kuali.common.impex.ExportProjectConstants;
import org.kuali.common.util.config.ConfigUtils;
import org.kuali.common.util.config.KualiUtilConfig;
import org.kuali.common.util.config.ProjectConfig;
import org.kuali.common.util.project.ImmutableProject;

public enum KualiImpexExportConfig implements ProjectConfig {

	SCM(KualiUtilConfig.SCM.getContextId()), //
	DUMP("dump"), //
	DUMP_BUILD("dump:build"), //
	STAGING("staging"), //
	STAGING_BUILD("staging:build"); //

	private final ImmutableProject project = ExportProjectConstants.PROJECT;
	private final String contextId;
	private final String configId;

	private KualiImpexExportConfig(String contextId) {
		this.contextId = contextId;
		this.configId = ConfigUtils.getConfigId(this);
	}

	@Override
	public String getGroupId() {
		return project.getGroupId();
	}

	@Override
	public String getArtifactId() {
		return project.getArtifactId();
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
