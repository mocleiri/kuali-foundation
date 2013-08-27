package org.kuali.common.jdbc.service;

import org.kuali.common.jdbc.listeners.SqlListener;
import org.kuali.common.jdbc.listeners.ThreadSafeListener;
import org.kuali.common.util.Assert;
import org.kuali.common.util.inform.PercentCompleteInformer;

public final class ThreadsContext {

	public ThreadsContext(PercentCompleteInformer informer, ThreadSafeListener threadSafeListener, SqlListener listener) {
		Assert.noNulls(informer, threadSafeListener, listener);
		this.informer = informer;
		this.threadSafeListener = threadSafeListener;
		this.listener = listener;
	}

	private final PercentCompleteInformer informer;
	private final ThreadSafeListener threadSafeListener;
	private final SqlListener listener;

	public PercentCompleteInformer getInformer() {
		return informer;
	}

	public ThreadSafeListener getThreadSafeListener() {
		return threadSafeListener;
	}

	public SqlListener getListener() {
		return listener;
	}

}
