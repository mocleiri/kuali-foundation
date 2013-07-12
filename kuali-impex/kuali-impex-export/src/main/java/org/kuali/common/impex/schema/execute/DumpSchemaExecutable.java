package org.kuali.common.impex.schema.execute;

import org.kuali.common.impex.schema.DumpSchemaService;
import org.kuali.common.util.execute.Executable;
import org.springframework.util.Assert;

public class DumpSchemaExecutable implements Executable {

	DumpSchemaService service = DumpSchemasExecutable.DEFAULT_SERVICE;
	boolean skip = DumpSchemasExecutable.DEFAULT_SKIP_VALUE;

	DumpSchemaRequest request;

	public DumpSchemaExecutable() {
		this(DumpSchemasExecutable.DEFAULT_SERVICE, DumpSchemasExecutable.DEFAULT_SKIP_VALUE, null);
	}

	public DumpSchemaExecutable(DumpSchemaService service, boolean skip, DumpSchemaRequest request) {
		super();
		this.service = service;
		this.skip = skip;
		this.request = request;
	}

	@Override
	public void execute() {

		if (skip) {
			return;
		}

		Assert.notNull(request, "request is null");

		DumpSchemasExecutable exec = new DumpSchemasExecutable(request, service, skip);
		exec.execute();

	}

	public DumpSchemaService getService() {
		return service;
	}

	public void setService(DumpSchemaService service) {
		this.service = service;
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

	public DumpSchemaRequest getRequest() {
		return request;
	}

	public void setRequest(DumpSchemaRequest request) {
		this.request = request;
	}

}
