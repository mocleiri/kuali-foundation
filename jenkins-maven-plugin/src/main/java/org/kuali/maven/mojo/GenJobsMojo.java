package org.kuali.maven.mojo;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.kuali.maven.common.PropertiesUtils;

/**
 * @goal genjobs
 */
public class GenJobsMojo extends AbstractGenerateMojo {

	/**
	 * Comma separated list of the types of jobs to generate
	 * 
	 * @parameter expression="${jenkins.types}" default-value="unit,publish,license,release"
	 * @required
	 */
	private String types;

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		String[] tokens = PropertiesUtils.splitAndTrim(types, ",");
		helper.generate(this, tokens);
	}

	public String getTypes() {
		return types;
	}

	public void setType(String types) {
		this.types = types;
	}

}
