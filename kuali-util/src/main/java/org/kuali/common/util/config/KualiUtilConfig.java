package org.kuali.common.util.config;

import java.util.ArrayList;
import java.util.List;

import org.kuali.common.util.KualiProjectConstants;
import org.kuali.common.util.project.ImmutableProject;

public enum KualiUtilConfig implements ConfigIdSupplier {

	SCM("scm"), // Config for SCM related process
	SQL("metainf:sql"), // Config for META-INF process related for SQL
	SQL_BUILD("metainf:sql:build"), // Config for META-INF process related to SQL, only available during a build
	MPX("metainf:mpx"), // Config for META-INF process related to MPX
	MPX_BUILD("metainf:mpx:build"); // Config for META-INF process related to MPX, only available during a build

	private final ImmutableProject project = KualiProjectConstants.KUALI_UTIL;
	private final String contextId;
	private final String id;

	private KualiUtilConfig(String contextId) {
		this.contextId = contextId;
		this.id = project.getId() + ":" + contextId;
	}

	public String getContextId() {
		return contextId;
	}

	public String getId() {
		return id;
	}

	@Override
	public String toString() {
		return id;
	}

	@Override
	public List<String> getConfigIds() {
		List<String> configIds = new ArrayList<String>();
		for (KualiUtilConfig config : values()) {
			configIds.add(config.getId());
		}
		return configIds;
	}

}
