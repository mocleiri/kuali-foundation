/**
 * Copyright 2010-2013 The Kuali Foundation
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
package org.kuali.common.util.secure;

/**
 * @deprecated
 */
@Deprecated
public class RemoteFile {

	String absolutePath;
	Integer groupId;
	Integer userId;
	Integer permissions;
	Long size;
	boolean directory;

	/**
	 * @deprecated
	 */
	@Deprecated
	Status status = Status.DEFAULT_REMOTE_FILE_STATUS;

	public RemoteFile() {
		this(null);
	}

	public RemoteFile(String absolutePath) {
		this.absolutePath = absolutePath;
	}

	public String getAbsolutePath() {
		return absolutePath;
	}

	public void setAbsolutePath(String absolutePath) {
		this.absolutePath = absolutePath;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getPermissions() {
		return permissions;
	}

	public void setPermissions(Integer permissions) {
		this.permissions = permissions;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public boolean isDirectory() {
		return directory;
	}

	public void setDirectory(boolean directory) {
		this.directory = directory;
	}

	/**
	 * @deprecated
	 */
	@Deprecated
	public Status getStatus() {
		return status;
	}

	/**
	 * @deprecated
	 */
	@Deprecated
	public void setStatus(Status status) {
		this.status = status;
	}

}
