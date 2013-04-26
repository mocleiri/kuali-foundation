package org.kuali.common.deploy;

import java.io.IOException;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.UnixCmds;
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
		Result result = channel.executeCommand(command);
		ServiceUtils.validateResult(result);
		processResult(result);
	}

	protected void processResult(Result result) {
		try {
			List<String> lines = IOUtils.readLines(LocationUtils.getBufferedReaderFromString(result.getStdout()));
			logger.info("size=" + lines.size());
		} catch (IOException e) {
			throw new IllegalArgumentException("Unexpected IO error", e);
		}
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
