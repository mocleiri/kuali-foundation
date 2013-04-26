package org.kuali.common.deploy;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.kuali.common.util.UnixCmds;
import org.kuali.common.util.UnixProcess;
import org.kuali.common.util.secure.Result;
import org.kuali.common.util.secure.SecureChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppDynamicsMonitoring implements Monitoring {

	private static final Logger logger = LoggerFactory.getLogger(AppDynamicsMonitoring.class);

	UnixCmds unixCmds = new UnixCmds();
	String machineAgentCommand;
	String user;
	SecureChannel channel;

	@Override
	public void stop() {
		logger.info("Shutting down AppDynamics");
		String command = unixCmds.ps(user, true);
		logger.info("[" + command + "]");
		Result result = channel.executeCommand(command);
		ServiceUtils.validateResult(result);
		List<UnixProcess> processes = getUnixProcesses(result);
		logger.info("Processes: " + processes.size());
	}

	protected List<UnixProcess> getUnixProcesses(Result result) {
		List<String> lines = ServiceUtils.getOutputLines(result);
		List<UnixProcess> processes = new ArrayList<UnixProcess>();
		if (CollectionUtils.isEmpty(lines)) {
			throw new IllegalStateException("There should at least be a header line");
		}
		if (lines.size() == 1) {
			return processes;
		}
		for (int i = 1; i < lines.size(); i++) {
			String line = lines.get(i);
			UnixProcess process = getUnixProcess(line);
			processes.add(process);
		}
		return processes;
	}

	protected UnixProcess getUnixProcess(String line) {
		UnixProcess process = new UnixProcess();
		return process;
	}

	@Override
	public void prepare() {
		logger.info("Preparing AppDynamics");
	}

	@Override
	public void start() {
		logger.info("Starting AppDynamics");
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

}
