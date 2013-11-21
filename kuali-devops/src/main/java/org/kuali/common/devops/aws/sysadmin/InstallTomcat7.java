package org.kuali.common.devops.aws.sysadmin;

import java.io.IOException;
import java.util.List;

import org.kuali.common.devops.aws.sysadmin.model.Deployable;
import org.kuali.common.devops.aws.sysadmin.model.InstallTomcatContext;
import org.kuali.common.util.Assert;
import org.kuali.common.util.channel.api.ChannelService;
import org.kuali.common.util.channel.api.SecureChannel;
import org.kuali.common.util.channel.model.ChannelContext;
import org.kuali.common.util.channel.util.ChannelUtils;
import org.kuali.common.util.execute.Executable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Customize Tomcat
 */
public final class InstallTomcat7 implements Executable {

	private static final Logger logger = LoggerFactory.getLogger(InstallTomcat7.class);

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

		public InstallTomcat7 build() {
			Assert.noNulls(context);
			return new InstallTomcat7(this);
		}

	}

	private InstallTomcat7(Builder builder) {
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
		String installDir = context.getInstallDir();
		// Add, update, replace, configuration files as needed (server.xml, web.xml, cleanup.sh, forced-shutdown.sh, custom JSP's, etc)
		List<Deployable> deployables = TomcatConfig.getDeployables(installDir,"7");
		for (Deployable deployable : deployables) {
			channel.scp(deployable.getSource(), deployable.getDestination());
		}

		// Recursively chown everything in /usr/local/tomcat and /home/tomcat to tomcat:tomcat
		String dir1 = context.getZip().getInstallDir();
		String dir2 = context.getTomcat().getHome();
		String command1 = "chown -RL " + context.getTomcat().getGroup() + ":" + context.getTomcat().getLogin() + " " + dir1 + " " + dir2;

		// Remove annoying windows .bat files
		String command2 = "rm -f " + context.getZip().getInstallDir() + "/bin/*.bat";

		// Make everything in the bin directory executable
		String command3 = "chmod -R 755 " + context.getZip().getInstallDir() + "/bin";

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
