package org.kuali.maven.mojo;

import java.io.File;
import java.io.IOException;

import org.apache.maven.plugin.MojoExecutionException;
import org.codehaus.plexus.configuration.PlexusConfigurationException;

/**
 * @goal antrun
 */
public class JenkinsAntRunMojo extends AbstractAntRunMojo {
	Generator generator = new Generator();

	@Override
	public void execute() throws MojoExecutionException {
		try {
			super.setAntTargetName("main");
			String location = "classpath:org/kuali/jenkins/ant/cli-wrapper.xml";
			String filename = getProject().getBuild().getDirectory() + "/jenkins/ant/cli-wrapper.xml";
			generator.copy(location, filename);
			super.execute();
		} catch (IOException e) {
			throw new MojoExecutionException("Unexpected error", e);
		}
	}

	@Override
	protected File writeTargetToProjectFile() throws IOException, PlexusConfigurationException {
		String filename = getProject().getBuild().getDirectory() + "/antrun/build-main.xml";
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
		sb.append("<project name=\"maven-antrun-\" default=\"main\"  >\n");
		sb.append("<target name=\"main\">\n");
		sb.append("  <property name=\"maven.plugin.classpath\" refid=\"maven.plugin.classpath\"/>\n");
		sb.append("  <ant antfile=\"target/jenkins/ant/cli-wrapper.xml\" target=\"noop\"/>\n");
		sb.append("</target>\n");
		sb.append("</project>\n");
		generator.write(filename, sb.toString());
		return new File(filename);

	}

}
