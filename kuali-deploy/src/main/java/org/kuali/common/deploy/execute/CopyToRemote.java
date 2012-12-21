package org.kuali.common.deploy.execute;

import java.io.File;

import org.kuali.common.util.UnixUtils;
import org.kuali.common.util.execute.Executable;

public class CopyToRemote extends SecureBase implements Executable {

	File localFile;
	String remoteFile;

	@Override
	public void execute() {
		String destination = UnixUtils.getLocation(user, hostname, remoteFile);
		int exitValue = UnixUtils.scp(localFile, destination);
		UnixUtils.validate(exitValue, "Error copying local file to remote", nonZeroExitValueMode);
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

}
