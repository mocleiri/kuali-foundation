package org.kuali.common.deploy.service;

import java.util.List;

import org.kuali.common.util.UnixCmds;
import org.kuali.common.util.secure.Result;
import org.kuali.common.util.secure.SecureChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

public class DefaultFileSystemAttendant implements FileSystemAttendant {

	private static final Logger logger = LoggerFactory.getLogger(DefaultFileSystemAttendant.class);

	UnixCmds cmds = new UnixCmds();
	SecureChannel channel;
	List<String> filesToDelete;
	List<String> directoriesToDelete;

	@Override
	public void clean() {
		deleteFiles(filesToDelete);
	}

	protected void deleteFiles(List<String> files) {
		if (CollectionUtils.isEmpty(files)) {
			return;
		}
		String command = cmds.rmrf(files);
		Result result = channel.executeCommand(command);
		ServiceUtils.logResult(result, logger);
		ServiceUtils.validateResult(result);
	}

	@Override
	public void prepare() {

	}

	public UnixCmds getCmds() {
		return cmds;
	}

	public void setCmds(UnixCmds cmds) {
		this.cmds = cmds;
	}

	public SecureChannel getChannel() {
		return channel;
	}

	public void setChannel(SecureChannel channel) {
		this.channel = channel;
	}

	public List<String> getFilesToDelete() {
		return filesToDelete;
	}

	public void setFilesToDelete(List<String> filesToDelete) {
		this.filesToDelete = filesToDelete;
	}

	public List<String> getDirectoriesToDelete() {
		return directoriesToDelete;
	}

	public void setDirectoriesToDelete(List<String> directoriesToDelete) {
		this.directoriesToDelete = directoriesToDelete;
	}

}
