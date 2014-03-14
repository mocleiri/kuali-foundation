package org.kuali.common.devops.ci;

import static com.google.common.base.Stopwatch.createStarted;
import static com.google.common.collect.Lists.newArrayList;
import static java.lang.String.format;
import static org.kuali.common.devops.aws.NamedSecurityGroups.CI;
import static org.kuali.common.devops.ci.CreateBuildSlaveAMI.getBasicLaunchRequest;
import static org.kuali.common.devops.ci.CreateBuildSlaveAMI.getEC2Service;
import static org.kuali.common.util.log.Loggers.newLogger;

import java.util.List;

import org.junit.Test;
import org.kuali.common.aws.ec2.api.EC2Service;
import org.kuali.common.aws.ec2.model.security.KualiSecurityGroup;
import org.kuali.common.core.system.VirtualSystem;
import org.kuali.common.devops.aws.NamedSecurityGroups;
import org.kuali.common.devops.aws.Tags;
import org.kuali.common.util.FormatUtils;
import org.slf4j.Logger;

import com.amazonaws.services.ec2.model.Instance;
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
		BasicLaunchRequest request = getMasterLaunchRequest();

		EC2Service service = getEC2Service(amazonAccount);
		// Instance instance = launchAndWait(service, request, securityGroups, tags);
		Instance instance = service.getInstance("i-d912d0fa");
		logger.info(format("public dns: %s", instance.getPublicDnsName()));
	}

	protected static BasicLaunchRequest getMasterLaunchRequest() {
		BasicLaunchRequest.Builder builder = BasicLaunchRequest.builder();
		builder.setTimeoutMillis(FormatUtils.getMillisAsInt("15m"));
		return getBasicLaunchRequest(builder.build());
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
