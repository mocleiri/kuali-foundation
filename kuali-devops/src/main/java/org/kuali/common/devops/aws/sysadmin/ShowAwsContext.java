package org.kuali.common.devops.aws.sysadmin;

import org.kuali.common.aws.model.AwsAccount;
import org.kuali.common.aws.model.AwsContext;
import org.kuali.common.util.Assert;
import org.kuali.common.util.execute.Executable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShowAwsContext implements Executable {

	private static final Logger logger = LoggerFactory.getLogger(ShowAwsContext.class);

	private final AwsContext context;
	private final boolean skip;

	public ShowAwsContext(AwsContext context) {
		this(context, false);
	}

	public ShowAwsContext(AwsContext context, boolean skip) {
		Assert.noNulls(context);
		this.context = context;
		this.skip = skip;
	}

	@Override
	public void execute() {

		if (skip) {
			return;
		}

		AwsAccount account = context.getAccount();
		logger.info("---------- AWS Context ----------");
		logger.info("Account Name: {}", account.getName());
		if (account.getDescription().isPresent()) {
			logger.info("Description: {}", account.getDescription());
		}
		logger.info("---------------------------------");
	}

	public AwsContext getContext() {
		return context;
	}

	public boolean isSkip() {
		return skip;
	}

}
