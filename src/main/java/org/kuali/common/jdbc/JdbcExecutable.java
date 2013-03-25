package org.kuali.common.jdbc;

import org.kuali.common.jdbc.context.JdbcContext;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.execute.Executable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

public class JdbcExecutable implements Executable {

	private static final Logger logger = LoggerFactory.getLogger(JdbcExecutable.class);

	JdbcService service = new DefaultJdbcService();
	JdbcContext context;
	boolean skip;

	public JdbcExecutable() {
		this(null, null);
	}

	public JdbcExecutable(JdbcService service, JdbcContext context) {
		super();
		this.service = service;
		this.context = context;
	}

	@Override
	public void execute() {
		if (skip) {
			logger.info("Skipping execution");
			return;
		}

		Assert.notNull(service, "service is null");
		Assert.notNull(context, "context is null");

		ExecutionResult result = service.executeSql(context);
		String elapsed = FormatUtils.getTime(result.getElapsed());
		String updates = FormatUtils.getCount(result.getUpdateCount());
		logger.info("Rows updated: {}  Total time: {}", updates, elapsed);
	}

	public JdbcService getService() {
		return service;
	}

	public void setService(JdbcService service) {
		this.service = service;
	}

	public JdbcContext getContext() {
		return context;
	}

	public void setContext(JdbcContext context) {
		this.context = context;
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

}
