package org.kuali.maven.plugins.spring;

import java.io.File;
import java.util.Properties;

import org.apache.maven.project.MavenProject;
import org.kuali.common.util.Assert;

public class TestableMavenProject extends MavenProject {

	public TestableMavenProject(Properties properties, File basedir, String artifactId, String groupId) {
		Assert.noBlanks(groupId, artifactId);
		Assert.noNulls(properties, basedir);
		this.properties = properties;
		this.basedir = basedir;
		this.artifactId = artifactId;
		this.groupId = groupId;
	}

	private final Properties properties;
	private final File basedir;
	private final String artifactId;
	private final String groupId;

	@Override
	public Properties getProperties() {
		return properties;
	}

	@Override
	public File getBasedir() {
		return basedir;
	}

	@Override
	public String getArtifactId() {
		return artifactId;
	}

	@Override
	public String getGroupId() {
		return groupId;
	}

}
