package org.kuali.maven.mojo.context;

public class MojoContext {
	MavenContext mvnContext;
	JobContext jobContext;
	CliContext cliContext;
	AntContext antContext;

	public MavenContext getMvnContext() {
		return mvnContext;
	}

	public void setMvnContext(MavenContext mvnContext) {
		this.mvnContext = mvnContext;
	}

	public JobContext getJobContext() {
		return jobContext;
	}

	public void setJobContext(JobContext jobContext) {
		this.jobContext = jobContext;
	}

	public CliContext getCliContext() {
		return cliContext;
	}

	public void setCliContext(CliContext cliContext) {
		this.cliContext = cliContext;
	}

	public AntContext getAntContext() {
		return antContext;
	}

	public void setAntContext(AntContext antContext) {
		this.antContext = antContext;
	}

}
