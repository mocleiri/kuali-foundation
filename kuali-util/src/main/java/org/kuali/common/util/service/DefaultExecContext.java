package org.kuali.common.util.service;

import java.io.File;
import java.util.List;

import org.codehaus.plexus.util.cli.StreamConsumer;
import org.kuali.common.util.LoggerLevel;
import org.kuali.common.util.LoggingStreamConsumer;

public class DefaultExecContext implements ExecContext {

	StreamConsumer stdout = new LoggingStreamConsumer(DefaultExecService.getLogger(), LoggerLevel.INFO);
	StreamConsumer stderr = new LoggingStreamConsumer(DefaultExecService.getLogger(), LoggerLevel.WARN);
	String executable;
	List<String> args;
	int timeoutInSeconds;
	File workingDirectory;

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
	public StreamConsumer getStdout() {
		return stdout;
	}

	public void setStdout(StreamConsumer stdout) {
		this.stdout = stdout;
	}

	@Override
	public StreamConsumer getStderr() {
		return stderr;
	}

	public void setStderr(StreamConsumer stderr) {
		this.stderr = stderr;
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

}
