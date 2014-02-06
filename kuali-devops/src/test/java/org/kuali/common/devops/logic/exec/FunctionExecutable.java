package org.kuali.common.devops.logic.exec;

import static org.kuali.common.util.base.Assertions.assertNotNull;

import org.kuali.common.util.execute.Executable;

import com.google.common.base.Function;

public final class FunctionExecutable<F, T> implements Executable {

	public static <F, T> FunctionExecutable<F, T> create(Function<F, T> function, F input) {
		return new FunctionExecutable<F, T>(function, input);
	}

	public FunctionExecutable(Function<F, T> function, F input) {
		this.function = assertNotNull(function, "function");
		this.input = assertNotNull(input, "input");
	}

	private final Function<F, T> function;
	private final F input;

	private T result;

	@Override
	public void execute() {
		this.result = function.apply(input);
	}

	public T getResult() {
		return result;
	}

}
