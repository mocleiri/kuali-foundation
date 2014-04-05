/**
 * Copyright 2004-2014 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.common.aws.ec2;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Stopwatch.createStarted;
import static java.lang.String.format;
import static java.lang.System.currentTimeMillis;
import static org.kuali.common.aws.ec2.model.ImmutableBlockDeviceMapping.INSTANCE_STORE_0;
import static org.kuali.common.aws.ec2.model.ImmutableBlockDeviceMapping.INSTANCE_STORE_1;
import static org.kuali.common.aws.ec2.model.Regions.US_WEST_1;
import static org.kuali.common.aws.ec2.model.security.NamedPermissions.ALLOW_SSH_FROM_ANYWHERE;
import static org.kuali.common.util.log.Loggers.newLogger;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.kuali.common.aws.Credentials;
import org.kuali.common.aws.KeyPairBuilders;
import org.kuali.common.aws.ec2.impl.DefaultEC2Service;
import org.kuali.common.aws.ec2.model.CreateAMIRequest;
import org.kuali.common.aws.ec2.model.EC2ServiceContext;
import org.kuali.common.aws.ec2.model.ImmutableBlockDeviceMapping;
import org.kuali.common.aws.ec2.model.ImmutableTag;
import org.kuali.common.aws.ec2.model.LaunchInstanceContext;
import org.kuali.common.aws.ec2.model.RootVolume;
import org.kuali.common.aws.ec2.model.security.KualiSecurityGroup;
import org.kuali.common.aws.status.Auth;
import org.kuali.common.core.ssh.KeyPair;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.wait.DefaultWaitService;
import org.slf4j.Logger;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.BlockDeviceMapping;
import com.amazonaws.services.ec2.model.CopyImageRequest;
import com.amazonaws.services.ec2.model.CopyImageResult;
import com.amazonaws.services.ec2.model.EbsBlockDevice;
import com.amazonaws.services.ec2.model.Image;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.InstanceType;
import com.amazonaws.services.ec2.model.Tag;
import com.google.common.base.Optional;
import com.google.common.base.Stopwatch;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

public class DefaultEC2ServiceTest {

	private static final Logger logger = newLogger();

	@Test
	public void testCopyAMI() {
		try {
			Stopwatch sw = createStarted();
			AWSCredentials credentials = Auth.getCredentials(Credentials.FOUNDATION);
			AmazonEC2Client client = new AmazonEC2Client(credentials);
			Region dst = Region.getRegion(Regions.US_WEST_2);
			Region src = Region.getRegion(Regions.US_EAST_1);
			String ami = "ami-7dada414";
			client.setRegion(dst);
			CopyImageRequest request = new CopyImageRequest();
			request.setSourceRegion(src.getName());
			request.setSourceImageId(ami);
			logger.info(format("copying [%s] from %s to %s", ami, src, dst));
			CopyImageResult result = client.copyImage(request);
			String resultAmi = result.getImageId();
			logger.info(format("copied [%s] from %s to %s - %s", ami, src, dst, FormatUtils.getTime(sw)));
			logger.info(format("resultAmi [%s]", resultAmi));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	@Ignore
	public void testLaunchWithSSD() {
		try {
			DefaultEC2Service service = getUSWestService();
			KeyPair keyPair = Auth.getKeyPair(KeyPairBuilders.FOUNDATION);
			List<Tag> tags = ImmutableList.<Tag> of(new ImmutableTag("Name", String.format("ci.slave.ssd.%s", currentTimeMillis() / (1000 * 60))));
			List<BlockDeviceMapping> additionalMappings = ImmutableList.<BlockDeviceMapping> of(INSTANCE_STORE_0, INSTANCE_STORE_1);
			InstanceType type = InstanceType.C3Xlarge;
			KualiSecurityGroup ksg = KualiSecurityGroup.builder("ci").addPermission(ALLOW_SSH_FROM_ANYWHERE.getPermission()).build();
			LaunchInstanceContext context = LaunchInstanceContext.builder("ami-709ba735", keyPair).withSecurityGroup(ksg).withType(type).withAdditionalMappings(additionalMappings)
					.withTags(tags).build();
			service.launchInstance(context);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	@Test
	@Ignore
	public void testCreateAmi() {
		try {
			DefaultEC2Service service = getUSWestService();
			Instance instance = service.getInstance("i-4423721b");
			ImmutableTag name = new ImmutableTag("Name", "test.slave.ssd." + (currentTimeMillis() / (1000 * 60)));
			RootVolume rootVolume = RootVolume.create(32, true);
			int timeoutMillis = FormatUtils.getMillisAsInt("1h");
			List<BlockDeviceMapping> additionalMappings = Lists.<BlockDeviceMapping> newArrayList(SSD);
			CreateAMIRequest request = CreateAMIRequest.builder().withInstanceId(instance.getInstanceId()).withName(name).withRootVolume(rootVolume)
					.withAdditionalMappings(additionalMappings).withTimeoutMillis(timeoutMillis).withDescription("ssd test").build();
			service.createAmi(request);
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

	private static final String SSD_DEVICE_NAME = "/dev/sdb";
	private static final String SSD_VIRTUAL_NAME = "ephemeral0";
	private static final Optional<EbsBlockDevice> NO_EBS = absent();
	private static final Optional<String> ABSENT = absent();

	public static final ImmutableBlockDeviceMapping SSD = new ImmutableBlockDeviceMapping(SSD_DEVICE_NAME, NO_EBS, Optional.of(SSD_VIRTUAL_NAME), ABSENT);

}
