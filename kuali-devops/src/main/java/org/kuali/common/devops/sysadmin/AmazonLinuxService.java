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

public final class AmazonLinuxService implements SysAdminService {

	private static final Logger logger = LoggerFactory.getLogger(AmazonLinuxService.class);

	public AmazonLinuxService(AmazonLinuxContext context) {
		Assert.noNulls(context);
		this.context = context;
	}

	private final AmazonLinuxContext context;

	@Override
	public void enableRootSSH() {
		SecureChannel channel = null;
		try {
			String src = "classpath:org/kuali/common/kuali-devops/amazon-linux/2013.09/etc/ssh/sshd_config";
			String path = context.getEc2User().getHome() + "/sshd_config";
			String command1 = "sudo cp " + context.getEc2User().getAuthorizedKeys() + " " + context.getRoot().getAuthorizedKeys();
			String command2 = "sudo cp " + path + " /etc/ssh/sshd_config";
			String command3 = "sudo service sshd restart";

			ChannelContext cc = getEC2UserContext(instance, context.getKeyPair().getPrivateKey().get());
			channel = context.getService().getChannel(cc);
			RemoteFile dst = new RemoteFile.Builder(path).build();
			exec(channel, command1); // copy authorized_keys from ec2-user to root as that version does not have the header commands blocking ssh
			logger.info("cp {} {}", src, dst.getAbsolutePath());
			channel.copyLocationToFile(src, dst); // copy the updated sshd_config file into the ec2-users home directory
			exec(channel, command2); // copy the updated sshd_config file to /etc/ssh/sshd_config
			exec(channel, command3); // restart the sshd service
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
