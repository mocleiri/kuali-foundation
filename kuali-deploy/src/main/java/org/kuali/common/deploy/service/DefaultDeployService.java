package org.kuali.common.deploy.service;

import org.kuali.common.util.secure.SecureChannel;
import org.springframework.util.Assert;

public class DefaultDeployService implements DeployService {

	SecureChannel channel;
	ApplicationServerController controller;
	FileSystemAttendant organizer;

	@Override
	public void deploy() {
		Assert.notNull(channel);
		Assert.notNull(controller);
		try {
			channel.open();
			controller.stop();
			organizer.clean();
			organizer.prepare();
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

	public FileSystemAttendant getOrganizer() {
		return organizer;
	}

	public void setOrganizer(FileSystemAttendant organizer) {
		this.organizer = organizer;
	}

}
