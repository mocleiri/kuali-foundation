package org.kuali.common.http;

import org.kuali.common.util.Assert;
import org.kuali.common.util.execute.Executable;

public class HttpWaitExecutable implements Executable {

	HttpContext context;
	HttpService service = new DefaultHttpService();
	HttpStatus expected = HttpStatus.SUCCESS;

	@Override
	public void execute() {
		HttpWaitResult result = service.wait(context);
		HttpStatus actual = result.getStatus();
		Assert.isTrue(expected.equals(actual), "Expected status is [" + expected + "] but actual status is [" + actual + "]");
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

	public HttpStatus getExpected() {
		return expected;
	}

	public void setExpected(HttpStatus expected) {
		this.expected = expected;
	}

}
