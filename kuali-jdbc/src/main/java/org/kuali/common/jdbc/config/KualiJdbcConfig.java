package org.kuali.common.jdbc.config;

import org.kuali.common.util.KualiProjectConstants;
import org.kuali.common.util.config.ConfigUtils;
import org.kuali.common.util.config.ProjectConfig;
import org.kuali.common.util.project.ImmutableProject;

public enum KualiJdbcConfig implements ProjectConfig {

	DEFAULT(); // Provides the default set of configuration for JDBC related processes

	private final ImmutableProject project = KualiProjectConstants.KUALI_JDBC;
	private final String contextId;
	private final String configId;

	private KualiJdbcConfig() {
		this(null);
	}

	private KualiJdbcConfig(String contextId) {
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
