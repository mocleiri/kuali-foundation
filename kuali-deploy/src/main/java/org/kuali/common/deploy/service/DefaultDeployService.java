package org.kuali.common.deploy.service;

import org.kuali.common.util.secure.SecureChannel;
import org.springframework.util.Assert;

public class DefaultDeployService implements DeployService {

	SecureChannel channel;
	ApplicationServerController controller;

	@Override
	public void deploy() {
		Assert.notNull(channel);
		Assert.notNull(controller);
		try {
			channel.open();
			controller.shutdown();
			controller.cleanup();
			controller.startup();
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			channel.close();
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
