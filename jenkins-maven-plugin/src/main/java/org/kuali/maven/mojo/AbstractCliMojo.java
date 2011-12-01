package org.kuali.maven.mojo;

/**
 * 
 */
public abstract class AbstractCliMojo extends BaseMojo {

	/**
	 * The classname for Jenkins CLI
	 * 
	 * @parameter expression="${jenkins.classname}" default-value="hudson.cli.CLI"
	 * @required
	 */
	private String classname;

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

}
