package org.kuali.maven.mojo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.kuali.maven.common.PropertiesUtils;
import org.kuali.maven.mojo.context.JobContext;

/**
 * @goal genjobs
 */
public class GenJobsMojo extends AbstractGenerateMojo {
	Generator generator = new Generator();
	PropertiesUtils propertiesUtils = new PropertiesUtils();

	/**
	 * Comma separated list of the types of jobs to generate
	 * 
	 * @parameter expression="${jenkins.types}" default-value="unit,publish,license,release"
	 * @required
	 */
	private String types;

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		try {
			List<JobContext> contexts = getJobContexts(types);
			for (JobContext context : contexts) {
				getLog().info("Generating: " + context.getLocalFile());
				generator.generate(context);
			}
		} catch (IOException e) {
			throw new MojoExecutionException("Unexpected error", e);
		}
	}

	protected List<JobContext> getJobContexts(String types) {
		String[] tokens = propertiesUtils.getTokens(types, true);
		List<JobContext> contexts = new ArrayList<JobContext>();
		for (String type : tokens) {
			JobContext context = getJobContext(type);
			generator.fillInContext(context);
			contexts.add(context);
		}
		return contexts;
	}

	public String getTypes() {
		return types;
	}

	public void setType(String types) {
		this.types = types;
	}

}
