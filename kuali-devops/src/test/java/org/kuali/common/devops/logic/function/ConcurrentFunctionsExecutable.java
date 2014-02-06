package org.kuali.common.devops.logic.function;

import static org.kuali.common.util.base.Assertions.assertNotNull;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.List;

import org.kuali.common.util.base.Exceptions;
import org.kuali.common.util.base.Threads;
import org.kuali.common.util.execute.Executable;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

public class ConcurrentFunctionsExecutable<F, T> implements Executable, UncaughtExceptionHandler {

	public ConcurrentFunctionsExecutable(Function<F, T> function, List<F> inputs) {
		this.function = assertNotNull(function, "function");
		this.inputs = assertNotNull(inputs, "inputs");
	}

	private final Function<F, T> function;
	private final List<F> inputs;

	private List<IllegalStateException> exceptions = Lists.newArrayList();

	@Override
	public void execute() {
		List<FunctionRunner<F, T>> runners = Lists.newArrayList();
		List<Thread> threads = Lists.newArrayList();
		for (F input : inputs) {
			FunctionRunner<F, T> runner = new FunctionRunner<F, T>(function, input);
			Thread thread = new Thread(runner, "function runner");
			thread.setUncaughtExceptionHandler(this);
			runners.add(runner);
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
