package org.kuali.common.deploy.execute;

import java.io.File;

import org.kuali.common.util.UnixUtils;
import org.kuali.common.util.execute.Executable;

public class ScpFileToRemote extends SecureBase implements Executable {

	File source;
	String destinationDirectory;

	@Override
	public void execute() {
		String file = destinationDirectory + File.separator + source.getName();
		String destination = UnixUtils.getLocation(user, hostname, file);
		int exitValue = UnixUtils.scp(source, destination);
		UnixUtils.validate(exitValue, "Error copying local file to remote", nonZeroExitValueMode);
	}

}
