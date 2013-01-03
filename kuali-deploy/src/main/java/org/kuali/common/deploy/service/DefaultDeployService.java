package org.kuali.common.deploy.service;

import org.kuali.common.util.secure.ChannelUtils;
import org.kuali.common.util.secure.SecureChannel;

public class DefaultDeployService implements DeployService {

	ApplicationServerController controller;
	SecureChannel channel;

	@Override
	public void deploy() {
		try {
			channel.open();
			controller.shutdown();
			controller.cleanup();
			controller.startup();
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			ChannelUtils.closeQuietly(channel);
		}
	}

	public ApplicationServerController getController() {
		return controller;
	}

	public void setController(ApplicationServerController controller) {
		this.controller = controller;
	}

	public SecureChannel getChannel() {
		return channel;
	}

	public void setChannel(SecureChannel channel) {
		this.channel = channel;
	}

}
