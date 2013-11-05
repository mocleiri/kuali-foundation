package org.kuali.common.deploy.monitoring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.kuali.common.deploy.DeployUtils;
import org.kuali.common.deploy.Deployable;
import org.kuali.common.util.Assert;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.MonitorTextFileResult;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.secure.channel.SecureChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppDynamicsMonitoring implements Monitoring {

	private static final Logger logger = LoggerFactory.getLogger(AppDynamicsMonitoring.class);

	SecureChannel channel;
	String setEnvPropertyKey = "setenv.env.content";
	MachineAgent machineAgent;
	ServerAgent serverAgent;
	String user;
	String group;
	boolean enabled;
	Properties filterProperties;

	@Override
	public void stop() {
		long start = System.currentTimeMillis();
		logger.info("[appdynamics:stopping]");
		DeployUtils.killMatchingProcesses(channel, user, machineAgent.getStartupCommand(), "machine agent");
		logger.info("[appdynamics:stopped] - {}", FormatUtils.getTime(System.currentTimeMillis() - start));
	}

	@Override
	public void prepare() {
		long start = System.currentTimeMillis();
		logger.info("[appdynamics:preparing]");
		List<String> dirs = Arrays.asList(machineAgent.getLogsDir(), machineAgent.getTmpDir(), serverAgent.getLogsDir());
		List<String> chownDirs = getChownDirs(dirs);
		DeployUtils.delete(channel, dirs);
		DeployUtils.mkdirs(channel, dirs);
		DeployUtils.chown(channel, user, group, chownDirs);
		List<Deployable> deployables = Arrays.asList(machineAgent.getController(), serverAgent.getController());
		DeployUtils.copyFiles(channel, deployables, filterProperties);
		logger.info("enabled={}", enabled);
		if (enabled) {
			String value = "\n" + serverAgent.getAppServerStartupOptions();
			PropertyUtils.appendToOrSetProperty(filterProperties, setEnvPropertyKey, value);
		}
		logger.info("[appdynamics:prepared] - {}", FormatUtils.getTime(System.currentTimeMillis() - start));
	}

	@Override
	public void start() {
		if (!enabled) {
			logger.info("[appdynamics:start] - (skipped) - monitoring is not enabled");
			return;
		}
		long start = System.currentTimeMillis();
		logger.info("[appdynamics:starting]");
		startMachineAgent(channel, machineAgent);
		logger.info("[appdynamics:started] - {}", FormatUtils.getTime(System.currentTimeMillis() - start));
	}

	protected void startMachineAgent(SecureChannel channel, MachineAgent machineAgent) {
		logger.info("[appdynamics:machineagent:starting]");
		boolean exists = channel.exists(machineAgent.getLogFile());
		Assert.isFalse(exists, "machine agent log file [" + machineAgent.getLogFile() + "] already exists");
		String command = DeployUtils.getNohupBackgroundProcess(user, machineAgent.getStartupCommand());
		logger.debug(command);
		channel.executeNoWait(command);
		String path = machineAgent.getLogFile();
		String token = machineAgent.getStartupToken();
		int intervalMillis = machineAgent.getLogFileIntervalMillis();
		int timeoutMillis = machineAgent.getStartupTimeoutMillis();
		String encoding = machineAgent.getLogFileEncoding();
		MonitorTextFileResult result = DeployUtils.monitorTextFile(channel, path, token, intervalMillis, timeoutMillis, encoding);
		if (!result.isContains()) {
			throw new IllegalStateException("Could not verify AppDynamics Machine Agent startup");
		} else {
			logger.info("[appdynamics:machineagent:started]");
		}
	}

	protected List<String> getChownDirs(List<String> dirs) {
		List<String> chownDirs = new ArrayList<String>();
		chownDirs.addAll(dirs);
		chownDirs.add(machineAgent.getBaseDir());
		chownDirs.add(serverAgent.getBaseDir());
		return chownDirs;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public SecureChannel getChannel() {
		return channel;
	}

	public void setChannel(SecureChannel channel) {
		this.channel = channel;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public MachineAgent getMachineAgent() {
		return machineAgent;
	}

	public void setMachineAgent(MachineAgent machineAgent) {
		this.machineAgent = machineAgent;
	}

	public ServerAgent getServerAgent() {
		return serverAgent;
	}

	public void setServerAgent(ServerAgent serverAgent) {
		this.serverAgent = serverAgent;
	}

	public Properties getFilterProperties() {
		return filterProperties;
	}

	public void setFilterProperties(Properties filterProperties) {
		this.filterProperties = filterProperties;
	}

	public String getSetEnvPropertyKey() {
		return setEnvPropertyKey;
	}

	public void setSetEnvPropertyKey(String setEnvPropertyKey) {
		this.setEnvPropertyKey = setEnvPropertyKey;
	}

}
