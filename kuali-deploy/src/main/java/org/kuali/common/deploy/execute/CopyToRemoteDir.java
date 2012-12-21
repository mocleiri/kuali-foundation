package org.kuali.common.deploy.execute;

import java.io.File;

import org.kuali.common.util.UnixUtils;
import org.kuali.common.util.execute.Executable;

public class CopyToRemoteDir extends SecureBase implements Executable {

	File localFile;
	String remoteDir;

	@Override
	public void execute() {
		String file = remoteDir + "/" + localFile.getName();
		String destination = UnixUtils.getLocation(user, hostname, file);
		int exitValue = UnixUtils.scp(localFile, destination);
		UnixUtils.validate(exitValue, "Error copying local file to remote", nonZeroExitValueMode);
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

}
