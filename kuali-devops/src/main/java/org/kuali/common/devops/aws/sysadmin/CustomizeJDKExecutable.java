package org.kuali.common.devops.aws.sysadmin;

import org.kuali.common.util.Assert;
import org.kuali.common.util.channel.api.SecureChannel;
import org.kuali.common.util.channel.util.ChannelExecutable;
import org.kuali.common.util.channel.util.ChannelUtils;

/**
 * Customize the JDK install as needed.
 */
public final class CustomizeJDKExecutable implements ChannelExecutable {

	public CustomizeJDKExecutable(String jdkDir) {
		this(jdkDir, false);
	}

	public CustomizeJDKExecutable(String jdkDir, boolean skip) {
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

		// Make sure everything in bin and jre/bin is executable
		String dir1 = jdkDir + "/bin";
		String dir2 = jdkDir + "/jre/bin";
		String command1 = "chmod -R 755 " + dir1 + " " + dir2;

		// AppDynamics needs a copy of tools.jar in jre/lib/ext
		String src = jdkDir + "/lib/tools.jar";
		String dst = jdkDir + "/jre/lib/ext/tools.jar";
		String command2 = "cp " + src + " " + dst;

		ChannelUtils.exec(channel, command1, command2);
	}

	public boolean isSkip() {
		return skip;
	}

	public String getJdkDir() {
		return jdkDir;
	}

}
