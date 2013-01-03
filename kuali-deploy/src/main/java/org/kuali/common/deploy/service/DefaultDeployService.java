package org.kuali.common.deploy.service;

import org.kuali.common.util.secure.SecureChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

public class DefaultDeployService implements DeployService {

	private static final Logger logger = LoggerFactory.getLogger(DefaultDeployService.class);

	SecureChannel channel;
	ApplicationServerController controller;
	FileSystemAttendant attendant;

	@Override
	public void deploy() {
		Assert.notNull(channel);
		Assert.notNull(controller);
		Assert.notNull(attendant);
		try {
			logger.debug("Opening secure channel");
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
