package org.kuali.common.util.config;

import org.kuali.common.util.KualiProjectConstants;
import org.kuali.common.util.project.ImmutableProject;

public enum KualiUtilConfig implements ConfigIdSupplier {

	SCM("scm"), // Config for SCM related process
	SQL("metainf:sql"), // Config for META-INF processing for SQL resources
	MPX("metainf:mpx"), // Config for META-INF processing for MPX resources
	SQL_BUILD("metainf:sql:build"), // Config for META-INF processing for SQL files, only available during a build
	MPX_BUILD("metainf:mpx:build"); // Config for META-INF processing for MPX files, only available during a build

	private final ImmutableProject project = KualiProjectConstants.KUALI_UTIL;
	private final String contextId;
	private final String configId;

	private KualiUtilConfig(String contextId) {
		this.contextId = contextId;
		this.configId = ConfigUtils.getConfigId(project, contextId);
	}

	public String getContextId() {
		return contextId;
	}

	@Override
	public String getConfigId() {
		return configId;
	}

	public ImmutableProject getProject() {
		return project;
	}

}
