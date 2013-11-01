package org.kuali.common.aws.ec2.util;

import org.kuali.common.aws.ec2.model.EC2ServiceContext;
import org.kuali.common.aws.ec2.model.LaunchInstanceContext;
import org.kuali.common.util.Assert;
import org.kuali.common.util.execute.Executable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShowLaunchConfigExecutable implements Executable {

	private static final Logger logger = LoggerFactory.getLogger(ShowLaunchConfigExecutable.class);

	private final EC2ServiceContext service;
	private final LaunchInstanceContext instance;
	private final boolean skip;

	public ShowLaunchConfigExecutable(EC2ServiceContext service, LaunchInstanceContext instance) {
		this(service, instance, false);
	}

	public ShowLaunchConfigExecutable(EC2ServiceContext service, LaunchInstanceContext instance, boolean skip) {
		Assert.noNulls(service, instance);
		this.service = service;
		this.instance = instance;
		this.skip = skip;
	}

	@Override
	public void execute() {
		if (!skip) {
			logger.info("AWS Access Key ID: {}", service.getCredentials().getAWSAccessKeyId());
		}
	}

	public EC2ServiceContext getService() {
		return service;
	}

	public LaunchInstanceContext getInstance() {
		return instance;
	}
}
