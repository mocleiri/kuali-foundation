package org.kuali.common.deploy.service;

import org.kuali.common.util.secure.ChannelUtils;
import org.kuali.common.util.secure.SecureChannel;

public class DefaultDeployService implements DeployService {

	ApplicationServer applicationServer;
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

	public ApplicationServer getApplicationServer() {
		return applicationServer;
	}

	public void setApplicationServer(ApplicationServer applicationServer) {
		this.applicationServer = applicationServer;
	}

	public SecureChannel getChannel() {
		return channel;
	}

	public void setChannel(SecureChannel channel) {
		this.channel = channel;
	}

}
