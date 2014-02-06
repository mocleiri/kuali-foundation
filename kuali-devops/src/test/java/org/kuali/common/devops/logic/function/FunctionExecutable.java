package org.kuali.common.devops.logic.function;

import static com.google.common.base.Preconditions.checkNotNull;

import org.kuali.common.util.execute.Executable;

import com.google.common.base.Function;

public final class FunctionExecutable<F, T> implements Executable {

	public static <F, T> FunctionExecutable<F, T> create(Function<F, T> function, F input) {
		return new FunctionExecutable<F, T>(function, input);
	}

	public FunctionExecutable(Function<F, T> function, F input) {
		this.function = checkNotNull(function);
		this.input = checkNotNull(input);
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
