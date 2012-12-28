package org.kuali.common.util.secure;

public class Result {

	String command;
	int exitValue;
	byte[] stdout;
	byte[] stderr;
	long start;
	long stop;
	long elapsed;

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public int getExitValue() {
		return exitValue;
	}

	public void setExitValue(int exitValue) {
		this.exitValue = exitValue;
	}

	public byte[] getStdout() {
		return stdout;
	}

	public void setStdout(byte[] stdout) {
		this.stdout = stdout;
	}

	public byte[] getStderr() {
		return stderr;
	}

	public void setStderr(byte[] stderr) {
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

}
