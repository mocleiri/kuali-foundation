package org.kuali.common.deploy;

import java.util.Date;

import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.secure.SecureChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppDynamicsMonitoring implements Monitoring {

	private static final Logger logger = LoggerFactory.getLogger(AppDynamicsMonitoring.class);

	SecureChannel channel;
	MachineAgent machineAgent;
	String user;
	String group;
	String javaStartupOptions;
	boolean enabled;

	@Override
	public void stop() {
		logger.info("[appdynamics:stop] - {}", FormatUtils.getDate(new Date()));
		DeployUtils.killMatchingProcesses(channel, user, machineAgent.getStartupCommand(), "machine agent");
	}

	@Override
	public void prepare() {
		logger.info("[appdynamics:prepare]  - {}", FormatUtils.getDate(new Date()));
		// List<String> dirs = Arrays.asList(tmpDir, logDir);
		// DeployUtils.delete(channel, dirs);
		// DeployUtils.mkdirs(channel, dirs);
		// DeployUtils.chown(channel, user, group, dirs);
	}

	@Override
	public void start() {
		if (!enabled) {
			logger.info("[appdynamics:start]    - monitoring is not enabled");
			return;
		}
		logger.info("[appdynamics:start]    - {}", FormatUtils.getDate(new Date()));
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
	public String getJavaStartupOptions() {
		return javaStartupOptions;
	}

	public void setJavaStartupOptions(String javaStartupOptions) {
		this.javaStartupOptions = javaStartupOptions;
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

}
