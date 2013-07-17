package org.kuali.common.util.config;

public abstract class ConfigConstants {

	// Used to indicate a config grouping that is unsafe to use anywhere except during a build.
	// It is likely to contain build specific properties like ${project.build.directory} in the config that will be unresolvable at runtime
	public static final String BUILD = "build";

}
