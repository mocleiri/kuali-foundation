package org.kuali.common.devops.logic.function;

import static org.kuali.common.util.base.Assertions.assertNotNull;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.List;

import org.kuali.common.util.base.Exceptions;
import org.kuali.common.util.base.Threads;
import org.kuali.common.util.execute.Executable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

public class ConcurrentFunctions<F, T> implements Executable, UncaughtExceptionHandler {

	public static <F, T> void execute(FunctionRunner<F, T>... runners) {
		execute(ImmutableList.copyOf(runners));
	}

	public static <F, T> void execute(List<FunctionRunner<F, T>> runners) {
		new ConcurrentFunctions<F, T>(runners).execute();
	}

	public ConcurrentFunctions(List<FunctionRunner<F, T>> runners) {
		this.runners = assertNotNull(runners, "runners");
	}

	private final List<FunctionRunner<F, T>> runners;

	private List<IllegalStateException> exceptions = Lists.newArrayList();

	@Override
	public void execute() {
		List<Thread> threads = Lists.newArrayList();
		for (FunctionRunner<F, T> runner : runners) {
			Thread thread = new Thread(runner, "function runner");
			thread.setUncaughtExceptionHandler(this);
			threads.add(thread);
		}
		Threads.start(threads);
		Threads.join(threads);
		if (!exceptions.isEmpty()) {
			throw exceptions.get(0);
		}
	}

	@Override
	public synchronized void uncaughtException(Thread thread, Throwable e) {
		exceptions.add(Exceptions.illegalState(e, "uncaught exception in thread [%s]", thread.getName()));
	}
}
