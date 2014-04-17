package org.kuali.common.devops.ci;

import static com.google.common.base.Optional.fromNullable;
import static java.lang.String.format;
import static org.kuali.common.devops.ci.CreateBuildSlaveAMI.US_REGIONS;
import static org.kuali.common.devops.ci.CreateBuildSlaveAMI.cleanupAmis;
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
import org.kuali.common.aws.ec2.model.ImmutableTag;
import org.kuali.common.core.system.VirtualSystem;
import org.kuali.common.devops.aws.Tags;
import org.kuali.common.devops.aws.Tags.Stack;
import org.kuali.common.devops.ci.model.CloneJenkinsStackContext;
import org.slf4j.Logger;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.ec2.model.Image;
import com.amazonaws.services.ec2.model.Tag;
import com.google.common.base.Optional;

public class CloneJenkinsStack {

	private static final Logger logger = newLogger();

	@Test
	public void test() throws Exception {
		try {
			System.setProperty("ec2.stack.src", "test");
			System.setProperty("ec2.stack.dst", "prod");
			System.setProperty("ec2.ami.region.src", "us-west-1");
			VirtualSystem vs = VirtualSystem.create();
			CloneJenkinsStackContext context = getCloneJenkinsStackContext(vs);
			AWSCredentials creds = getAwsCredentials(KUALI_FOUNDATION_ACCOUNT);
			EC2Service service = new DefaultEC2Service(creds, context.getRegion().getName());
			String ami = getMostRecentAMI(service, context.getSrcStack().getTag(), Tags.Name.SLAVE.getTag());
			Image image = service.getImage(ami);
			circulateAMI(context.getRegion().getName(), US_REGIONS, image, context.getDstStack().getTag());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void circulateAMI(String srcRegion, Set<String> regions, Image ami, Tag stack) {
		String copiedAmi = null;
		String copiedRegion = null;
		for (String dstRegion : regions) {
			if (!dstRegion.equals(srcRegion)) {
				copiedAmi = copyAMI(srcRegion, ami.getImageId(), ami.getName(), dstRegion, stack);
				copiedRegion = dstRegion;
			}
		}
		copyAMI(copiedRegion, copiedAmi, getNewName(ami.getName(), stack.getValue()), srcRegion, stack);
	}

	protected String getNewName(String amiName, String stack) {
		int pos = amiName.lastIndexOf("-");
		return amiName.substring(0, pos) + "-" + stack;
	}

	protected String copyAMI(String srcRegion, String ami, String amiName, String dstRegion, Tag stack) {
		String newName = getNewName(amiName, stack.getValue());
		EC2Service service = new DefaultEC2Service(getAwsCredentials(KUALI_FOUNDATION_ACCOUNT), dstRegion);
		info("copying [%s] from [%s] to [%s] as [%s]", amiName, srcRegion, dstRegion, newName);
		String newAmi = service.copyAmi(srcRegion, ami, newName);
		service.tag(newAmi, stack);
		service.tag(newAmi, new ImmutableTag(Tags.Name.SLAVE.getTag().getKey(), newName));
		cleanupAmis(service, stack, 7);
		return newAmi;
	}

	private static CloneJenkinsStackContext getCloneJenkinsStackContext(VirtualSystem vs) {
		Stack src = Stack.valueOf(getRequiredProperty(vs, "ec2.stack.src").toUpperCase());
		Stack dst = Stack.valueOf(getRequiredProperty(vs, "ec2.stack.dst").toUpperCase());
		String region = getRequiredProperty(vs, "ec2.ami.region.src");
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
