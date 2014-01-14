package org.codehaus.plexus.util.cli;

public class ProcessHook extends Thread {

	private final Process process;

	public ProcessHook(Process process) {
		super("CommandlineUtils process shutdown hook");
		this.process = process;
		this.setContextClassLoader(null);
	}

	@Override
	public void run() {
		process.destroy();
	}
}
