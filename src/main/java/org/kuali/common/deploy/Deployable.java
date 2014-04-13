package org.kuali.common.deploy;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.Assert;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.nullify.NullUtils;

import com.google.common.base.Optional;

public final class Deployable {

	private final String local;
	private final String remote;
	private final boolean exists;

	private final boolean filter;
	private final boolean required;
	private final Optional<String> permissions;

	public boolean isFilter() {
		return filter;
	}

	public String getLocal() {
		return local;
	}

	public String getRemote() {
		return remote;
	}

	public Optional<String> getPermissions() {
		return permissions;
	}

	public boolean isRequired() {
		return required;
	}

	public boolean isExists() {
		return exists;
	}

	public static class Builder {

		private final String local;
		private final String remote;
		private final boolean exists;

		private boolean filter = false;
		private boolean required = true;
		private Optional<String> permissions = Optional.absent();

		public Builder(String local, String remote) {
			this.local = local;
			this.remote = remote;
			this.exists = LocationUtils.exists(local);
		}

		public Builder filter(boolean filter) {
			this.filter = filter;
			return this;
		}

		public Builder required(boolean required) {
			this.required = required;
			return this;
		}

		/**
		 * Can't be the empty string or all whitespace. <code>null</code> and the magic strings "NONE" and "NULL" are all allowed and mean the same thing. (ie no explicit
		 * permissions will be set on the remote machine for this deployable).
		 */
		public Builder permissions(String permissions) {
			if (permissions != null) {
				Assert.noBlanks(permissions);
			}
			String trimmed = StringUtils.trimToNull(permissions);
			String converted = NullUtils.isNullOrNone(trimmed) ? null : trimmed;
			this.permissions = Optional.fromNullable(converted);
			return this;
		}

		public Deployable build() {
			Assert.noBlanks(local, remote);
			Assert.noNulls(permissions);
			if (required) {
				Assert.isTrue(exists, "[" + local + "] does not exist");
			}
			return new Deployable(this);
		}

	}

	private Deployable(Builder builder) {
		this.local = builder.local;
		this.remote = builder.remote;
		this.exists = builder.exists;
		this.filter = builder.filter;
		this.required = builder.required;
		this.permissions = builder.permissions;
	}

}
