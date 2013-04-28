package org.kuali.common.deploy;

import org.kuali.common.util.UnixCmds;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.secure.SecureChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SysAdminExecutable implements Executable {

	private static final Logger logger = LoggerFactory.getLogger(SysAdminExecutable.class);

	SecureChannel channel;
	UnixCmds cmds = new UnixCmds();
	String hostname;
	boolean skip;

	@Override
	public void execute() {

	}

}
