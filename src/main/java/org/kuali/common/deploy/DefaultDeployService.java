package org.kuali.common.deploy;

import org.kuali.common.util.RepositoryUtils;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.execute.NoOpExecutable;
import org.kuali.common.util.secure.SecureChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

public class DefaultDeployService implements DeployService {

	private static final Logger logger = LoggerFactory.getLogger(DefaultDeployService.class);

	SecureChannel channel;
	AppServerController controller;
	Executable databaseResetExecutable = new NoOpExecutable();
	FileSystemHandler handler;
	DeployContext context;
	Executable httpWaitExecutable = new NoOpExecutable();

	@Override
	public void deploy() {
		Assert.notNull(channel);
		Assert.notNull(controller);
		Assert.notNull(handler);
		Assert.notNull(context);
		try {
			logger.info("---------------- Deploy Application ----------------");
			logger.info("Secure Channel - {}@{}", context.getUsername(), context.getHostname());
			logger.info("Environment - {}", context.getEnvironment());
			logger.info("Application - {}", RepositoryUtils.toString(context.getApplication()));
			if (context.getJdbcDriver() != null) {
				logger.info("Jdbc Driver - {}", RepositoryUtils.toString(context.getJdbcDriver()));
			}
			logger.info("Config - [{}]", context.getConfig().getLocal());
			logger.info("----------------------------------------------------");
			channel.open();
			controller.stop();
			databaseResetExecutable.execute();
			handler.clean();
			handler.prepare();
			controller.start();
			httpWaitExecutable.execute();
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

	public Executable getDatabaseResetExecutable() {
		return databaseResetExecutable;
	}

	public void setDatabaseResetExecutable(Executable databaseResetExecutable) {
		this.databaseResetExecutable = databaseResetExecutable;
	}

	public Executable getHttpWaitExecutable() {
		return httpWaitExecutable;
	}

	public void setHttpWaitExecutable(Executable httpWaitExecutable) {
		this.httpWaitExecutable = httpWaitExecutable;
	}

}
