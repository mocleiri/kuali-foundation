package org.kuali.common.util.execute.impl;

import org.kuali.common.util.Assert;
import org.kuali.common.util.execute.Executable;

public final class ExecutableRunner implements Runnable {

	public ExecutableRunner(Executable executable) {
		Assert.noNulls(executable);
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
