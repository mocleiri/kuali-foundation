package org.codehaus.mojo.properties;

import java.util.Properties;

import org.apache.maven.project.MavenProject;

public class MavenUtils {

	/**
	 * Return properties Maven uses internally
	 */
	public static final Properties getInternalMavenProperties(MavenProject project) {
		Properties properties = new Properties();
		properties.setProperty("project.groupId", project.getGroupId());
		properties.setProperty("project.artifactId", project.getArtifactId());
		properties.setProperty("project.version", project.getVersion());
		properties.setProperty("project.basedir", project.getBasedir().getAbsolutePath());
		properties.setProperty("project.build.directory", project.getBuild().getDirectory());
		return properties;
	}

}
