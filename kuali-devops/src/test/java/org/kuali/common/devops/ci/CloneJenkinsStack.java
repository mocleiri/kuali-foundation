package org.kuali.common.devops.ci;

import static com.google.common.base.Optional.fromNullable;
import static java.lang.String.format;
import static org.apache.commons.lang3.StringUtils.equalsIgnoreCase;
import static org.kuali.common.devops.ci.UpdateBuildSlaveAMI.getMostRecentAMI;
import static org.kuali.common.devops.ci.model.Constants.KUALI_FOUNDATION_ACCOUNT;
import static org.kuali.common.devops.logic.Auth.getAwsCredentials;
import static org.kuali.common.util.base.Exceptions.illegalState;
import static org.kuali.common.util.log.Loggers.newLogger;
import static org.kuali.common.util.nullify.NullUtils.trimToNull;

import java.util.Set;

import org.junit.Test;
import org.kuali.common.aws.ec2.api.EC2Service;
import org.kuali.common.aws.ec2.impl.DefaultEC2Service;
import org.kuali.common.core.system.VirtualSystem;
import org.kuali.common.devops.aws.Tags;
import org.kuali.common.devops.aws.Tags.Stack;
import org.kuali.common.devops.ci.model.CloneJenkinsStackContext;
import org.slf4j.Logger;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.ec2.model.Tag;
import com.google.common.base.Optional;

public class CloneJenkinsStack {

	private static final Logger logger = newLogger();

	@Test
	public void test() throws Exception {
		VirtualSystem vs = VirtualSystem.create();
		boolean quiet = equalsIgnoreCase(vs.getProperties().getProperty("ec2.quiet"), "false") ? false : true;
		CloneJenkinsStackContext context = getCloneJenkinsStackContext(vs);
		AWSCredentials creds = getAwsCredentials(KUALI_FOUNDATION_ACCOUNT);
		EC2Service service = new DefaultEC2Service(creds, context.getRegion().getName());
		String ami = getMostRecentAMI(service, context.getDstStack().getTag(), Tags.Name.SLAVE.getTag());
	}

	protected void copyAmi(String sourceRegion, Set<String> regions, String ami, Tag stack) {
		for (String region : regions) {
			if (!region.equals(sourceRegion)) {
				EC2Service service = new DefaultEC2Service(getAwsCredentials(KUALI_FOUNDATION_ACCOUNT), region);
				String copiedAmi = service.copyAmi(sourceRegion, ami);
				service.tag(copiedAmi, stack);
			}
		}
	}

	private static CloneJenkinsStackContext getCloneJenkinsStackContext(VirtualSystem vs) {
		Stack src = Stack.valueOf(getRequiredProperty(vs, "ec2.stack.src"));
		Stack dst = Stack.valueOf(getRequiredProperty(vs, "ec2.stack.dst"));
		String region = getRequiredProperty(vs, "ec2.ami.region");
		return new CloneJenkinsStackContext.Builder().withDstStack(dst).withSrcStack(src).withRegion(region).build();
	}

	private static String getRequiredProperty(VirtualSystem vs, String key) {
		Optional<String> property = fromNullable(trimToNull(vs.getProperties().getProperty(key)));
		if (!property.isPresent()) {
			throw illegalState("[%s] is required", key);
		} else {
			return property.get();
		}
	}

	private static void info(String msg, Object... args) {
		if (args == null) {
			logger.info(msg);
		} else {
			logger.info(format(msg, args));
		}
	}

}
