package org.kuali.maven.mojo;

import java.io.IOException;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

/**
 * @goal generatejobconfig
 */
public class GenerateJobConfigMojo extends AbstractGenerateMojo {
	Generator generator = new Generator();

	/**
	 * The type of job eg publish, unit, license, release
	 * 
	 * @parameter expression="${jenkins.type}" default-value="publish"
	 * @required
	 */
	private String type;

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		try {
			JobContext context = getJobContext(type);
			generator.fillInContext(context);
			getLog().info("Generating: " + context.getFilename());
			generator.generate(context);
		} catch (IOException e) {
			throw new MojoExecutionException("Unexpected error", e);
		}
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
