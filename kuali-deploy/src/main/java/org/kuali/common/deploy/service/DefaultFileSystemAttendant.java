package org.kuali.common.deploy.service;

import java.util.List;

import org.kuali.common.util.UnixCmds;
import org.kuali.common.util.secure.SecureChannel;

public class DefaultFileSystemAttendant implements FileSystemAttendant {

	UnixCmds cmds = new UnixCmds();
	SecureChannel channel;
	List<String> filesToDelete;
	List<String> directoriesToDelete;

	@Override
	public void clean() {

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
