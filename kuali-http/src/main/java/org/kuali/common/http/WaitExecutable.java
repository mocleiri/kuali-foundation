package org.kuali.common.http;

import org.kuali.common.util.execute.Executable;

public class WaitExecutable implements Executable {

	HttpContext context;
	HttpService service = new DefaultHttpService();

	@Override
	public void execute() {
		WaitResult result = service.wait(context);
	}

	public HttpContext getContext() {
		return context;
	}

	public void setContext(HttpContext context) {
		this.context = context;
	}

	public HttpService getService() {
		return service;
	}

	public void setService(HttpService service) {
		this.service = service;
	}

}
