package org.kuali.common.aws.ec2.util;

import java.util.Map;

import org.kuali.common.aws.ec2.model.EC2ServiceContext;
import org.kuali.common.aws.ec2.model.LaunchInstanceContext;
import org.kuali.common.aws.model.Regions;
import org.kuali.common.util.Assert;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.nullify.NullUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Optional;

public class ShowLaunchConfigExecutable implements Executable {

	private static final Logger logger = LoggerFactory.getLogger(ShowLaunchConfigExecutable.class);

	private final EC2ServiceContext serviceContext;
	private final LaunchInstanceContext instanceContext;
	private final boolean skip;

	public ShowLaunchConfigExecutable(EC2ServiceContext serviceContext, LaunchInstanceContext instanceContext) {
		this(serviceContext, instanceContext, false);
	}

	public ShowLaunchConfigExecutable(EC2ServiceContext serviceContext, LaunchInstanceContext instanceContext, boolean skip) {
		Assert.noNulls(serviceContext, instanceContext);
		this.serviceContext = serviceContext;
		this.instanceContext = instanceContext;
		this.skip = skip;
	}

	@Override
	public void execute() {
		if (!skip) {
			String accessKey = serviceContext.getCredentials().getAWSAccessKeyId();
			String regionName = getRegionName(serviceContext);
			String regionLocation = getRegionLocation(serviceContext);
			String availabilityZone = getAvailabilityZone(instanceContext);

			logger.info("---------- Launching EC2 Instance ----------");
			logger.info("Access Key: {}", accessKey);
			logger.info("Location: {}", regionLocation);
			logger.info("Region: {}", regionName);
			logger.info("Zone: {}", availabilityZone);
			logger.info("AMI: {}", instanceContext.getAmi());
			logger.info("Type: {}", instanceContext.getType().toString());
			logger.info("Key: {}", instanceContext.getKeyName());
			logger.info("--------------------------------------------");
		}
	}

	protected String getAvailabilityZone(LaunchInstanceContext context) {
		Optional<String> zone = context.getAvailabilityZone();
		if (!zone.isPresent() || NullUtils.trimToNull(zone.get()) == null) {
			return "no preference";
		} else {
			return zone.get();
		}
	}

	protected String getRegionLocation(EC2ServiceContext context) {
		Optional<String> regionName = context.getRegionName();
		if (!regionName.isPresent()) {
			return Regions.DEFAULT_REGION.getLocation();
		}
		Map<String, Regions> map = Regions.asMap();
		Regions region = map.get(regionName.get());
		if (region == null) {
			// They've supplied a region we don't know about
			return "unknown";
		} else {
			return region.getLocation();
		}
	}

	protected String getRegionName(EC2ServiceContext context) {
		Optional<String> regionName = context.getRegionName();
		if (regionName.isPresent()) {
			return regionName.get();
		} else {
			return Regions.DEFAULT_REGION.getName();
		}
	}

	public EC2ServiceContext getServiceContext() {
		return serviceContext;
	}

	public LaunchInstanceContext getInstanceContext() {
		return instanceContext;
	}

	public boolean isSkip() {
		return skip;
	}

}
