package org.kuali.common.deploy;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.UnixProcess;
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
	String monitoringJavaOpts;

	@Override
	public void stop() {
		logger.info("[appdynamics:stop] - {}", FormatUtils.getDate(new Date()));
		List<UnixProcess> processes = DeployUtils.getUnixProcesses(channel, user);

		// No existing processes, we are done
		if (processes.size() == 0) {
			logger.info("No running processes for user [{}]", user);
			return;
		}

		// Figure out if any of the running processes are machine agent
		List<UnixProcess> machineAgents = DeployUtils.getMatchingProcesses(processes, machineAgentCommand);

		if (CollectionUtils.isEmpty(machineAgents)) {
			// Nothing to do
			logger.info("  no machine agents detected. total running processes - {}", processes.size());
		} else {
			// Kill the machine agent process
			for (UnixProcess machineAgent : machineAgents) {
				logger.info("  killing machine agent - [{}]", machineAgent.getProcessId());
				DeployUtils.kill(channel, machineAgent);
			}
		}
	}

	@Override
	public void prepare() {
		logger.info("[appdynamics:prepare]  - {}", FormatUtils.getDate(new Date()));
		List<String> dirs = Arrays.asList(tmpDir, logDir);
		DeployUtils.delete(channel, dirs);
		DeployUtils.mkdirs(channel, dirs);
		DeployUtils.chown(channel, user, group, dirs);
	}

	@Override
	public void start() {
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
		return monitoringJavaOpts;
	}

	public void setMonitoringJavaOpts(String monitoringJavaOpts) {
		this.monitoringJavaOpts = monitoringJavaOpts;
	}

}
