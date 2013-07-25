package org.kuali.common.deploy;

import org.kuali.common.util.FormatUtils;
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
	Executable sysAdminExecutable = new NoOpExecutable();
	Monitoring monitoring;
	ApplicationServer appServer;
	Executable databaseResetExecutable = new NoOpExecutable();

	@Override
	public void deploy() {
		long start = System.currentTimeMillis();
		logger.info("[deploy:starting]");
		// Make sure we are configured correctly
		Assert.notNull(context);
		Assert.notNull(channel);
		Assert.notNull(sysAdminExecutable);
		Assert.notNull(monitoring);
		Assert.notNull(appServer);
		Assert.notNull(databaseResetExecutable);
		try {
			logger.info("---------------- Deploy Application ----------------");
			logger.info("Secure Channel - {}@{}", context.getUsername(), context.getHostname());
			logger.info("Environment - {}", context.getEnvironment());
			logger.info("Application - {}", RepositoryUtils.toString(context.getApplication()));
			if (context.getJdbcDriver() != null) {
				logger.info("Jdbc Driver - {}", RepositoryUtils.toString(context.getJdbcDriver()));
			}
			for (Deployable deployable : context.getConfigFiles()) {
				logger.info("Config - [{}]", deployable.getLocal());
			}
			logger.info("----------------------------------------------------");
			channel.open();
			monitoring.stop();
			appServer.stop();
			sysAdminExecutable.execute();
			databaseResetExecutable.execute();
			monitoring.prepare();
			monitoring.start();
			appServer.prepare();
			appServer.start();
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			channel.close();
		}
		logger.info("[deploy:complete] - {}", FormatUtils.getTime(System.currentTimeMillis() - start));
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

	public Monitoring getMonitoring() {
		return monitoring;
	}

	public void setMonitoring(Monitoring monitoring) {
		this.monitoring = monitoring;
	}

	public Executable getSysAdminExecutable() {
		return sysAdminExecutable;
	}

	public void setSysAdminExecutable(Executable systemAdministration) {
		this.sysAdminExecutable = systemAdministration;
	}

}
