package org.kuali.common.devops.logic.function;

import static org.kuali.common.util.base.Assertions.assertNotNull;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.List;

import org.kuali.common.util.base.Exceptions;
import org.kuali.common.util.base.Threads;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

public class ConcurrentFunctions<F, T> implements UncaughtExceptionHandler {

	public ConcurrentFunctions(Function<F, T> function, List<F> inputs) {
		this.function = assertNotNull(function, "function");
		this.inputs = assertNotNull(inputs, "inputs");
	}

	private final Function<F, T> function;
	private final List<F> inputs;

	private List<IllegalStateException> exceptions = Lists.newArrayList();

	public List<T> apply() {
		List<FunctionRunner<F, T>> runners = Lists.newArrayList();
		List<Thread> threads = Lists.newArrayList();
		for (F input : inputs) {
			FunctionRunner<F, T> runner = new FunctionRunner<F, T>(function, input);
			Thread thread = new Thread(runner);
			thread.setUncaughtExceptionHandler(this);
			thread.setName("function runner");
			runners.add(runner);
			threads.add(thread);
		}
		Threads.start(threads);
		Threads.join(threads);
		if (!exceptions.isEmpty()) {
			throw exceptions.get(0);
		}
		List<T> results = Lists.newArrayList();
		for (FunctionRunner<F, T> runner : runners) {
			results.add(runner.getResult());
		}
		return results;
	}

	@Override
	public synchronized void uncaughtException(Thread thread, Throwable e) {
		exceptions.add(Exceptions.illegalState(e, "uncaught exception in thread [%s]", thread.getName()));
	}
}
