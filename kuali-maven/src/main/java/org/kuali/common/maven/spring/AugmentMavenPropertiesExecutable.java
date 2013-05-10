package org.kuali.common.maven.spring;

import java.util.Properties;

import org.apache.maven.project.MavenProject;
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
		if (skip) {
			logger.info("Skipping execution");
			return;
		}
		// This is the original Maven project object created by Maven and injected into the context by spring-maven-plugin
		Assert.notNull(mavenProject, "mavenProject is null");

		// This is a derived set of properties mostly populated by Maven, but augmented by other logic
		// It adds in a few timestamp related properties, adds orgId, adds properties for paths based on orgId, groupId
		// eg project.groupId.path=org/kuali/rice
		Assert.notNull(augmentedMavenProperties, "augmentedMavenProperties is null");

		// The augmented set of properties is 90% the same with 10% new properties added
		mavenProject.getProperties().putAll(augmentedMavenProperties);
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
