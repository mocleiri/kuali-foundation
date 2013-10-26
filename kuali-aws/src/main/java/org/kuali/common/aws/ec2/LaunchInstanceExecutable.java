package org.kuali.common.aws.ec2;

import org.kuali.common.aws.ec2.api.EC2Service;
import org.kuali.common.aws.ec2.model.LaunchInstanceContext;
import org.kuali.common.util.Assert;
import org.kuali.common.util.execute.Executable;

public class LaunchInstanceExecutable implements Executable {

	private final EC2Service service;
	private final LaunchInstanceContext context;
	private final boolean skip;

	public LaunchInstanceExecutable(EC2Service service, LaunchInstanceContext request) {
		this(service, request, false);
	}

	public LaunchInstanceExecutable(EC2Service service, LaunchInstanceContext request, boolean skip) {
		Assert.noNulls(service, request);
		this.service = service;
		this.context = request;
		this.skip = skip;
	}

	@Override
	public void execute() {
		if (!skip) {
			service.launchInstance(context);
		}
	}

	public EC2Service getService() {
		return service;
	}

	public LaunchInstanceContext getContext() {
		return context;
	}

}
