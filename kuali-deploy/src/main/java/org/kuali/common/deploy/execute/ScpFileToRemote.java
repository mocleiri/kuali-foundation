package org.kuali.common.deploy.execute;

import java.io.File;

import org.kuali.common.util.UnixUtils;
import org.kuali.common.util.execute.Executable;

public class ScpFileToRemote extends SecureBase implements Executable {

	File localFile;
	String remoteFile;

	@Override
	public void execute() {
		String destination = UnixUtils.getLocation(user, hostname, remoteFile);
		int exitValue = UnixUtils.scp(localFile, destination);
		UnixUtils.validate(exitValue, "Error copying local file to remote", nonZeroExitValueMode);
	}

}
