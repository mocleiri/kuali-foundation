package org.kuali.common.deploy.execute;

import java.io.File;

import org.kuali.common.util.UnixUtils;
import org.kuali.common.util.execute.Executable;

public class CopyToRemoteDir extends SecureBase implements Executable {

	File localFile;
	String remoteDir;
	String owner;
	String group;

	@Override
	public void execute() {
		String destination = UnixUtils.getLocation(user, hostname, getRemoteFile());
		int scp = UnixUtils.scp(localFile, destination);
		UnixUtils.validate(scp, "Error copying local file to remote", nonZeroExitValueMode);
		if (owner != null && group != null) {
			int chown = UnixUtils.sshchown(user, hostname, owner, group, getRemoteFile());
			UnixUtils.validate(chown, "Error changing file ownership", nonZeroExitValueMode);
		}
	}

	protected String getRemoteFile() {
		return remoteDir + "/" + localFile.getName();
	}

	public File getLocalFile() {
		return localFile;
	}

	public void setLocalFile(File localFile) {
		this.localFile = localFile;
	}

	public String getRemoteDir() {
		return remoteDir;
	}

	public void setRemoteDir(String remoteDir) {
		this.remoteDir = remoteDir;
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
