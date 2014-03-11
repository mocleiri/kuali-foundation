package org.kuali.common.util.execute.impl;

import static org.kuali.common.util.base.Precondition.checkNotNull;

import java.util.List;

import org.kuali.common.util.execute.Executable;

public final class ExecutablesRunner implements Runnable {

	public ExecutablesRunner(List<Executable> executables) {
		checkNotNull(executables, "executables");
		this.executables = executables;
	}

	private final List<Executable> executables;

	@Override
	public void run() {
		for (Executable executable : executables) {
			executable.execute();
		}
	}

	public List<Executable> getExecutable() {
		return executables;
	}

}
