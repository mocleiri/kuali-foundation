package org.kuali.common.deploy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.codehaus.plexus.util.StringUtils;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.LoggerLevel;
import org.kuali.common.util.UnixCmds;
import org.kuali.common.util.UnixProcess;
import org.kuali.common.util.secure.Result;
import org.kuali.common.util.secure.SecureChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppDynamicsMonitoring implements Monitoring {

	private static final Logger logger = LoggerFactory.getLogger(AppDynamicsMonitoring.class);
	private static final String CMD = "CMD";

	UnixCmds unixCmds = new UnixCmds();
	String machineAgentCommand;
	String user;
	String group;
	SecureChannel channel;
	String tmpDir;
	String logDir;

	@Override
	public void stop() {
		logger.info("[appdynamics:shutdown] - {}", FormatUtils.getDate(new Date()));
		List<UnixProcess> processes = getUnixProcesses(user);

		// No existing processes, we are done
		if (processes.size() == 0) {
			logger.info("No running processes for user [{}]", user);
			return;
		}

		// Figure out if any of the running processes are machine agent
		List<UnixProcess> machineAgents = getMachineAgents(processes, machineAgentCommand);

		if (CollectionUtils.isEmpty(machineAgents)) {
			// Nothing to do
			logger.info("  no machine agents detected. total running processes: [{}]", processes.size());
		} else {
			// Kill the machine agent process
			for (UnixProcess machineAgent : machineAgents) {
				logger.info("  killing machine agent process - [{}]", machineAgent.getProcessId());
				kill(machineAgent);
			}
		}
	}

	@Override
	public void prepare() {
		logger.info("[appdynamics:prepare]  - {}", FormatUtils.getDate(new Date()));
		List<String> dirs = Arrays.asList(tmpDir, logDir);
		ServiceUtils.executePathCommand(channel, unixCmds.rmrf(dirs), dirs, LoggerLevel.DEBUG);
		ServiceUtils.executePathCommand(channel, unixCmds.mkdirp(dirs), dirs, LoggerLevel.DEBUG);
		ServiceUtils.executePathCommand(channel, unixCmds.chownr(user, group, dirs), dirs, LoggerLevel.DEBUG);
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

	protected void kill(UnixProcess process) {
		String command = unixCmds.kill(process.getProcessId());
		Result result = channel.executeCommand(command);
		ServiceUtils.logResult(result, logger, LoggerLevel.DEBUG);
		ServiceUtils.validateResult(result);
	}

	protected List<UnixProcess> getUnixProcesses(String user) {
		String command = unixCmds.psf(user);
		Result result = channel.executeCommand(command);
		return getUnixProcesses(result);

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

	protected List<UnixProcess> getUnixProcesses(Result result) {
		// Convert stdout to a list of strings
		List<String> lines = ServiceUtils.getOutputLines(result);

		// Something has gone wrong
		if (CollectionUtils.isEmpty(lines)) {
			throw new IllegalStateException("There should be a header line");
		}

		// If there are no processes running, exit value is 1
		if (lines.size() == 1 && result.getExitValue() == 1) {
			// return an empty list
			return Collections.emptyList();
		}

		// Make sure exit value was zero
		ServiceUtils.validateResult(result);

		// Need the header line in order to parse the process lines
		String header = lines.get(0);

		// Setup some storage for the list of running processes
		List<UnixProcess> processes = new ArrayList<UnixProcess>();

		// Convert each line into a UnixProcess pojo
		for (int i = 1; i < lines.size(); i++) {

			// Extract a line
			String line = lines.get(i);

			// Convert to a pojo
			UnixProcess process = getUnixProcess(header, line);

			// Add to the list
			processes.add(process);
		}

		// return what we've found
		return processes;
	}

	/**
	 * Output looks like this:
	 * 
	 * <pre>
	 *   UID        PID  PPID  C STIME TTY          TIME CMD
	 * 	 tomcat   15461 15460  0 22:51 pts/0    00:00:00 -bash
	 * 	 tomcat   15480 15461  0 22:52 pts/0    00:00:02 java -jar /usr/local/machine-agent/machineagent.jar
	 * </pre>
	 */
	protected UnixProcess getUnixProcess(String header, String line) {
		// Split the strings up into tokens
		String[] tokens = StringUtils.split(line, " ");
		// First token is the user id
		String userId = StringUtils.trim(tokens[0]);
		// Second token is the process id
		String processId = StringUtils.trim(tokens[1]);
		// The command starts where "CMD" starts in the header line
		int pos = header.indexOf(CMD);
		if (pos == -1) {
			throw new IllegalStateException(line + " does not contain [" + CMD + "]");
		}
		String command = StringUtils.trim(StringUtils.substring(line, pos));

		//
		UnixProcess process = new UnixProcess();
		process.setUserId(userId);
		process.setProcessId(Integer.parseInt(processId));
		process.setCommand(command);
		return process;
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
