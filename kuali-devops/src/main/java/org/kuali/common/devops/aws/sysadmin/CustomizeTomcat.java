package org.kuali.common.devops.aws.sysadmin;

import java.util.List;

import org.kuali.common.devops.aws.sysadmin.model.Bashrc;
import org.kuali.common.devops.aws.sysadmin.model.CustomizeTomcatContext;
import org.kuali.common.devops.aws.sysadmin.model.Deployable;
import org.kuali.common.devops.aws.sysadmin.model.User;
import org.kuali.common.util.Assert;
import org.kuali.common.util.channel.api.SecureChannel;
import org.kuali.common.util.channel.model.RemoteFile;
import org.kuali.common.util.channel.util.ChannelExecutable;
import org.kuali.common.util.channel.util.ChannelUtils;
import org.kuali.common.util.nullify.NullUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Customize Tomcat
 */
public final class CustomizeTomcat implements ChannelExecutable {

	private static final Logger logger = LoggerFactory.getLogger(CustomizeTomcat.class);

	private final CustomizeTomcatContext context;
	private final boolean skip;

	public static class Builder {

		// Required
		private final CustomizeTomcatContext context;

		// Optional
		private boolean skip = false;

		public Builder(CustomizeTomcatContext context) {
			this.context = context;
		}

		public Builder skip(boolean skip) {
			this.skip = skip;
			return this;
		}

		public CustomizeTomcat build() {
			Assert.noNulls(context);
			return new CustomizeTomcat(this);
		}

	}

	private CustomizeTomcat(Builder builder) {
		this.context = builder.context;
		this.skip = builder.skip;
	}

	@Override
	public void execute(SecureChannel channel) {
		if (skip) {
			return;
		}

		// This has to happen first so tomcat dir's can be chown'd to tomcat
		createUser(channel);

		// Customize things as needed, chown everything to tomcat:tomcat
		customizeTomcat(channel);

	}

	protected void customizeTomcat(SecureChannel channel) {
		// Remove, then recreate the webapps dir to get rid of all the junk that's in there by default (docs, manager app, etc)
		String webappsDir = context.getZip().getInstallDir() + "/webapps";
		ChannelUtils.exec(channel, "rm -rf " + webappsDir + "; mkdir -p " + webappsDir);

		// Add, update, replace, configuration files as needed (server.xml, web.xml, cleanup.sh, forced-shutdown.sh, custom JSP's, etc)
		List<Deployable> deployables = TomcatUtils.getDeployables(context.getZip().getInstallDir(), context.getMajorVersion());
		for (Deployable deployable : deployables) {
			ChannelUtils.scp(channel, deployable.getSource(), deployable.getDestination());
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
		ChannelUtils.exec(channel, command1, command2, command3);
	}

	protected void createUser(SecureChannel channel) {
		// Delete the tomcat user (if it exists)
		boolean tomcatUserExists = channel.exists(context.getTomcat().getHome());
		if (tomcatUserExists) {
			ChannelUtils.exec(channel, "userdel -rf " + context.getTomcat().getLogin());
		}

		// Create the tomcat group and user
		String command1 = "groupadd -f " + context.getTomcat().getGroup();
		String command2 = "useradd -g " + context.getTomcat().getGroup() + " " + context.getTomcat().getLogin();

		// Invoke the commands
		ChannelUtils.exec(channel, command1, command2);

		Bashrc bashrc = getBashrc();
		RemoteFile file = new RemoteFile.Builder(bashrc.getLocation()).build();
		channel.copyStringToFile(bashrc.getContent(), file);
		logger.info("created -> " + file.getAbsolutePath());
	}

	protected Bashrc getBashrc() {
		User user = context.getTomcat();
		Bashrc dummy = new Bashrc.Builder(user, NullUtils.NONE).build();
		String content = BashrcUtils.getContent(dummy, context.getBashrc());
		return new Bashrc.Builder(user, content).build();
	}

	public boolean isSkip() {
		return skip;
	}

	public CustomizeTomcatContext getContext() {
		return context;
	}

}
