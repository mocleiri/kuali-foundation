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

	DeployContext context;
	SecureChannel channel;
	Monitoring monitoring;
	ApplicationServer appServer;
	Executable databaseResetExecutable = new NoOpExecutable();
	FileSystem fileSystem;
	Executable httpWaitExecutable = new NoOpExecutable();

	@Override
	public void deploy() {
		Assert.notNull(context);
		Assert.notNull(channel);
		Assert.notNull(appServer);
		Assert.notNull(fileSystem);
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
			// monitoring.stop();
			appServer.stop();
			// databaseResetExecutable.execute();
			// fileSystem.clean();
			// monitoring.prepare();
			// fileSystem.prepare();
			// monitoring.start();
			appServer.prepare();
			appServer.start();
			// httpWaitExecutable.execute();
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			channel.close();
		}
	}

	public ApplicationServer getAppServer() {
		return appServer;
	}

	public void setAppServer(ApplicationServer controller) {
		this.appServer = controller;
	}

	public SecureChannel getChannel() {
		return channel;
	}

	public void setChannel(SecureChannel channel) {
		this.channel = channel;
	}

	public FileSystem getFileSystem() {
		return fileSystem;
	}

	public void setFileSystem(FileSystem handler) {
		this.fileSystem = handler;
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

	public Monitoring getMonitoring() {
		return monitoring;
	}

	public void setMonitoring(Monitoring monitoring) {
		this.monitoring = monitoring;
	}

}
