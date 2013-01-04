package org.kuali.common.deploy.service;

import java.util.Arrays;
import java.util.List;

import org.kuali.common.util.UnixCmds;
import org.kuali.common.util.secure.RemoteFile;
import org.kuali.common.util.secure.Result;
import org.kuali.common.util.secure.SecureChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

public class DefaultFileSystemAttendant implements FileSystemAttendant {

	private static final Logger logger = LoggerFactory.getLogger(DefaultFileSystemAttendant.class);
	private static final String TRAVERSE_SYMBOLIC_LINKS = "-L";

	UnixCmds cmds = new UnixCmds();
	SecureChannel channel;
	List<String> filesToDelete;
	List<String> directoriesToDelete;
	List<String> directoriesToCreate;
	List<String> directoriesToChown;
	List<String> jsps;
	String jspDir;
	String owner;
	String group;

	@Override
	public void clean() {
		executeCommand(cmds.rmrf(filesToDelete), filesToDelete);
		executeCommand(cmds.rmrf(directoriesToDelete), directoriesToDelete);
	}

	@Override
	public void prepare() {
		executeCommand(cmds.mkdirp(directoriesToCreate), directoriesToCreate);
		copyJsps();
		executeCommand(cmds.chownr(Arrays.asList(TRAVERSE_SYMBOLIC_LINKS), owner, group, directoriesToChown), directoriesToChown);
	}

	protected void copyJsps() {
		if (CollectionUtils.isEmpty(jsps)) {
			return;
		}
		RemoteFile destination = new RemoteFile(jspDir);
		for (String jsp : jsps) {
			logger.info("[{}] -> [{}]", jsp, jspDir);
			channel.copyLocationToDirectory(jsp, destination);
		}
	}

	protected void executeCommand(String command, List<String> paths) {
		if (CollectionUtils.isEmpty(paths)) {
			return;
		}
		Result result = channel.executeCommand(command);
		ServiceUtils.logResult(result, logger);
		ServiceUtils.validateResult(result);
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

	public List<String> getDirectoriesToCreate() {
		return directoriesToCreate;
	}

	public void setDirectoriesToCreate(List<String> directoriesToCreate) {
		this.directoriesToCreate = directoriesToCreate;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public List<String> getDirectoriesToChown() {
		return directoriesToChown;
	}

	public void setDirectoriesToChown(List<String> directoriesToChown) {
		this.directoriesToChown = directoriesToChown;
	}

	public List<String> getJsps() {
		return jsps;
	}

	public void setJsps(List<String> jsps) {
		this.jsps = jsps;
	}

	public String getJspDir() {
		return jspDir;
	}

	public void setJspDir(String jspDir) {
		this.jspDir = jspDir;
	}

}
