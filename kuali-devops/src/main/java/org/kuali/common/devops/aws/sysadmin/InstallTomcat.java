package org.kuali.common.devops.aws.sysadmin;

import java.io.IOException;
import java.util.List;

import org.kuali.common.devops.aws.sysadmin.model.FileResource;
import org.kuali.common.devops.aws.sysadmin.model.InstallTomcatContext;
import org.kuali.common.util.Assert;
import org.kuali.common.util.channel.api.ChannelService;
import org.kuali.common.util.channel.api.SecureChannel;
import org.kuali.common.util.channel.model.ChannelContext;
import org.kuali.common.util.channel.util.ChannelUtils;
import org.kuali.common.util.execute.Executable;

/**
 * Customize Tomcat
 */
public final class InstallTomcat implements Executable {

	private final InstallTomcatContext context;
	private final boolean skip;

	public static class Builder {

		// Required
		private final InstallTomcatContext context;

		// Optional
		private boolean skip = false;

		public Builder(InstallTomcatContext context) {
			this.context = context;
		}

		public Builder skip(boolean skip) {
			this.skip = skip;
			return this;
		}

		public InstallTomcat build() {
			Assert.noNulls(context);
			return new InstallTomcat(this);
		}

	}

	private InstallTomcat(Builder builder) {
		this.context = builder.context;
		this.skip = builder.skip;
	}

	@Override
	public void execute() {

		if (skip) {
			return;
		}

		SecureChannel channel = null;
		try {

			ChannelService service = context.getService();
			ChannelContext cc = context.getContext();
			channel = service.openChannel(cc);

			String command = "yum --assumeyes " + context.getPackageName();
			channel.exec(command);

		} catch (IOException e) {
			throw new IllegalStateException(e);
		} finally {
			ChannelUtils.closeQuietly(channel);
		}

	}

	protected void customizeTomcat(SecureChannel channel) {
		String installDir = context.getSharedDir() + "/" + context.getPackageName();

		// Add, update, and delete configuration files as needed (server.xml, web.xml, cleanup.sh, forced-shutdown.sh, custom JSP's, etc)
		List<FileResource> deployables = TomcatConfig.getDeployables(installDir, context.getVersion().getValue());
		for (FileResource deployable : deployables) {
			channel.scp(deployable.getSource(), deployable.getDestination());
		}

		// Recursively chown everything in /usr/share/tomcat[6,7] and /home/tomcat to tomcat:tomcat
		String dir1 = installDir;
		String dir2 = context.getUser().getHome();
		String command1 = "chown -RL " + context.getUser().getGroup() + ":" + context.getUser().getLogin() + " " + dir1 + " " + dir2;

		// Remove annoying windows .bat files
		String command2 = "rm -f " + installDir + "/bin/*.bat";

		// Make everything in the bin directory executable
		String command3 = "chmod -R 755 " + installDir + "/bin";

		// Invoke the commands
		channel.exec(command1, command2, command3);
	}

	public boolean isSkip() {
		return skip;
	}

	public InstallTomcatContext getContext() {
		return context;
	}

}
