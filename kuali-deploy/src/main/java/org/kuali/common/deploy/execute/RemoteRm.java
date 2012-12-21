package org.kuali.common.deploy.execute;

import java.util.List;

import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.UnixUtils;
import org.kuali.common.util.execute.Executable;

public class RemoteRm extends SecureBase implements Executable {

	String file;
	List<String> files;

	@Override
	public void execute() {
		List<String> merged = CollectionUtils.sortedMerge(files, file);
		int exitValue = UnixUtils.sshrm(user, hostname, merged);
		UnixUtils.validate(exitValue, "Error removing remote file", nonZeroExitValueMode);
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public List<String> getFiles() {
		return files;
	}

	public void setFiles(List<String> files) {
		this.files = files;
	}

}
