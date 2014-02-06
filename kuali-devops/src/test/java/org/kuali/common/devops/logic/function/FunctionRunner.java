package org.kuali.common.devops.logic.function;

import static org.kuali.common.util.base.Assertions.assertNotNull;

import com.google.common.base.Function;

public class FunctionRunner<F, T> implements Runnable {

	public static <F, T> FunctionRunner<F, T> create(Function<F, T> function, F input) {
		return new FunctionRunner<F, T>(function, input);
	}

	public FunctionRunner(Function<F, T> function, F input) {
		this.input = assertNotNull(input, "input");
		this.function = assertNotNull(function, "function");
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
