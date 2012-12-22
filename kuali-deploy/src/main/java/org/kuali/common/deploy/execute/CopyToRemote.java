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
		int scp = UnixUtils.scp(localFile, destination);
		UnixUtils.validate(scp, "Error copying local file to remote", nonZeroExitValueMode);
		if (owner != null && group != null) {
			int chown = UnixUtils.sshchown(user, hostname, owner, group, getRemoteFile());
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
