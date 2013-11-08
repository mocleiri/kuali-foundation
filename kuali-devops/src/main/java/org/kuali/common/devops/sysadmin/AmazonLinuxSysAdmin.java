package org.kuali.common.devops.sysadmin;

import java.io.IOException;

import org.kuali.common.util.Assert;
import org.kuali.common.util.channel.api.SecureChannel;
import org.kuali.common.util.channel.model.ChannelContext;
import org.kuali.common.util.channel.model.RemoteFile;
import org.kuali.common.util.channel.util.ChannelUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class AmazonLinuxSysAdmin implements SysAdmin {

	private static final Logger logger = LoggerFactory.getLogger(AmazonLinuxSysAdmin.class);

	public AmazonLinuxSysAdmin(SysAdminContext context) {
		Assert.noNulls(context);
		this.context = context;
	}

	private final SysAdminContext context;

	@Override
	public void enableRootSSH() {
		SecureChannel channel = null;
		try {
			String src = context.getSshd().getLocalConfigLocation();
			String path = context.getSshEnabledUser().getHome() + "/" + context.getSshd().getConfigFilename();
			String command1 = "sudo cp " + context.getSshEnabledUser().getAuthorizedKeys() + " " + context.getRoot().getAuthorizedKeys();
			String command2 = "sudo cp " + path + " " + context.getSshd().getRemoteConfigLocation();
			String command3 = "sudo service " + context.getSshd().getServiceName() + " restart";

			ChannelContext cc = new ChannelContext.Builder(context.getSshEnabledUser().getLogin(), context.getDnsName()).privateKey(context.getKeyPair().getPrivateKey().get())
					.requestPseudoTerminal(true).build();
			channel = context.getService().getChannel(cc);
			RemoteFile dst = new RemoteFile.Builder(path).build();
			ChannelUtils.exec(channel, command1); // copy authorized_keys from ec2-user to root since ec2-user's doesn't block out ssh
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
	public void bootstrap() {
	}

	public SysAdminContext getContext() {
		return context;
	}

}
