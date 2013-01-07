package org.kuali.common.deploy;

import org.kuali.common.util.Artifact;
import org.kuali.common.util.RepositoryUtils;
import org.kuali.common.util.secure.SecureChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

public class DefaultDeployService implements DeployService {

	private static final Logger logger = LoggerFactory.getLogger(DefaultDeployService.class);

	SecureChannel channel;
	AppServerController controller;
	FileSystemHandler handler;
	DeployContext context;

	@Override
	public void deploy() {
		Assert.notNull(channel);
		Assert.notNull(controller);
		Assert.notNull(handler);
		Assert.notNull(context);
		try {
			Artifact war = context.getWar();
			logger.info("---------------- Deploy Application ----------------");
			logger.info("Secure Channel Username - {}", context.getUsername());
			logger.info("Secure Channel Hostname - {}", context.getHostname());
			logger.info("Environment - {}", context.getEnvironment());
			logger.info("Group Id - {}", war.getGroupId());
			logger.info("Artifact Id - {}", war.getArtifactId());
			logger.info("Version - {}", war.getVersion());
			logger.info("Classifier - {}", war.getClassifier());
			logger.info("Packaging - {}", war.getPackaging());
			if (context.getJdbcDriver() != null) {
				logger.info("Jdbc Driver - {}", RepositoryUtils.toString(context.getJdbcDriver()));
			}
			logger.info("----------------------------------------------------");
			channel.open();
			controller.stop();
			handler.clean();
			handler.prepare();
			controller.start();
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			channel.close();
		}
	}

	public AppServerController getController() {
		return controller;
	}

	public void setController(AppServerController controller) {
		this.controller = controller;
	}

	public SecureChannel getChannel() {
		return channel;
	}

	public void setChannel(SecureChannel channel) {
		this.channel = channel;
	}

	public FileSystemHandler getHandler() {
		return handler;
	}

	public void setHandler(FileSystemHandler handler) {
		this.handler = handler;
	}

	public DeployContext getContext() {
		return context;
	}

	public void setContext(DeployContext context) {
		this.context = context;
	}

}
