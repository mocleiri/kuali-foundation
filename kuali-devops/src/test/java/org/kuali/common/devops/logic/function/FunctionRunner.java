package org.kuali.common.devops.logic.function;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.base.Function;

public final class FunctionRunner<F, T> implements Runnable {

	public static <F, T> FunctionRunner<F, T> create(Function<F, T> function, F input) {
		return new FunctionRunner<F, T>(function, input);
	}

	public FunctionRunner(Function<F, T> function, F input) {
		this.input = checkNotNull(input);
		this.function = checkNotNull(function);
	}

	private final F input;
	private final Function<F, T> function;
	private T result;

	@Override
	public void run() {
		this.result = function.apply(input);
	}

	public T getResult() {
		return result;
	}

	public F getInput() {
		return input;
	}

	public Function<F, T> getFunction() {
		return function;
	}
}
