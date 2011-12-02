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
package org.kuali.maven.mojo.context;

public class MojoContext {
	MavenContext mvnContext;
	JobContext jobContext;
	CliContext cliContext;
	AntContext antContext;
	ResultContext resultContext;

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

	public ResultContext getResultContext() {
		return resultContext;
	}

	public void setResultContext(ResultContext resultContext) {
		this.resultContext = resultContext;
	}

}
