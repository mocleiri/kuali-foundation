package org.kuali.common.util.execute.impl;

import static org.kuali.common.util.base.Precondition.checkNotNull;

import org.kuali.common.util.execute.Executable;

public final class ExecutableRunner implements Runnable {

	public ExecutableRunner(Executable executable) {
		checkNotNull(executable, "executable");
		this.executable = executable;
	}

	private final Executable executable;

	@Override
	public void run() {
		executable.execute();
	}

	public Executable getExecutable() {
		return executable;
	}

}
