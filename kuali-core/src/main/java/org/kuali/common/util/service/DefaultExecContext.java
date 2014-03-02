/**
 * Copyright 2010-2013 The Kuali Foundation
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
package org.kuali.common.util.service;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import org.codehaus.plexus.util.cli.StreamConsumer;

public class DefaultExecContext implements ExecContext {

	InputStream input;
	String executable;
	List<String> args;
	int timeoutInSeconds;
	File workingDirectory;
	StreamConsumer standardOutConsumer;
	StreamConsumer standardErrConsumer;
	boolean addSystemEnvironment = false;

	@Override
	public InputStream getInput() {
		return input;
	}

	public void setInput(InputStream input) {
		this.input = input;
	}

	@Override
	public String getExecutable() {
		return executable;
	}

	public void setExecutable(String executable) {
		this.executable = executable;
	}

	@Override
	public List<String> getArgs() {
		return args;
	}

	public void setArgs(List<String> args) {
		this.args = args;
	}

	@Override
	public int getTimeoutInSeconds() {
		return timeoutInSeconds;
	}

	public void setTimeoutInSeconds(int timeoutInSeconds) {
		this.timeoutInSeconds = timeoutInSeconds;
	}

	@Override
	public File getWorkingDirectory() {
		return workingDirectory;
	}

	public void setWorkingDirectory(File workingDirectory) {
		this.workingDirectory = workingDirectory;
	}

	@Override
	public StreamConsumer getStandardOutConsumer() {
		return standardOutConsumer;
	}

	public void setStandardOutConsumer(StreamConsumer standardOutConsumer) {
		this.standardOutConsumer = standardOutConsumer;
	}

	@Override
	public StreamConsumer getStandardErrConsumer() {
		return standardErrConsumer;
	}

	public void setStandardErrConsumer(StreamConsumer standardErrConsumer) {
		this.standardErrConsumer = standardErrConsumer;
	}

	public boolean isAddSystemEnvironment() {
		return addSystemEnvironment;
	}

	public void setAddSystemEnvironment(boolean addSystemEnvironment) {
		this.addSystemEnvironment = addSystemEnvironment;
	}

}
