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

		SecureChannel channel = null;
		try {
			channel = context.getService().openChannel(context.getContext());
			boolean installed = isInstalled(channel, context.getInstallDir());
			if (!installed) {
				install(channel);
			}
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error", e);
		} finally {
			ChannelUtils.closeQuietly(channel);
		}

	}

	protected void install(SecureChannel channel) {

		String jdkHome = context.getInstallDir();

		// Make sure everything in bin and jre/bin is executable
		String bin = jdkHome + "/bin";
		String jreBin = jdkHome + "/jre/bin";
		String command1 = "chmod -R 755 " + bin + " " + jreBin;

		// AppDynamics needs a copy of tools.jar in jre/lib/ext
		String src = jdkHome + "/lib/tools.jar";
		String dst = jdkHome + "/jre/lib/ext/tools.jar";
		String command2 = "cp " + src + " " + dst;

		// install the vanilla zip package
		new InstallZip.Builder(context).forceExecution(true).build().execute(channel);

		// customize the jdk installation as needed
		channel.exec(command1, command2);

		// leave a marker file indicating the installation/customization has been completed correctly
		String content = "jdk installed and customized: " + FormatUtils.getDate(System.currentTimeMillis()) + "\n" + WARNING;
		RemoteFile file = getJdkCustomizationCompleteFile(jdkHome);
		channel.scpString(content, file);
	}

	protected RemoteFile getJdkCustomizationCompleteFile(String jdkHome) {
		String absolutePath = jdkHome + "/jdk.installed";
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
