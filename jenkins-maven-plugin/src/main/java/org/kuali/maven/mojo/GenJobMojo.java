package org.kuali.maven.mojo;

import org.apache.maven.plugin.MojoExecutionException;

/**
 * @goal genjob
 */
public class GenJobMojo extends AbstractGenerateMojo {

	/**
	 * The type of job eg publish, unit, license, release
	 * 
	 * @parameter expression="${jenkins.type}" default-value="publish"
	 * @required
	 */
	private String type;

	@Override
	public void execute() throws MojoExecutionException {
		helper.generate(this, type);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
