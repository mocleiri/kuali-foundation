package org.kuali.common.deploy.service;

import org.kuali.common.util.secure.SecureChannel;
import org.springframework.util.Assert;

public class DefaultDeployService implements DeployService {

	SecureChannel channel;
	ApplicationServerController controller;
	FileSystemAttendant attendant;

	@Override
	public void deploy() {
		Assert.notNull(channel);
		Assert.notNull(controller);
		try {
			channel.open();
			controller.stop();
			attendant.clean();
			attendant.prepare();
			controller.start();
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

	public FileSystemAttendant getAttendant() {
		return attendant;
	}

	public void setAttendant(FileSystemAttendant attendant) {
		this.attendant = attendant;
	}

}
