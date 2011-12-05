/**
 * Copyright 2004-2011 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.maven.mojo.jenkins.context;

import java.util.List;

import org.apache.maven.plugin.MojoExecutionException;

public class ResultContext {
	Integer returnCode;
	MojoExecutionException exception;
	String fileContents;
	List<String> fileContentLines;

	public ResultContext() {
		this(null, null);
	}

	public ResultContext(Integer returnCode, MojoExecutionException exception) {
		this(returnCode, exception, null, null);
	}

	public ResultContext(Integer returnCode, MojoExecutionException exception, String fileContents,
			List<String> fileContentLines) {
		super();
		this.returnCode = returnCode;
		this.exception = exception;
		this.fileContents = fileContents;
		this.fileContentLines = fileContentLines;
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

	public String getFileContents() {
		return fileContents;
	}

	public void setFileContents(String fileContents) {
		this.fileContents = fileContents;
	}

	public List<String> getFileContentLines() {
		return fileContentLines;
	}

	public void setFileContentLines(List<String> fileContentLines) {
		this.fileContentLines = fileContentLines;
	}

}
