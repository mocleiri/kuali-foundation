package org.kuali.common.devops.model;

import static org.kuali.common.util.property.ImmutableProperties.copyOf;

import java.util.Properties;

import org.kuali.common.util.property.ImmutableProperties;

public final class GlobalProperties {

	public GlobalProperties(Properties system, Properties environment) {
		this.system = copyOf(system);
		this.environment = copyOf(environment);
	}

	private final ImmutableProperties system;
	private final ImmutableProperties environment;

	public Properties getSystem() {
		return system;
	}

	public Properties getEnvironment() {
		return environment;
	}

}
