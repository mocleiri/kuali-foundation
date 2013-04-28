package org.kuali.common.deploy;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
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
		long begin = System.currentTimeMillis();
		logger.info("[sysadmin:begin] - {}", FormatUtils.getDate(new Date(begin)));
		for (String command : commands) {
			logger.info(command);
			channel.executeCommand(command);
		}
		long end = System.currentTimeMillis();
		String elapsed = FormatUtils.getTime(end - begin);
		logger.info("[sysadmin:complete] - {} - {}", FormatUtils.getDate(new Date(end)), elapsed);

	}

}
