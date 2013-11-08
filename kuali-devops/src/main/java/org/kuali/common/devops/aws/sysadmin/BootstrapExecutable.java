package org.kuali.common.devops.aws.sysadmin;

import java.io.IOException;

import org.kuali.common.devops.aws.sysadmin.model.BootstrapContext;
import org.kuali.common.util.Assert;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.channel.api.SecureChannel;
import org.kuali.common.util.channel.model.ChannelContext;
import org.kuali.common.util.channel.model.RemoteFile;
import org.kuali.common.util.channel.util.ChannelUtils;
import org.kuali.common.util.execute.Executable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Bootstrap a freshly launched AWS instance
 * 
 * <ul>
 * <li>Enable root ssh</li>
 * <li>Resize the root volume to so it uses all of the allocated space</li>
 * <li>Update the operating system to the latest and greatest <code>yum update -y</code></li>
 * <li>Install a list of essential packages</li>
 * </ul>
 * 
 */
public final class BootstrapExecutable implements Executable {

	private static final Logger logger = LoggerFactory.getLogger(BootstrapExecutable.class);

	public BootstrapExecutable(BootstrapContext context) {
		this(context, false);
	}

	public BootstrapExecutable(BootstrapContext context, boolean skip) {
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
			channel = getChannel(context.getRoot().getLogin(), false);
			String command1 = "resize2fs " + context.getRootVolumeDeviceName();
			String command2 = "yum --assumeyes update";
			String command3 = "yum --assumeyes install " + CollectionUtils.getSpaceSeparatedString(context.getPackages());
			ChannelUtils.exec(channel, command1); // Re-size the root volume so it uses all of the allocated space
			ChannelUtils.exec(channel, command2); // Update the general operating system to the latest and greatest
			if (context.getPackages().size() > 0) {
				ChannelUtils.exec(channel, command3); // Install custom packages (if any)
			}
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error", e);
		} finally {
			ChannelUtils.closeQuietly(channel);
		}
	}

	protected void enableRootSSH() {
		SecureChannel channel = null;
		try {
			channel = getChannel(context.getSshEnabledUser().getLogin(), true);

			String src = context.getSshd().getLocalConfigLocation();
			String dst = context.getSshEnabledUser().getHome() + "/" + context.getSshd().getConfigFilename();
			String command1 = "sudo cp " + context.getSshEnabledUser().getAuthorizedKeys() + " " + context.getRoot().getAuthorizedKeys();
			String command2 = "sudo cp " + dst + " " + context.getSshd().getRemoteConfigLocation();
			String command3 = "sudo service " + context.getSshd().getServiceName() + " restart";

			RemoteFile file = new RemoteFile.Builder(dst).build();
			ChannelUtils.exec(channel, command1); // copy authorized_keys from ec2-user to root since that one doesn't prevent ssh
			logger.info("cp {} {}", src, file.getAbsolutePath());
			channel.copyLocationToFile(src, file); // copy the updated sshd_config file into the ec2-users home directory
			ChannelUtils.exec(channel, command2); // copy the updated sshd_config file to /etc/ssh/sshd_config
			ChannelUtils.exec(channel, command3); // restart the sshd service
			logger.info("rm {}", dst);
			channel.deleteFile(dst); // delete the sshd_config file we left in the ec2-users home directory
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error", e);
		} finally {
			ChannelUtils.closeQuietly(channel);
		}
	}

	protected SecureChannel getChannel(String login, boolean requestPseudoTerminal) throws IOException {
		String dnsName = context.getHostname();
		String privateKey = context.getPrivateKey();
		ChannelContext cc = new ChannelContext.Builder(login, dnsName).privateKey(privateKey).requestPseudoTerminal(requestPseudoTerminal).build();
		return context.getService().getChannel(cc);
	}

	public BootstrapContext getContext() {
		return context;
	}

	public boolean isSkip() {
		return skip;
	}

}
