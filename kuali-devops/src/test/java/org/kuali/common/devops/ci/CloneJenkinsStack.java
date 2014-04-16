package org.kuali.common.devops.ci;

import static com.google.common.base.Optional.fromNullable;
import static java.lang.String.format;
import static org.apache.commons.lang3.StringUtils.equalsIgnoreCase;
import static org.kuali.common.util.base.Exceptions.illegalState;
import static org.kuali.common.util.log.Loggers.newLogger;
import static org.kuali.common.util.nullify.NullUtils.trimToNull;

import org.junit.Test;
import org.kuali.common.core.system.VirtualSystem;
import org.kuali.common.devops.aws.Tags.Stack;
import org.slf4j.Logger;

import com.google.common.base.Optional;

public class CloneJenkinsStack {

	private static final Logger logger = newLogger();

	@Test
	public void test() throws Exception {
		VirtualSystem vs = VirtualSystem.create();
		boolean quiet = equalsIgnoreCase(vs.getProperties().getProperty("ec2.quiet"), "false") ? false : true;
		CloneJenkinsStackContext context = getJenkinsCloneContext(vs);

	}

	private static CloneJenkinsStackContext getJenkinsCloneContext(VirtualSystem vs) {
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
