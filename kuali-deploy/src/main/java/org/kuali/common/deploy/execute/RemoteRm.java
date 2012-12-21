package org.kuali.common.deploy.execute;

import org.kuali.common.util.UnixUtils;
import org.kuali.common.util.execute.Executable;

public class RemoteRm extends SecureBase implements Executable {

	String file;

	@Override
	public void execute() {
		int exitValue = UnixUtils.sshrm(user, hostname, file);
		UnixUtils.validate(exitValue, "Error removing remote file", nonZeroExitValueMode);
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

}
