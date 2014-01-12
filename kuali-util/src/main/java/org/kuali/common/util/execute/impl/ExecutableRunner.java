package org.kuali.common.util.execute.impl;

import org.kuali.common.util.execute.Executable;

import com.google.common.base.Preconditions;

public final class ExecutableRunner implements Runnable {

	public ExecutableRunner(Executable executable) {
		Preconditions.checkNotNull(executable, "executable cannot be null");
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
