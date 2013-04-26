package org.kuali.common.deploy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppDynamicsMonitoring implements Monitoring {

	private static final Logger logger = LoggerFactory.getLogger(AppDynamicsMonitoring.class);

	String machineAgentCommand;
	String user;

	@Override
	public void stop() {
		logger.info("Shutting down AppDynamics");
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

}
