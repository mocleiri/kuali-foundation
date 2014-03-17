package org.kuali.common.aws.ec2;

import static org.kuali.common.aws.ec2.model.Regions.US_WEST_1;
import static org.kuali.common.util.log.Loggers.newLogger;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.kuali.common.aws.Credentials;
import org.kuali.common.aws.ec2.impl.DefaultEC2Service;
import org.kuali.common.aws.ec2.model.EC2ServiceContext;
import org.kuali.common.aws.status.Auth;
import org.kuali.common.util.wait.DefaultWaitService;
import org.slf4j.Logger;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.ec2.model.EbsInstanceBlockDevice;
import com.amazonaws.services.ec2.model.Image;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.InstanceBlockDeviceMapping;

public class DefaultEC2ServiceTest {

	private static final Logger logger = newLogger();

	@Test
	public void testCreateAmi() {
		try {
			DefaultEC2Service service = getUSWestService();
			Instance instance = service.getInstance("i-ef0455b0");
			List<InstanceBlockDeviceMapping> mappings = instance.getBlockDeviceMappings();
			info("ram disk id: %s", instance.getRamdiskId());
			for (InstanceBlockDeviceMapping mapping : mappings) {
				EbsInstanceBlockDevice eibd = mapping.getEbs();
				info("device: %s  volume: %s", mapping.getDeviceName(), eibd.getVolumeId());
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	@Test
	@Ignore
	public void testGetAllImages() {
		DefaultEC2Service service = getEC2Service();
		List<Image> images = service.getMyImages();
		info("images: %s", images.size());
	}

	private static final DefaultEC2Service getUSWestService() {
		AWSCredentials credentials = Auth.getCredentials(Credentials.FOUNDATION);
		EC2ServiceContext context = EC2ServiceContext.builder(credentials).withRegion(US_WEST_1).build();
		return new DefaultEC2Service(context, new DefaultWaitService());
	}

	private static final DefaultEC2Service getEC2Service() {
		AWSCredentials credentials = Auth.getCredentials(Credentials.FOUNDATION);
		EC2ServiceContext context = EC2ServiceContext.create(credentials);
		return new DefaultEC2Service(context, new DefaultWaitService());
	}

	private final void info(String msg, Object... args) {
		if (args == null || args.length == 0) {
			logger.info(msg);
		} else {
			logger.info(String.format(msg, args));
		}
	}

}
