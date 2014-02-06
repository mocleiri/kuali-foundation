package org.kuali.common.devops.logic.function;

import static com.google.common.base.Preconditions.checkArgument;
import static org.kuali.common.util.base.Assertions.assertNotNull;

import com.google.common.base.Function;

public class FunctionRunner<F, T> implements Runnable {

	public FunctionRunner(Function<F, T> function, F input) {
		this.input = assertNotNull(input, "input");
		this.function = assertNotNull(function, "function");
	}

	private final F input;
	private final Function<F, T> function;
	private T result;
	private boolean done = false;

	@Override
	public void run() {
		this.result = function.apply(input);
		this.done = true;
	}

	public T getResult() {
		checkArgument(done, "not done");
		return result;
	}

	public F getInput() {
		return input;
	}

	public Function<F, T> getFunction() {
		return function;
	}
}
