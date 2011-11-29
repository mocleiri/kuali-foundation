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

	/**
	 * @parameter expression="${jenkins.cli.cmd}" default-value="noop"
	 * @required
	 */
	private String cliCommand;

	/**
	 * @parameter expression="${jenkins.cliWrapper}" default-value="classpath:org/kuali/jenkins/ant/cli-wrapper.xml"
	 * @required
	 */
	private String cliWrapper;

	private File localFile;

	@Override
	public void execute() throws MojoExecutionException {
		try {
			super.setAntTargetName("main");
			localFile = new File(getProject().getBuild().getDirectory() + "/antrun/build-local.xml");
			generator.copy(cliWrapper, localFile.getAbsolutePath());
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
		sb.append("<project name=\"maven-antrun-\" default=\"main\">\n");
		sb.append("  <target name=\"main\">\n");
		sb.append("    <property name=\"maven.plugin.classpath\" refid=\"maven.plugin.classpath\"/>\n");
		sb.append("    <ant antfile=\"" + localFile.getAbsolutePath() + "\" target=\"" + cliCommand + "\"/>\n");
		sb.append("  </target>\n");
		sb.append("</project>\n");
		generator.write(filename, sb.toString());
		return new File(filename);

	}
}
