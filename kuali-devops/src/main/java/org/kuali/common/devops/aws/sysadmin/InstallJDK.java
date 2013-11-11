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

	public static class Builder {

		// Required (no default value)
		private final InstallZipContext context;

		// Optional (default values are usually ok)
		private boolean forceExecution = false;
		private boolean skip = false;
		private boolean skipIfInstalled = true;

		public Builder(InstallZipContext context) {
			this.context = context;
		}

		public Builder forceExecution(boolean forceExecution) {
			this.forceExecution = forceExecution;
			return this;
		}

		public Builder skipIfInstalled(boolean skipIfInstalled) {
			this.skipIfInstalled = skipIfInstalled;
			return this;
		}

		public Builder skip(boolean skip) {
			this.skip = skip;
			return this;
		}

		public InstallJDK build() {
			Assert.noNulls(context);
			return new InstallJDK(this);
		}

	}

	private InstallJDK(Builder builder) {
		this.context = builder.context;
		this.forceExecution = builder.forceExecution;
		this.skip = builder.skip;
		this.skipIfInstalled = builder.skipIfInstalled;
	}

	private final InstallZipContext context;
	private final boolean skip;
	private final boolean forceExecution;
	private final boolean skipIfInstalled;

	@Override
	public void execute() {

		if (forceExecution) {
			// Always install, no matter what (overrides the skip flags)
			install(false);
		} else if (skip) {
			// Always skip the install (even it it's not installed)
			return;
		} else {
			// Complete the install with the option to skip, if it's already installed
			install(skipIfInstalled);
		}

	}

	protected void install(boolean skipIfInstalled) {
		SecureChannel channel = null;
		try {
			// Open a secure channel to the server
			channel = context.getService().openChannel(context.getContext());

			// Determine if it is installed already
			boolean installed = isInstalled(channel, context.getInstallDir());

			// Only skip if it's already installed AND the skip-if-installed flag is set
			boolean skip = installed && skipIfInstalled;

			// If we aren't skipping the install, install it!
			if (!skip) {
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

	public boolean isForceExecution() {
		return forceExecution;
	}

	public boolean isSkipIfInstalled() {
		return skipIfInstalled;
	}

}
