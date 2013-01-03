package org.kuali.common.deploy.service;

import org.kuali.common.util.secure.ChannelUtils;
import org.kuali.common.util.secure.SecureChannel;

public class DefaultDeployService implements DeployService {

	ApplicationServerController applicationServer;
	SecureChannel channel;

	@Override
	public void deploy() {
		try {
			channel.open();
			applicationServer.shutdown();
			applicationServer.cleanup();
			applicationServer.startup();
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			ChannelUtils.closeQuietly(channel);
		}
	}

	public ApplicationServerController getApplicationServer() {
		return applicationServer;
	}

	public void setApplicationServer(ApplicationServerController applicationServer) {
		this.applicationServer = applicationServer;
	}

	public SecureChannel getChannel() {
		return channel;
	}

	public void setChannel(SecureChannel channel) {
		this.channel = channel;
	}

}
