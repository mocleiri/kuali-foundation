package org.kuali.common.devops.ci;

import static com.google.common.base.Stopwatch.createStarted;
import static com.google.common.collect.Lists.newArrayList;
import static java.lang.Integer.parseInt;
import static java.lang.String.format;
import static org.kuali.common.devops.aws.NamedSecurityGroups.CI;
import static org.kuali.common.devops.ci.CreateBuildSlaveAMI.getEC2Service;
import static org.kuali.common.devops.ci.CreateBuildSlaveAMI.launchAndWait;
import static org.kuali.common.util.FormatUtils.getMillisAsInt;
import static org.kuali.common.util.log.Loggers.newLogger;

import java.util.List;

import org.junit.Test;
import org.kuali.common.aws.ec2.api.EC2Service;
import org.kuali.common.aws.ec2.model.AMI;
import org.kuali.common.aws.ec2.model.RootVolume;
import org.kuali.common.aws.ec2.model.security.KualiSecurityGroup;
import org.kuali.common.core.system.VirtualSystem;
import org.kuali.common.devops.aws.NamedSecurityGroups;
import org.kuali.common.devops.aws.Tags;
import org.slf4j.Logger;

import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.InstanceType;
import com.amazonaws.services.ec2.model.Tag;
import com.google.common.base.Stopwatch;
import com.google.common.collect.ImmutableList;

public class SpinUpJenkinsMaster {

	private static final Logger logger = newLogger();

	private final Stopwatch sw = createStarted();
	private final VirtualSystem vs = VirtualSystem.create();
	private final List<Tag> tags = getMasterTags();
	private final List<KualiSecurityGroup> securityGroups = ImmutableList.of(CI.getGroup(), NamedSecurityGroups.CI_MASTER.getGroup());
	private final String gpgPassphrase = "coSLMPP2IsSAXYVp9NIsvxzqAkd7N+Yh";
	private final String amazonAccount = "foundation";

	// TODO Change this to 256 when ready
	private final int defaultRootVolumeSize = 32;

	@Test
	public void test() {
		// Configurable items
		String ami = System.getProperty("master.ami", AMI.UBUNTU_64_BIT_PRECISE_LTS_1204.getId());
		InstanceType type = InstanceType.fromValue(System.getProperty("master.type", InstanceType.C3Xlarge.toString()));
		RootVolume rootVolume = RootVolume.create(parseInt(System.getProperty("master.size", defaultRootVolumeSize + "")), true);
		// The amount of time to wait before timing out on instance creation
		int timeoutMillis = getMillisAsInt(System.getProperty("master.timeout", "1h"));

		EC2Service service = getEC2Service(amazonAccount);
		Instance instance = launchAndWait(service, ami, type, rootVolume, timeoutMillis, securityGroups, tags);
		// Instance instance = getRunningSlaveInstance(service, "i-d912d0fa");
		logger.info(format("public dns: %s", instance.getPublicDnsName()));
	}

	protected static List<Tag> getMasterTags() {
		List<Tag> tags = newArrayList();
		tags.addAll(CreateBuildSlaveAMI.getTags());
		tags.add(Tags.Name.MASTER.getTag());
		// TODO Remove this when things are ready
		tags.add(new Tag("Name", "ci.master.jeff"));
		return ImmutableList.copyOf(tags);
	}

}
