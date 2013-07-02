package org.kuali.common.deploy;

import java.util.List;

import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.secure.SecureChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SysAdminExecutable implements Executable {

	private static final Logger logger = LoggerFactory.getLogger(SysAdminExecutable.class);

	SecureChannel channel;
	List<String> commands;
	boolean skip;

	@Override
	public void execute() {
		if (skip) {
			logger.info("[sysadmin:skipped]");
			return;
		}
		if (CollectionUtils.isEmpty(commands)) {
			logger.info("[sysadmin:nocmds]");
			return;
		}
		long start = System.currentTimeMillis();
		logger.info("[sysadmin:begin]");
		logger.info("  executing {} commands", commands.size());
		for (String command : commands) {
			logger.debug(command);
			channel.executeCommand(command);
		}
		logger.info("[sysadmin:complete] - {}", FormatUtils.getTime(System.currentTimeMillis() - start));
	}

	public SecureChannel getChannel() {
		return channel;
	}

	public void setChannel(SecureChannel channel) {
		this.channel = channel;
	}

	public List<String> getCommands() {
		return commands;
	}

	public void setCommands(List<String> commands) {
		this.commands = commands;
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

}
