package org.kuali.common.deploy;

import java.util.List;

import org.kuali.common.util.Assert;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.secure.channel.SecureChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableList;

public class SysAdminExecutable implements Executable {

	private static final Logger logger = LoggerFactory.getLogger(SysAdminExecutable.class);

	public SysAdminExecutable(SecureChannel channel, List<String> commands, boolean skip) {
		Assert.noNulls(channel, commands);
		this.channel = channel;
		this.commands = ImmutableList.copyOf(commands);
		this.skip = skip;
	}

	private final SecureChannel channel;
	private final List<String> commands;
	private final boolean skip;

	@Override
	public void execute() {
		if (skip) {
			logger.info("[sysadmin:skipped]");
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

	public List<String> getCommands() {
		return commands;
	}

	public boolean isSkip() {
		return skip;
	}

}
