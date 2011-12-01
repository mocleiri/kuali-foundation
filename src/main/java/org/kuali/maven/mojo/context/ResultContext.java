package org.kuali.maven.mojo.context;

import org.apache.maven.plugin.MojoExecutionException;

public class ResultContext {
	Integer returnCode;
	MojoExecutionException exception;

	public ResultContext() {
		this(null, null);
	}

	public ResultContext(Integer returnCode, MojoExecutionException exception) {
		super();
		this.returnCode = returnCode;
		this.exception = exception;
	}

	public Integer getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(Integer returnCode) {
		this.returnCode = returnCode;
	}

	public MojoExecutionException getException() {
		return exception;
	}

	public void setException(MojoExecutionException exception) {
		this.exception = exception;
	}

}
