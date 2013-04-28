package org.kuali.common.deploy;

import java.util.Date;

import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.secure.SecureChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppDynamicsMonitoring implements Monitoring {

	private static final Logger logger = LoggerFactory.getLogger(AppDynamicsMonitoring.class);

	SecureChannel channel;
	String machineAgentCommand;
	String user;
	String group;
	String tmpDir;
	String logDir;
	String javaStartupOptions;
	boolean enabled;

	@Override
	public void stop() {
		logger.info("[appdynamics:stop] - {}", FormatUtils.getDate(new Date()));
		DeployUtils.killProcesses(channel, user, machineAgentCommand, "machine agent");
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
		String command = DeployUtils.getAppDynamicsMachineAgentStartupCommand(user, machineAgentCommand);
		logger.debug(command);
		channel.executeNoWait(command);
	}

	public String getMachineAgentCommand() {
		return machineAgentCommand;
	}

	public void setMachineAgentCommand(String machineAgentCommand) {
		this.machineAgentCommand = machineAgentCommand;
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

	public String getTmpDir() {
		return tmpDir;
	}

	public void setTmpDir(String tmpDir) {
		this.tmpDir = tmpDir;
	}

	public String getLogDir() {
		return logDir;
	}

	public void setLogDir(String logDir) {
		this.logDir = logDir;
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

}
