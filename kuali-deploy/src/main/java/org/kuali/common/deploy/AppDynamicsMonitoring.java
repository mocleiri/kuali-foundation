package org.kuali.common.deploy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.codehaus.plexus.util.StringUtils;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.LoggerLevel;
import org.kuali.common.util.UnixCmds;
import org.kuali.common.util.UnixProcess;
import org.kuali.common.util.secure.SecureChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppDynamicsMonitoring implements Monitoring {

	private static final Logger logger = LoggerFactory.getLogger(AppDynamicsMonitoring.class);

	SecureChannel channel;
	UnixCmds unixCmds = new UnixCmds();
	String machineAgentCommand;
	String user;
	String group;
	String tmpDir;
	String logDir;

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
		List<UnixProcess> machineAgents = getMachineAgents(processes, machineAgentCommand);

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
		DeployUtils.executePathCommand(channel, unixCmds.rmrf(dirs), dirs, LoggerLevel.DEBUG);
		DeployUtils.executePathCommand(channel, unixCmds.mkdirp(dirs), dirs, LoggerLevel.DEBUG);
		DeployUtils.executePathCommand(channel, unixCmds.chownr(user, group, dirs), dirs, LoggerLevel.DEBUG);
	}

	@Override
	public void start() {
		logger.info("[appdynamics:start]    - {}", FormatUtils.getDate(new Date()));
		String command = getCommand();
		logger.debug(command);
		channel.executeNoWait(command);
	}

	protected String getCommand() {
		StringBuilder sb = new StringBuilder();
		sb.append("su");
		sb.append(" - ");
		sb.append(user);
		sb.append(" ");
		sb.append("--command");
		sb.append("=");
		sb.append("'");
		sb.append(unixCmds.nohup(machineAgentCommand));
		sb.append(" ");
		sb.append("&");
		sb.append("'");
		return sb.toString();
	}

	protected List<UnixProcess> getMachineAgents(List<UnixProcess> processes, String command) {
		List<UnixProcess> machineAgents = new ArrayList<UnixProcess>();
		for (UnixProcess process : processes) {
			if (StringUtils.equals(process.getCommand(), command)) {
				machineAgents.add(process);
			}
		}
		return machineAgents;
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

	public UnixCmds getUnixCmds() {
		return unixCmds;
	}

	public void setUnixCmds(UnixCmds cmds) {
		this.unixCmds = cmds;
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

}
