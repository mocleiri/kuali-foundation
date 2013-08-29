package org.kuali.common.deploy;

import org.kuali.common.util.Assert;
import org.kuali.common.util.execute.Executable;

public class DeployExecutable implements Executable {

	public DeployExecutable(DeployService service) {
		Assert.noNulls(service);
		this.service = service;
	}

	private final DeployService service;

	@Override
	public void execute() {
		service.deploy();
	}

	public DeployService getService() {
		return service;
	}

}
