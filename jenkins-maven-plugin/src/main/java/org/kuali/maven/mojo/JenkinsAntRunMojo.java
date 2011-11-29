package org.kuali.maven.mojo;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.antrun.AntrunXmlPlexusConfigurationWriter;
import org.codehaus.plexus.configuration.PlexusConfiguration;
import org.codehaus.plexus.configuration.PlexusConfigurationException;
import org.codehaus.plexus.util.FileUtils;

/**
 * @goal antrun
 */
public class JenkinsAntRunMojo extends AbstractAntRunMojo {
	Generator generator = new Generator();

	@Override
	public void execute() throws MojoExecutionException {
		getLog().info("hello world");
		PlexusConfiguration pc
		super.execute();
	}

	/**
	 * Write the ant target and surrounding tags to a temporary file
	 * 
	 * @throws PlexusConfigurationException
	 */
	@Override
	protected File writeTargetToProjectFile() throws IOException, PlexusConfigurationException {
		String filename = getProject().getBuild().getDirectory() + "/antrun/build-main.xml";
		
	}

}
