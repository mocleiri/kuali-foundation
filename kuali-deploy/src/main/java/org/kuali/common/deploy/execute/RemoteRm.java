/**
 * Copyright 2004-2012 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
