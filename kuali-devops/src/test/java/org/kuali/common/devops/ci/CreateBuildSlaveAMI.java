package org.kuali.common.devops.ci;

import static com.amazonaws.services.ec2.model.InstanceType.C3Xlarge;
import static com.google.common.base.Optional.fromNullable;
import static com.google.common.base.Preconditions.checkState;
import static com.google.common.collect.Lists.newArrayList;
import static java.lang.String.format;
import static org.kuali.common.devops.aws.NamedSecurityGroups.CI;
import static org.kuali.common.devops.aws.NamedSecurityGroups.CI_BUILD_SLAVE;
import static org.kuali.common.util.log.Loggers.newLogger;

import java.net.URL;
import java.security.CodeSource;
import java.util.List;

import org.junit.Test;
import org.kuali.common.aws.ec2.api.EC2Service;
import org.kuali.common.aws.ec2.impl.DefaultEC2Service;
import org.kuali.common.aws.ec2.model.EC2ServiceContext;
import org.kuali.common.aws.ec2.model.LaunchInstanceContext;
import org.kuali.common.aws.ec2.model.RootVolume;
import org.kuali.common.aws.ec2.model.security.KualiSecurityGroup;
import org.kuali.common.core.ssh.KeyPair;
import org.kuali.common.devops.aws.KeyPairBuilders;
import org.kuali.common.devops.aws.Tags;
import org.kuali.common.devops.logic.Auth;
import org.kuali.common.dns.api.DnsService;
import org.kuali.common.dns.dnsme.model.DNSMadeEasyCredentials;
import org.kuali.common.util.wait.DefaultWaitService;
import org.kuali.common.util.wait.WaitService;
import org.slf4j.Logger;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.InstanceType;
import com.amazonaws.services.ec2.model.Tag;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

public class CreateBuildSlaveAMI {

	private static final Logger logger = newLogger();
	private final String ami = "ami-83dee0ea";
	private final InstanceType type = C3Xlarge;
	private final RootVolume rootVolume = RootVolume.create(64, true);
	private final List<KualiSecurityGroup> securityGroups = ImmutableList.of(CI.getGroup(), CI_BUILD_SLAVE.getGroup());
	private final List<Tag> tags = getTags();
	private final String subdomain = "slave.ci";
	private final String domain = "kuali.org";

	@Test
	public void test() {
		try {
			// Instance instance = getNewSlaveInstance();
			// Instance instance = getRunningSlaveInstance("i-385fa21b");
			// logger.info(format("public dns: %s", instance.getPublicDnsName()));
			// Optional<CodeSource> src = fromNullable(KualiDevOpsProjectConstants.class.getProtectionDomain().getCodeSource());
			Optional<CodeSource> src = fromNullable(Optional.class.getProtectionDomain().getCodeSource());
			checkState(src.isPresent(), "could not get code source");
			URL url = src.get().getLocation();
			logger.info(format("url: [%s]", url));
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	protected DnsService getDnsService() {
		DNSMadeEasyCredentials credentials = Auth.getDnsmeCredentials();
		DnsService service = null;// new DNSMadeEasyServiceContext(credentials, restApiUrl, domainName)
		return null;
	}

	protected List<Tag> getTags() {
		List<Tag> tags = newArrayList();
		// TODO Change this to PRODUCTION when ready
		tags.add(Tags.Stack.TESTING.getTag());
		tags.add(Tags.Name.SLAVE.getTag());
		tags.add(Tags.Team.DEVOPS.getTag());
		tags.add(Tags.Vendor.JENKINS.getTag());
		tags.add(Tags.Project.SHARED.getTag());
		return ImmutableList.copyOf(tags);
	}

	protected Instance getRunningSlaveInstance(String instanceId) {
		EC2Service service = getEC2Service();
		return service.getInstance(instanceId);
	}

	protected Instance getNewSlaveInstance() {
		EC2Service service = getEC2Service();
		KeyPair keyPair = Auth.getKeyPair(KeyPairBuilders.FOUNDATION.getBuilder());
		LaunchInstanceContext context = LaunchInstanceContext.builder(ami, keyPair).withType(type).withRootVolume(rootVolume).withSecurityGroups(securityGroups).withTags(tags)
				.build();
		return service.launchInstance(context);
	}

	protected EC2Service getEC2Service() {
		AWSCredentials creds = Auth.getAwsCredentials("foundation");
		WaitService ws = new DefaultWaitService();
		EC2ServiceContext ec = EC2ServiceContext.create(creds);
		return new DefaultEC2Service(ec, ws);
	}

}
