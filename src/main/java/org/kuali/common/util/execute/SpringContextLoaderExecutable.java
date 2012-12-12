package org.kuali.common.util.execute;

import org.kuali.common.util.service.SpringService;
import org.kuali.common.util.spring.SpringContext;

public class SpringContextLoaderExecutable implements Executable {

	SpringService service;
	SpringContext context;

	@Override
	public void execute() {
		service.load(context);
	}

	public SpringService getService() {
		return service;
	}

	public void setService(SpringService service) {
		this.service = service;
	}

	public SpringContext getContext() {
		return context;
	}

	public void setContext(SpringContext context) {
		this.context = context;
	}

}
