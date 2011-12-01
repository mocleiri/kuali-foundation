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

	/**
	 * The command to issue to Jenkins CLI
	 * 
	 * @parameter expression="${jenkins.cmd}"
	 */
	private String cmd;

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

}
