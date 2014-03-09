package org.kuali.common.devops.ci;

import static org.kuali.common.util.log.Loggers.newLogger;

import java.util.List;

import org.junit.Test;
import org.kuali.common.aws.KeyPairBuilders;
import org.kuali.common.aws.ec2.api.EC2Service;
import org.kuali.common.aws.ec2.impl.DefaultEC2Service;
import org.kuali.common.aws.ec2.model.EC2ServiceContext;
import org.kuali.common.aws.ec2.model.LaunchInstanceContext;
import org.kuali.common.aws.ec2.model.RootVolume;
import org.kuali.common.aws.ec2.model.security.KualiSecurityGroup;
import org.kuali.common.core.ssh.KeyPair;
import org.kuali.common.devops.aws.NamedSecurityGroup;
import org.kuali.common.devops.aws.Tags;
import org.kuali.common.devops.logic.Auth;
import org.kuali.common.util.wait.DefaultWaitService;
import org.kuali.common.util.wait.WaitService;
import org.slf4j.Logger;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.InstanceType;
import com.amazonaws.services.ec2.model.Tag;
import com.google.common.collect.ImmutableList;

public class CreateBuildSlaveAMI {

	private static final Logger logger = newLogger();
	private final String ami = "ami-83dee0ea";
	private final InstanceType type = InstanceType.C3Xlarge;
	private final RootVolume rootVolume = RootVolume.builder().withDeleteOnTermination(true).withSizeInGigabytes(64).build();
	private final List<KualiSecurityGroup> securityGroups = ImmutableList.of(NamedSecurityGroup.CI.getGroup(), NamedSecurityGroup.CI_BUILD_SLAVE.getGroup());
	private final List<Tag> tags = ImmutableList.of(Tags.Name.SLAVE.getTag(), Tags.Team.DEVOPS.getTag(), Tags.Stack.TESTING.getTag());

	@Test
	public void test() {
		try {
			EC2Service service = getEC2Service();
			KeyPair keyPair = Auth.getKeyPair(KeyPairBuilders.FOUNDATION.getBuilder());
			LaunchInstanceContext context = LaunchInstanceContext.builder(ami, keyPair).withType(type).withRootVolume(rootVolume).withSecurityGroups(securityGroups).withTags(tags)
					.build();
			Instance instance = service.launchInstance(context);
			logger.info(String.format("public dns: %s", instance.getPublicDnsName()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected EC2Service getEC2Service() {
		AWSCredentials creds = Auth.getAwsCredentials("foundation");
		WaitService ws = new DefaultWaitService();
		EC2ServiceContext ec = EC2ServiceContext.create(creds);
		return new DefaultEC2Service(ec, ws);
	}

}
