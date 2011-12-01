package org.kuali.maven.mojo.context;

import java.util.List;

public class GenJobsContext {
	List<String> types;
	MavenContext mavenContext;
	JobContext jobContext;

	public MavenContext getMavenContext() {
		return mavenContext;
	}

	public void setMavenContext(MavenContext mavenContext) {
		this.mavenContext = mavenContext;
	}

	public JobContext getJobContext() {
		return jobContext;
	}

	public void setJobContext(JobContext jobContext) {
		this.jobContext = jobContext;
	}

	public List<String> getTypes() {
		return types;
	}

	public void setTypes(List<String> types) {
		this.types = types;
	}

}
