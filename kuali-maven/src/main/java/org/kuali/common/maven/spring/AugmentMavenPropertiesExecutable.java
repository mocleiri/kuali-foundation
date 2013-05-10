package org.kuali.common.maven.spring;

import java.util.Properties;

import org.apache.maven.project.MavenProject;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.execute.Executable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

public class AugmentMavenPropertiesExecutable implements Executable {

	private static final Logger logger = LoggerFactory.getLogger(AugmentMavenPropertiesExecutable.class);

	MavenProject mavenProject;
	Properties augmentedMavenProperties;
	boolean skip;

	@Override
	public void execute() {

		// Are we skipping execution altogether?
		if (skip) {
			logger.info("Skipping execution");
			return;
		}

		// This is the original Maven project object created by Maven and injected into the context by spring-maven-plugin
		Assert.notNull(mavenProject, "mavenProject is null");

		// This is a derived set of properties mostly populated by Maven, but augmented by other logic that enhances the default set of Maven properties
		// eg project.groupId.path=org/kuali/rice
		// eg project.version.snapshot=true
		// eg project.build.timestamp.millis=123456789
		Assert.notNull(augmentedMavenProperties, "augmentedMavenProperties is null");

		// The augmented set of properties is mostly the same as the original with ~5% new properties added
		mavenProject.getProperties().putAll(augmentedMavenProperties);

		// Print something useful if we are in debug mode
		int size1 = mavenProject.getProperties().size();
		int size2 = augmentedMavenProperties.size();
		String diff = FormatUtils.getCount(size2 - size1);
		logger.debug("Added {} properties", diff);

	}

	public MavenProject getMavenProject() {
		return mavenProject;
	}

	public void setMavenProject(MavenProject mavenProject) {
		this.mavenProject = mavenProject;
	}

	public Properties getAugmentedMavenProperties() {
		return augmentedMavenProperties;
	}

	public void setAugmentedMavenProperties(Properties augmentedMavenProperties) {
		this.augmentedMavenProperties = augmentedMavenProperties;
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

}
