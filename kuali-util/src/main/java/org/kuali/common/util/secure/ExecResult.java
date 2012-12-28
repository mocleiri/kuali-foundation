package org.kuali.common.util.secure;

import java.util.List;

public class ExecResult {

	String command;
	int exitValue;
	List<String> stdout;
	List<String> stderr;
	long start;
	long stop;
	long elapsed;

	public int getExitValue() {
		return exitValue;
	}

	public void setExitValue(int exitValue) {
		this.exitValue = exitValue;
	}

	public List<String> getStdout() {
		return stdout;
	}

	public void setStdout(List<String> stdout) {
		this.stdout = stdout;
	}

	public List<String> getStderr() {
		return stderr;
	}

	public void setStderr(List<String> stderr) {
		this.stderr = stderr;
	}

	public long getStart() {
		return start;
	}

	public void setStart(long start) {
		this.start = start;
	}

	public long getStop() {
		return stop;
	}

	public void setStop(long stop) {
		this.stop = stop;
	}

	public long getElapsed() {
		return elapsed;
	}

	public void setElapsed(long elapsed) {
		this.elapsed = elapsed;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

}
