package org.kuali.common.util.main;

import org.kuali.common.util.Assert;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.execute.Executable;

/**
 * Validate <code>String[] args</code> is not null, contains at least one non-blank value and points to a location that exists.
 */
public final class ValidatePropertiesLocationExecutable implements Executable {

	public ValidatePropertiesLocationExecutable(MainContext context, String message) {
		Assert.noNulls(context);
		Assert.noBlanks(message);
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
		String location = args[0];
		Assert.noBlanks(message, location);
		LocationUtils.validateLocation(location, message);
	}

	public String getMessage() {
		return message;
	}

	public MainContext getContext() {
		return context;
	}

}
