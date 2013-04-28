package org.kuali.common.deploy;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.secure.SecureChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppDynamicsMonitoring implements Monitoring {

	private static final Logger logger = LoggerFactory.getLogger(AppDynamicsMonitoring.class);

	SecureChannel channel;
	MachineAgent machineAgent;
	ServerAgent serverAgent;
	String user;
	String group;
	String appServerStartupOptions;
	boolean enabled;
	Properties filterProperties;

	@Override
	public void stop() {
		logger.info("[appdynamics:stop] - {}", FormatUtils.getDate(new Date()));
		DeployUtils.killMatchingProcesses(channel, user, machineAgent.getStartupCommand(), "machine agent");
	}

	@Override
	public void prepare() {
		logger.info("[appdynamics:prepare]  - {}", FormatUtils.getDate(new Date()));
		List<String> dirs = Arrays.asList(machineAgent.getLogsDir(), machineAgent.getTmpDir(), serverAgent.getLogsDir());
		DeployUtils.delete(channel, dirs);
		DeployUtils.mkdirs(channel, dirs);
		DeployUtils.chown(channel, user, group, dirs);
		DeployUtils.chown(channel, user, group, Arrays.asList(machineAgent.getBaseDir(), serverAgent.getBaseDir()));
		List<Deployable> deployables = Arrays.asList(machineAgent.getController(), serverAgent.getController());
		DeployUtils.copyFiles(channel, deployables, filterProperties);
	}

	@Override
	public void start() {
		if (!enabled) {
			logger.info("[appdynamics:start]    - monitoring is not enabled");
			return;
		}
		logger.info("[appdynamics:start]    - {}", FormatUtils.getDate(new Date()));
		// This command starts up Machine Agent running as tomcat using nohup so it will continue running after the session closes
		// The danger here is that we have absolutely no idea if the process started successfully because we can't wait around
		// for the command to complete and thus get an exit value. The command will never complete. It just runs in the background
		// forever.
		String command = DeployUtils.getNohupBackgroundProcessCommand(user, machineAgent.getStartupCommand());
		logger.debug(command);
		channel.executeNoWait(command);
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

	@Override
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

	@Override
	public String getAppServerStartupOptions() {
		return appServerStartupOptions;
	}

	public void setAppServerStartupOptions(String appServerStartupOptions) {
		this.appServerStartupOptions = appServerStartupOptions;
	}

}
