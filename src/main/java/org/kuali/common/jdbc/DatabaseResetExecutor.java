package org.kuali.common.jdbc;

import org.kuali.common.jdbc.DatabaseService;
import org.kuali.common.jdbc.context.DatabaseResetContext;
import org.kuali.common.util.execute.Executable;
import org.springframework.util.Assert;

public class DatabaseResetExecutor implements Executable {

	DatabaseService service;
	DatabaseResetContext context;

	@Override
	public void execute() {
		Assert.notNull(context, "context is null");
		Assert.notNull(service, "service is null");
		service.reset(context);
	}

	public DatabaseService getService() {
		return service;
	}

	public void setService(DatabaseService service) {
		this.service = service;
	}

	public DatabaseResetContext getContext() {
		return context;
	}

	public void setContext(DatabaseResetContext context) {
		this.context = context;
	}

}
