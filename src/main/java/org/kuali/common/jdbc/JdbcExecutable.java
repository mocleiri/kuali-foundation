package org.kuali.common.jdbc;

import org.kuali.common.jdbc.context.JdbcContext;
import org.kuali.common.util.execute.Executable;
import org.springframework.util.Assert;

public class JdbcExecutable implements Executable {

	JdbcService service;
	JdbcContext context;

	@Override
	public void execute() {
		Assert.notNull(service, "service is null");
		Assert.notNull(context, "context is null");

		service.executeSql(context);
	}

}
