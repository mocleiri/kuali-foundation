package org.kuali.common.util.spring.main;

import org.kuali.common.util.Assert;
import org.kuali.common.util.execute.Executable;

public class ValidatePropertyLocationArgsExecutable implements Executable {

	private ValidatePropertyLocationArgsExecutable(MainContext context, String message) {
		Assert.noNulls(context, message);
		this.context = context;
		this.message = message;
	}

	private final String message;
	private final MainContext context;

	@Override
	public void execute() {
		String[] args = context.getArgs();
		Assert.notNull(args, message);
		Assert.isTrue(args.length > 0, message);
	}

	public String getMessage() {
		return message;
	}

	public MainContext getContext() {
		return context;
	}

}
