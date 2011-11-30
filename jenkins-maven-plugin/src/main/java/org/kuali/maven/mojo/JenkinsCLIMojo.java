package org.kuali.maven.mojo;

import org.apache.maven.plugin.MojoExecutionException;
import org.kuali.maven.common.PropertiesUtils;


public class JenkinsCLIMojo extends JenkinsAntRunMojo {
	PropertiesUtils pu = new PropertiesUtils();

	@Override
	public void execute() throws MojoExecutionException {
		String location = "classpath:org/kuali/jenkins/jenkins.properties";
		Properties properties = pu.getProperties(location);
		
		super.execute();
	}

}
