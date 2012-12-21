package org.kuali.common.deploy.execute;

import java.io.File;

import org.kuali.common.util.UnixUtils;
import org.kuali.common.util.execute.Executable;

public class CopyToRemote extends SecureBase implements Executable {

	File localFile;
	String remoteFile;
	String owner;
	String group;

	@Override
	public void execute() {
		String destination = UnixUtils.getLocation(user, hostname, remoteFile);
		int exitValue = UnixUtils.scp(localFile, destination);
		UnixUtils.validate(exitValue, "Error copying local file to remote", nonZeroExitValueMode);
		if (owner != null && group != null) {
			int chown = UnixUtils.sshchown(hostname, owner, group, remoteFile);
			UnixUtils.validate(chown, "Error changing file ownership", nonZeroExitValueMode);
		}
	}

	public File getLocalFile() {
		return localFile;
	}

	public void setLocalFile(File localFile) {
		this.localFile = localFile;
	}

	public String getRemoteFile() {
		return remoteFile;
	}

	public void setRemoteFile(String remoteFile) {
		this.remoteFile = remoteFile;
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

}
