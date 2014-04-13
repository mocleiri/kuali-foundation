package org.kuali.common.deploy;

import org.kuali.common.util.Assert;
import org.kuali.common.util.execute.Executable;

public class DeployExecutable implements Executable {

	private final boolean skip;

	public DeployExecutable(DeployService service) {
		this(service, false);
	}

	public DeployExecutable(DeployService service, boolean skip) {
		Assert.noNulls(service);
		this.service = service;
		this.skip = skip;
	}

	private final DeployService service;

	@Override
	public void execute() {

		if (skip) {
			return;
		}

		service.deploy();
	}

	public DeployService getService() {
		return service;
	}

}
