package org.kuali.maven.mojo;


/**
 * 
 */
public abstract class AbstractGenerateMojo extends BaseMojo {
	Generator generator = new Generator();

	/**
	 * The location of the jenkins job config template
	 * 
	 * @parameter expression="${jenkins.template}" default-value="classpath:org/kuali/jenkins/jobs/template.xml"
	 * @required
	 */
	private String template;

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

}
