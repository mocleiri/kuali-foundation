package org.kuali.maven.mojo;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;
import org.kuali.maven.common.Extractor;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Template;

public class CreateJobFromTemplate extends AbstractMojo {
	Extractor extractor = new Extractor();

	/**
	 * The Maven project this plugin runs in.
	 * 
	 * @parameter expression="${project}"
	 * @required
	 * @readonly
	 */
	private MavenProject project;

	/**
	 * 
	 * @parameter expression="${jenkins.template}"
	 * @required
	 */
	private Template template;

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		String scmType = extractor.getScmType(project.getScm());
		String scmUrl = extractor.getScmUrl(project.getScm());
		String majorVersion = extractor.getMajorVersion(project.getVersion());

		Properties properties = project.getProperties();
		properties.setProperty("jenkins.project.scmType", scmType);
		properties.setProperty("jenkins.project.scmUrl", scmUrl);
		properties.setProperty("jenkins.project.majorVersion", majorVersion);

		List<String> locations = new ArrayList<String>();
		locations.add("classpath:org/kuali/cm/jenkins/kuali.properties");
		locations.add("classpath:org/kuali/cm/jenkins/jenkins.properties");
		locations.add("classpath:org/kuali/cm/jenkins/jobs/properties/common.xml");
	}

	public MavenProject getProject() {
		return project;
	}

	public void setProject(MavenProject project) {
		this.project = project;
	}

	public Template getTemplate() {
		return template;
	}

	public void setTemplate(Template template) {
		this.template = template;
	}
}
