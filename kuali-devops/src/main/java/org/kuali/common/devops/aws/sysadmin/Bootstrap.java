package org.kuali.common.devops.aws.sysadmin;

import java.io.IOException;

import org.kuali.common.devops.aws.sysadmin.model.BootstrapContext;
import org.kuali.common.devops.aws.sysadmin.model.Service;
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
			if (!bootstrapCompleted(channel)) {
				bootstrap(channel);
			}
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error", e);
		} finally {
			ChannelUtils.closeQuietly(channel);
		}
	}

	protected boolean bootstrapCompleted(SecureChannel channel) {
		RemoteFile completed = getBootStrapCompletedFile();
		return channel.exists(completed.getAbsolutePath());
	}

	protected void bootstrap(SecureChannel channel) {
		// Re-size the root volume so it uses all of the allocated space
		String command1 = "resize2fs " + context.getRootVolumeDeviceName();

		// Update the general operating system to the latest and greatest
		String command2 = "yum --assumeyes update";

		ChannelUtils.exec(channel, command1, command2);

		// Install custom packages (if any)
		if (context.getPackages().size() > 0) {
			String command3 = "yum --assumeyes install " + CollectionUtils.getSpaceSeparatedString(context.getPackages());
			ChannelUtils.exec(channel, command3);
		}

		RemoteFile completed = getBootStrapCompletedFile();
		String content = "bootstrap completed: " + FormatUtils.getDate(System.currentTimeMillis());
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
			}
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error", e);
		} finally {
			ChannelUtils.closeQuietly(channel);
		}
	}

	protected void enableRootSSH(SecureChannel channel) {
		Service sshd = context.getSshdOverride().getService();

		String src = context.getSshdOverride().getConfigFileOverrideLocation();
		String dst = context.getSshEnabledUser().getHome() + "/" + sshd.getConfigFileName();

		String command1 = "sudo cp " + context.getSshEnabledUser().getAuthorizedKeys() + " " + context.getRoot().getAuthorizedKeys();
		String command2 = "sudo cp " + dst + " " + sshd.getConfigFileAbsolutePath();
		String command3 = "sudo service " + sshd.getName() + " restart";
		String command4 = "rm " + dst;

		RemoteFile file = new RemoteFile.Builder(dst).build();

		ChannelUtils.exec(channel, command1); // copy authorized_keys from ec2-user to root. This allows root to ssh
		channel.scp(src, file); // copy the updated sshd_config file into the ec2-users home directory
		ChannelUtils.exec(channel, command2); // copy the updated sshd_config file to /etc/ssh/sshd_config
		ChannelUtils.exec(channel, command3); // restart the sshd service
		ChannelUtils.exec(channel, command4); // delete the sshd_config file we left in the ec2-users home directory
	}

	protected boolean isRootSSHEnabled(SecureChannel channel) {
		User ec2 = context.getSshEnabledUser();
		User root = context.getRoot();
		RemoteFile temp = new RemoteFile.Builder(ec2.getHome() + "/ssh.bootstrap.check.tmp").build();
		String command1 = "sudo cp " + root.getAuthorizedKeys() + " " + temp.getAbsolutePath();
		String command2 = "sudo chown " + ec2.getGroup() + ":" + ec2.getLogin() + " " + temp.getAbsolutePath();
		channel.exec(command1); // Copy the authorized_keys file from root to a temp file
		channel.exec(command2); // Chown it to ec2-user
		String auth = channel.toString(temp); // Convert the contents of the file to a string
		channel.deleteFile(temp.getAbsolutePath()); // Delete the temp file
		Assert.noBlanks(auth);
		boolean enabled = !auth.contains("command=\"");
		return enabled;
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
