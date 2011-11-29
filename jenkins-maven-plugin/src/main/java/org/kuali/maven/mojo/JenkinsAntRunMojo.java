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
		super.setAntTargetName("main");
		String location = "classpath:org/kuali/jenkins/ant/cli-wrapper.xml";
		super.execute();
	}

	@Override
	protected File writeTargetToProjectFile() throws IOException, PlexusConfigurationException {
		String filename = getProject().getBuild().getDirectory() + "/antrun/build-main.xml";
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
		sb.append("<project name=\"maven-antrun-\" default=\"main\"  >\n");
		sb.append("<target name=\"main\">\n");
		sb.append("  <echo>hello world</echo>\n");
		sb.append("</target>\n");
		sb.append("</project>\n");
		generator.write(filename, sb.toString());
		return new File(filename);

	}

}
