package org.kuali.common.deploy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.kuali.common.util.Assert;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.MonitorTextFileResult;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.secure.SecureChannel;
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
		logger.info("[appdynamics:stop] - {}", FormatUtils.getDate(new Date()));
		DeployUtils.killMatchingProcesses(channel, user, machineAgent.getStartupCommand(), "machine agent");
		logger.info("[appdynamics:stopped] - {}", FormatUtils.getDate(new Date()));
	}

	@Override
	public void prepare() {
		logger.info("[appdynamics:prepare] - {}", FormatUtils.getDate(new Date()));
		List<String> dirs = Arrays.asList(machineAgent.getLogsDir(), machineAgent.getTmpDir(), serverAgent.getLogsDir());
		List<String> chownDirs = getChownDirs(dirs);
		DeployUtils.delete(channel, dirs);
		DeployUtils.mkdirs(channel, dirs);
		DeployUtils.chown(channel, user, group, chownDirs);
		List<Deployable> deployables = Arrays.asList(machineAgent.getController(), serverAgent.getController());
		DeployUtils.copyFiles(channel, deployables, filterProperties);
		if (enabled) {
			String value = "\n" + serverAgent.getAppServerStartupOptions();
			PropertyUtils.appendToOrSetProperty(filterProperties, setEnvPropertyKey, value);
		}
		logger.info("[appdynamics:prepared]  - {}", FormatUtils.getDate(new Date()));
	}

	@Override
	public void start() {
		if (!enabled) {
			logger.info("[appdynamics:start] - (skipped) - monitoring is not enabled");
			return;
		}
		logger.info("[appdynamics:start] - {}", FormatUtils.getDate(new Date()));
		startMachineAgent(channel, machineAgent);
		logger.info("[appdynamics:started] - {}", FormatUtils.getDate(new Date()));
	}

	protected void startMachineAgent(SecureChannel channel, MachineAgent machineAgent) {
		logger.info("[appdynamics:machineagent:start]");
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
			logger.info("[appdynamics:machineagent:started] - {}", FormatUtils.getTime(result.getElapsed()));
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
