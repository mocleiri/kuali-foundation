package org.kuali.common.devops.aws.sysadmin.model;

import java.util.List;

import org.kuali.common.util.Assert;

import com.google.common.collect.ImmutableList;

public final class Java {

	private final String home;
	private final List<String> options;
	private final boolean useNonBlockingEntropyGatheringDevice;

	public static class Builder {

		// Required
		private final String home;

		// Optional
		private List<String> options = ImmutableList.of();

		// -Djava.security.egd=file:/dev/./urandom
		private boolean useNonBlockingEntropyGatheringDevice = true;

		public Builder(String home) {
			this.home = home;
		}

		public Builder options(List<String> options) {
			this.options = options;
			return this;
		}

		public Builder useNonBlockingEntropyGatheringDevice(boolean useNonBlockingEntropyGatheringDevice) {
			this.useNonBlockingEntropyGatheringDevice = useNonBlockingEntropyGatheringDevice;
			return this;
		}

		public Java build() {
			Assert.noBlanks(home);
			Assert.noNulls(options);
			this.options = ImmutableList.copyOf(options);
			return new Java(this);
		}
	}

	private Java(Builder builder) {
		this.home = builder.home;
		this.options = builder.options;
		this.useNonBlockingEntropyGatheringDevice = builder.useNonBlockingEntropyGatheringDevice;
	}

	public List<String> getOptions() {
		return options;
	}

	public String getHome() {
		return home;
	}

	public boolean isUseNonBlockingEntropyGatheringDevice() {
		return useNonBlockingEntropyGatheringDevice;
	}

}
