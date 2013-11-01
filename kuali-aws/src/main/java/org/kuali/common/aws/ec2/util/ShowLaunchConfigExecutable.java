package org.kuali.common.aws.ec2.util;

import java.util.Map;

import org.kuali.common.aws.ec2.model.EC2ServiceContext;
import org.kuali.common.aws.ec2.model.LaunchInstanceContext;
import org.kuali.common.aws.model.AvailabilityZones;
import org.kuali.common.aws.model.Regions;
import org.kuali.common.util.Assert;
import org.kuali.common.util.execute.Executable;
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
			String availabilityZone = AvailabilityZones.NO_PREFERENCE.getName();
			if (instanceContext.getAvailabilityZone().isPresent()) {
				availabilityZone = instanceContext.getAvailabilityZone().get();
			}
			logger.info("AWS Access Key ID: {}", accessKey);
			logger.info("Region: [{} {}]", regionName, regionLocation);
			logger.info("Availabilty Zone: {}", availabilityZone);
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
			return Regions.DEFAULT_REGION.getLocation();
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
