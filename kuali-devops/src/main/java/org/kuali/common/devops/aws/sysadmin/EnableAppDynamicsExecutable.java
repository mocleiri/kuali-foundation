package org.kuali.common.devops.aws.sysadmin;

import org.kuali.common.util.Assert;
import org.kuali.common.util.channel.api.SecureChannel;
import org.kuali.common.util.channel.util.ChannelExecutable;
import org.kuali.common.util.channel.util.ChannelUtils;

/**
 * AppDynamics needs tools.jar to be in the JRE's lib/ext directory
 */
public final class EnableAppDynamicsExecutable implements ChannelExecutable {

	public EnableAppDynamicsExecutable(String jdkDir) {
		this(jdkDir, false);
	}

	public EnableAppDynamicsExecutable(String jdkDir, boolean skip) {
		Assert.noBlanks(jdkDir);
		this.jdkDir = jdkDir;
		this.skip = skip;
	}

	private final String jdkDir;
	private final boolean skip;

	@Override
	public void execute(SecureChannel channel) {
		if (skip) {
			return;
		}
		String src = jdkDir + "/lib/tools.jar";
		String dst = jdkDir + "/jre/lib/ext/tools.jar";
		String command = "cp " + src + " " + dst;
		ChannelUtils.exec(channel, command);
	}

	public boolean isSkip() {
		return skip;
	}

	public String getJdkDir() {
		return jdkDir;
	}

}
