package org.kuali.common.util.execute.impl;

import org.kuali.common.util.execute.Executable;

public final class ExceptionExecutable implements Executable {

	@Override
	public void execute() {
		throw new IllegalStateException("Thrown from ExceptionExecutable");
	}

}
