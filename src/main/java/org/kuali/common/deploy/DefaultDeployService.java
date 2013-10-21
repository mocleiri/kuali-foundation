package org.kuali.common.deploy;

import org.kuali.common.deploy.appserver.ApplicationServer;
import org.kuali.common.deploy.monitoring.Monitoring;
import org.kuali.common.util.Assert;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.execute.impl.NoOpExecutable;
import org.kuali.common.util.maven.RepositoryUtils;
import org.kuali.common.util.nullify.NullUtils;
import org.kuali.common.util.secure.channel.SSHUtils;
import org.kuali.common.util.secure.channel.SecureChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultDeployService implements DeployService {

	private static final Logger logger = LoggerFactory.getLogger(DefaultDeployService.class);

	private static final Executable DEFAULT_SYS_ADMIN_EXEC = NoOpExecutable.INSTANCE;
	private static final Executable DEFAULT_DB_RESET_EXEC = NoOpExecutable.INSTANCE;

	public DefaultDeployService(DeployContext context, Monitoring monitoring, ApplicationServer appServer) {
		this(context, DEFAULT_SYS_ADMIN_EXEC, monitoring, appServer, DEFAULT_DB_RESET_EXEC);
	}

	public DefaultDeployService(DeployContext context, Executable sysAdmin, Monitoring monitoring, ApplicationServer appServer, Executable dbReset) {
		Assert.noNulls(context, sysAdmin, monitoring, appServer, dbReset);
		this.context = context;
		this.sysAdminExecutable = sysAdmin;
		this.monitoring = monitoring;
		this.appServer = appServer;
		this.databaseResetExecutable = dbReset;
	}

	private final DeployContext context;
	private final Executable sysAdminExecutable;
	private final Monitoring monitoring;
	private final ApplicationServer appServer;
	private final Executable databaseResetExecutable;

	@Override
	public void deploy() {
		SecureChannel channel = context.getChannel();
		long start = System.currentTimeMillis();
		logger.info("[deploy:starting]");
		try {
			logger.info("---------------- Deploy Application ----------------");
			if (channel.getPort() == SSHUtils.DEFAULT_PORT) {
				logger.info("Secure Channel - {}@{}", channel.getUsername(), channel.getHostname());
			} else {
				Object[] args = { channel.getUsername(), channel.getHostname(), channel.getPort() };
				logger.info("Secure Channel - {}@{}:{}", args);
			}
			logger.info("Environment - {}", context.getEnvironment().getName());
			logger.info("Application - {}", RepositoryUtils.toString(context.getApplication()));
			if (context.getJdbcDriver().isPresent()) {
				logger.info("Jdbc Driver - {}", RepositoryUtils.toString(context.getJdbcDriver().get()));
			} else {
				logger.info("Jdbc Driver - {}", NullUtils.NONE);
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

	public static Logger getLogger() {
		return logger;
	}

	public DeployContext getContext() {
		return context;
	}

	public Executable getSysAdminExecutable() {
		return sysAdminExecutable;
	}

	public Monitoring getMonitoring() {
		return monitoring;
	}

	public ApplicationServer getAppServer() {
		return appServer;
	}

	public Executable getDatabaseResetExecutable() {
		return databaseResetExecutable;
	}

}
