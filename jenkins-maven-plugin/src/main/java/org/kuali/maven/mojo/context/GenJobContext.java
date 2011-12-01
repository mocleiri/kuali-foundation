package org.kuali.maven.mojo.context;

public class GenJobContext {
	String type;
	MavenContext mavenContext;
	JobContext jobContext;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

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

}
