package org.kuali.common.devops.aws.sysadmin;

import java.io.IOException;

import org.kuali.common.devops.aws.sysadmin.model.BootstrapContext;
import org.kuali.common.devops.aws.sysadmin.model.ServiceOverride;
import org.kuali.common.devops.aws.sysadmin.model.User;
import org.kuali.common.util.Assert;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.channel.api.SecureChannel;
import org.kuali.common.util.channel.model.ChannelContext;
import org.kuali.common.util.channel.model.RemoteFile;
import org.kuali.common.util.channel.util.ChannelUtils;
import org.kuali.common.util.execute.Executable;

/**
 * Bootstrap a freshly launched Amazon Linux AWS instance
 * 
 * <ul>
 * <li>Enable root ssh</li>
 * <li>Resize the root volume to so it uses all of the allocated space</li>
 * <li>Update the operating system to the latest and greatest <code>yum update -y</code></li>
 * <li>Install a list of essential packages</li>
 * </ul>
 * 
 */
public final class Bootstrap implements Executable {

	private static final String WARNING = "WARNING: Do not delete or edit this file unless you know exactly what you are doing";

	public Bootstrap(BootstrapContext context) {
		this(context, false);
	}

	public Bootstrap(BootstrapContext context, boolean skip) {
		Assert.noNulls(context);
		this.context = context;
		this.skip = skip;
	}

	private final BootstrapContext context;
	private final boolean skip;

	@Override
	public void execute() {
		if (skip) {
			return;
		}
		bootstrap();
	}

	protected void bootstrap() {
		enableRootSSH();
		SecureChannel channel = null;
		try {
			channel = getChannel(context.getRoot(), false);
			if (!isBootstrapped(channel)) {
				bootstrap(channel);
				markAsBootstrapped(channel);
				Assert.isTrue(isBootstrapped(channel), "Unable to verify that this instance has been bootstrapped");
			}
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error", e);
		} finally {
			ChannelUtils.closeQuietly(channel);
		}
	}

	protected boolean isBootstrapped(SecureChannel channel) {
		RemoteFile completed = getBootStrapCompletedFile();
		return channel.exists(completed.getAbsolutePath());
	}

	protected void bootstrap(SecureChannel channel) {
		// Re-size the root volume so it uses all of the allocated space
		String command1 = "resize2fs " + context.getRootVolumeDeviceName();

		// Update the general operating system to the latest and greatest
		String command2 = "yum --assumeyes update";

		// Invoke the commands
		channel.exec(command1, command2);

		// Install custom packages (if any)
		if (context.getPackages().size() > 0) {
			String command = "yum --assumeyes install " + CollectionUtils.getSpaceSeparatedString(context.getPackages());
			channel.exec(command);
		}
	}

	protected void markAsBootstrapped(SecureChannel channel) {
		RemoteFile completed = getBootStrapCompletedFile();
		String content = "bootstrapping completed: " + FormatUtils.getDate(System.currentTimeMillis()) + "\n" + WARNING;
		channel.scpString(content, completed);
	}

	protected RemoteFile getBootStrapCompletedFile() {
		return new RemoteFile.Builder(context.getBootstrapCompletedAbsolutePath()).build();
	}

	/**
	 * Connect as ec2-user to see if root ssh is enabled. If it isn't, enable it.
	 */
	protected void enableRootSSH() {
		SecureChannel channel = null;
		try {
			channel = getChannel(context.getSshEnabledUser(), true);
			boolean enabled = isRootSSHEnabled(channel);
			if (!enabled) {
				enableRootSSH(channel);
				markAsRootSSHEnabled(channel);
				Assert.isTrue(isRootSSHEnabled(channel), "Unable to verify that root ssh is enabled");
			}
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error", e);
		} finally {
			ChannelUtils.closeQuietly(channel);
		}
	}

	protected void enableRootSSH(SecureChannel channel) {
		ServiceOverride sshd = context.getSshdOverride().getService();

		String src = context.getSshdOverride().getConfigFileOverrideLocation();
		String dst = context.getSshEnabledUser().getHome() + "/.bootstrap/" + sshd.getConfigFileName();

		String command1 = "sudo cp " + context.getSshEnabledUser().getAuthorizedKeys() + " " + context.getRoot().getAuthorizedKeys();
		String command2 = "sudo cp " + dst + " " + sshd.getConfigFileAbsolutePath();
		String command3 = "sudo service " + sshd.getName() + " restart";

		RemoteFile file = new RemoteFile.Builder(dst).build();

		channel.exec(command1); // copy authorized_keys from ec2-user to root. This allows root to ssh
		channel.scp(src, file); // create an sshd_config file in the ec2-users home directory from our internally modified copy
		channel.exec(command2); // copy the updated sshd_config file to /etc/ssh/sshd_config
		channel.exec(command3); // restart the sshd service

	}

	protected void markAsRootSSHEnabled(SecureChannel channel) {
		// Leave a marker file on the file system indicating that root ssh is now enabled
		RemoteFile enabled = getRootSSHEnabledFile(context.getSshEnabledUser());
		String content = "root ssh enabled: " + FormatUtils.getDate(System.currentTimeMillis()) + "\n" + WARNING;
		channel.scpString(content, enabled); // Create /home/ec2-user/.bootstrap/root-ssh.enabled
	}

	protected RemoteFile getRootSSHEnabledFile(User ec2User) {
		String absolutePath = ec2User.getHome() + "/.bootstrap/root-ssh.enabled";
		return new RemoteFile.Builder(absolutePath).build();
	}

	/**
	 * Return true only if the file /home/ec2-user/.bootstrap/root-ssh.enabled exists
	 */
	protected boolean isRootSSHEnabled(SecureChannel channel) {
		RemoteFile enabled = getRootSSHEnabledFile(context.getSshEnabledUser());
		return channel.exists(enabled.getAbsolutePath());
	}

	protected SecureChannel getChannel(User user, boolean requestPseudoTerminal) throws IOException {
		String dnsName = context.getHostname();
		String privateKey = context.getPrivateKey();
		ChannelContext cc = new ChannelContext.Builder(user.getLogin(), dnsName).privateKey(privateKey).requestPseudoTerminal(requestPseudoTerminal).build();
		return context.getService().openChannel(cc);
	}

	public BootstrapContext getContext() {
		return context;
	}

	public boolean isSkip() {
		return skip;
	}

}
