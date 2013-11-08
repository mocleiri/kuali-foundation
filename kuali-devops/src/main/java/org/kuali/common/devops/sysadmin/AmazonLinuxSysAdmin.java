package org.kuali.common.devops.sysadmin;

import java.io.IOException;
import java.util.List;

import org.kuali.common.util.Assert;
import org.kuali.common.util.channel.api.SecureChannel;
import org.kuali.common.util.channel.model.ChannelContext;
import org.kuali.common.util.channel.model.RemoteFile;
import org.kuali.common.util.channel.util.ChannelUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class AmazonLinuxSysAdmin implements SystemAdministrator {

	private static final Logger logger = LoggerFactory.getLogger(AmazonLinuxSysAdmin.class);

	public AmazonLinuxSysAdmin(AmazonLinuxContext context) {
		Assert.noNulls(context);
		this.context = context;
	}

	private final AmazonLinuxContext context;

	@Override
	public void enableRootSSH() {
		SecureChannel channel = null;
		try {
			String src = context.getSshd().getLocalConfigLocation();
			String path = context.getEc2User().getHome() + "/" + context.getSshd().getConfigFilename();
			String command1 = "sudo cp " + context.getEc2User().getAuthorizedKeys() + " " + context.getRoot().getAuthorizedKeys();
			String command2 = "sudo cp " + path + " " + context.getSshd().getRemoteConfigLocation();
			String command3 = "sudo service " + context.getSshd().getServiceName() + " restart";

			ChannelContext cc = new ChannelContext.Builder(context.getEc2User().getLogin(), context.getDnsName()).privateKey(context.getKeyPair().getPrivateKey().get())
					.requestPseudoTerminal(true).build();
			channel = context.getService().getChannel(cc);
			RemoteFile dst = new RemoteFile.Builder(path).build();
			ChannelUtils.exec(channel, command1); // copy authorized_keys from ec2-user to root as that version does not have the header commands blocking ssh
			logger.info("cp {} {}", src, dst.getAbsolutePath());
			channel.copyLocationToFile(src, dst); // copy the updated sshd_config file into the ec2-users home directory
			ChannelUtils.exec(channel, command2); // copy the updated sshd_config file to /etc/ssh/sshd_config
			ChannelUtils.exec(channel, command3); // restart the sshd service
			logger.info("rm {}", path);
			channel.deleteFile(path); // delete the sshd_config file we left in the ec2-users home directory
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error", e);
		} finally {
			ChannelUtils.closeQuietly(channel);
		}
	}

	@Override
	public void resizeRootVolume() {
	}

	@Override
	public void update() {
	}

	@Override
	public void installPackages(List<String> packages) {
	}

	public AmazonLinuxContext getContext() {
		return context;
	}

}
