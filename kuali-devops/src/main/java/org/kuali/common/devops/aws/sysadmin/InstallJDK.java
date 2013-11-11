package org.kuali.common.devops.aws.sysadmin;

import java.io.IOException;

import org.kuali.common.devops.aws.sysadmin.model.InstallZipContext;
import org.kuali.common.util.Assert;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.channel.api.SecureChannel;
import org.kuali.common.util.channel.model.RemoteFile;
import org.kuali.common.util.channel.util.ChannelUtils;
import org.kuali.common.util.execute.Executable;

/**
 * Customize the JDK install as needed.
 */
public final class InstallJDK implements Executable {

	private static final String WARNING = "WARNING: Do not delete or edit this file unless you know exactly what you are doing";

	public InstallJDK(InstallZipContext context) {
		this(context, false);
	}

	public InstallJDK(InstallZipContext context, boolean skip) {
		Assert.noNulls(context);
		this.context = context;
		this.skip = skip;
	}

	private final InstallZipContext context;
	private final boolean skip;

	@Override
	public void execute() {

		if (skip) {
			return;
		}

		String jdkHome = context.getInstallDir();

		// Make sure everything in bin and jre/bin is executable
		String bin = jdkHome + "/bin";
		String jreBin = jdkHome + "/jre/bin";
		String command1 = "chmod -R 755 " + bin + " " + jreBin;

		// AppDynamics needs a copy of tools.jar in jre/lib/ext
		String src = jdkHome + "/lib/tools.jar";
		String dst = jdkHome + "/jre/lib/ext/tools.jar";
		String command2 = "cp " + src + " " + dst;

		SecureChannel channel = null;
		try {
			channel = context.getService().openChannel(context.getContext());
			boolean installed = isInstalled(channel, jdkHome);
			if (!installed) {
				new InstallZip.Builder(context).runAlways(true).build().execute(channel);
				channel.exec(command1);
				channel.exec(command2);
				String content = "jdk customized: " + FormatUtils.getDate(System.currentTimeMillis()) + "\n" + WARNING;
				channel.scpString(content, getJdkCustomizationCompleteFile(jdkHome));
			}
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error", e);
		} finally {
			ChannelUtils.closeQuietly(channel);
		}

	}

	protected RemoteFile getJdkCustomizationCompleteFile(String jdkHome) {
		String absolutePath = jdkHome + "/jdk.customized";
		return new RemoteFile.Builder(absolutePath).build();
	}

	protected boolean isInstalled(SecureChannel channel, String jdkHome) {
		RemoteFile completed = getJdkCustomizationCompleteFile(jdkHome);
		return channel.exists(completed.getAbsolutePath());
	}

	public boolean isSkip() {
		return skip;
	}

	public InstallZipContext getContext() {
		return context;
	}

}
