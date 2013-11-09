package org.kuali.common.util.execute.impl;

public final class IllegalStateExceptionRunnable implements Runnable {

	@Override
	public void run() {
		throw new IllegalStateException("Thrown from IllegalStateExceptionRunner");
	}

}
