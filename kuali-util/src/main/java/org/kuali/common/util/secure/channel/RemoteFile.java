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
package org.kuali.common.util.secure.channel;

import org.kuali.common.util.Assert;

import com.google.common.base.Optional;

public final class RemoteFile {

	private final String absolutePath;
	private final Optional<Integer> groupId;
	private final Optional<Integer> userId;
	private final Optional<Integer> permissions;
	private final Optional<Long> size;
	private final boolean directory;
	private final Optional<Status> status;

	public static class Builder {

		// Required
		private final String absolutePath;

		// Optional
		private Optional<Integer> groupId = Optional.absent();
		private Optional<Integer> userId = Optional.absent();
		private Optional<Integer> permissions = Optional.absent();
		private Optional<Long> size = Optional.absent();
		private boolean directory = false;
		private Optional<Status> status = Optional.absent();

		public Builder(String absolutePath) {
			this.absolutePath = absolutePath;
		}

		public Builder clone(RemoteFile other) {
			this.groupId = other.groupId;
			this.userId = other.userId;
			this.permissions = other.permissions;
			this.size = other.size;
			this.directory = other.directory;
			this.status = other.status;
			return this;
		}

		public Builder groupId(int groupId) {
			this.groupId = Optional.of(groupId);
			return this;
		}

		public Builder userId(int userId) {
			this.userId = Optional.of(userId);
			return this;
		}

		public Builder permissions(int permissions) {
			this.permissions = Optional.of(permissions);
			return this;
		}

		public Builder size(long size) {
			this.size = Optional.of(size);
			return this;
		}

		public Builder directory(boolean directory) {
			this.directory = directory;
			return this;
		}

		public Builder status(Status status) {
			this.status = Optional.of(status);
			return this;
		}

		public RemoteFile build() {
			Assert.noBlanks(absolutePath);
			Assert.noNulls(groupId, userId, permissions, size, directory, status);
			if (size.isPresent()) {
				Assert.isTrue(size.get() >= 0, "size is negative");
			}
			return new RemoteFile(this);
		}

	}

	private RemoteFile(Builder builder) {
		this.absolutePath = builder.absolutePath;
		this.groupId = builder.groupId;
		this.userId = builder.userId;
		this.permissions = builder.permissions;
		this.size = builder.size;
		this.directory = builder.directory;
		this.status = builder.status;
	}

	public String getAbsolutePath() {
		return absolutePath;
	}

	public Optional<Integer> getGroupId() {
		return groupId;
	}

	public Optional<Integer> getUserId() {
		return userId;
	}

	public Optional<Integer> getPermissions() {
		return permissions;
	}

	public Optional<Long> getSize() {
		return size;
	}

	public boolean isDirectory() {
		return directory;
	}

	public Optional<Status> getStatus() {
		return status;
	}

}
