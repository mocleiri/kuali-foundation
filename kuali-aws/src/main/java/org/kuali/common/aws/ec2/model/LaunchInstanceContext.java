package org.kuali.common.aws.ec2.model;

import java.util.List;

import org.kuali.common.util.Assert;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.nullify.NullUtils;

import com.amazonaws.services.ec2.model.InstanceType;
import com.amazonaws.services.ec2.model.Tag;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

public final class LaunchInstanceContext {

	private final String ami;
	private final String keyName;
	private final InstanceType type;
	private final List<String> securityGroups;
	private final List<Tag> tags;
	private final Optional<String> availabilityZone;
	private final int timeoutMillis;
	private final boolean preventTermination;
	private final boolean ebsOptimized;
	private final boolean enableMonitoring;
	private final Optional<RootVolume> rootVolume;

	public static class Builder {

		// Required
		private final String ami;
		private final String keyName;

		// Optional
		private InstanceType type = InstanceType.M1Medium;
		private List<String> securityGroups = ImmutableList.of();
		private List<Tag> tags = ImmutableList.of();
		private Optional<String> availabilityZone = Optional.absent(); // If not provided, Amazon picks one for you
		private int timeoutMillis = FormatUtils.getMillisAsInt("15m");
		private boolean preventTermination = false;
		private boolean ebsOptimized = false;
		private boolean enableMonitoring = false;
		private Optional<RootVolume> rootVolume = Optional.absent(); // Default root volume definition is provided by the AMI

		public Builder(String ami, String keyName) {
			this.ami = ami;
			this.keyName = keyName;
		}

		public Builder rootVolume(RootVolume rootVolume) {
			this.rootVolume = Optional.of(rootVolume);
			return this;
		}

		public Builder enableMonitoring(boolean enableMonitoring) {
			this.enableMonitoring = enableMonitoring;
			return this;
		}

		public Builder ebsOptimized(boolean ebsOptimized) {
			this.ebsOptimized = ebsOptimized;
			return this;
		}

		public Builder preventTermination(boolean preventTermination) {
			this.preventTermination = preventTermination;
			return this;
		}

		public Builder availabilityZone(String availabilityZone) {
			this.availabilityZone = Optional.fromNullable(NullUtils.trimToNull(availabilityZone));
			return this;
		}

		public Builder timeoutMillis(int timeoutMillis) {
			this.timeoutMillis = timeoutMillis;
			return this;
		}

		public Builder type(InstanceType type) {
			this.type = type;
			return this;
		}

		public Builder securityGroups(List<String> securityGroups) {
			this.securityGroups = securityGroups;
			return this;
		}

		public Builder tags(List<Tag> tags) {
			this.tags = tags;
			return this;
		}

		public LaunchInstanceContext build() {
			Assert.noBlanks(ami, keyName);
			Assert.noNulls(type, securityGroups, tags, availabilityZone);
			Assert.notNegative(timeoutMillis);
			this.securityGroups = ImmutableList.copyOf(securityGroups);
			this.tags = ImmutableList.copyOf(tags);
			return new LaunchInstanceContext(this);
		}

	}

	private LaunchInstanceContext(Builder builder) {
		this.ami = builder.ami;
		this.keyName = builder.keyName;
		this.type = builder.type;
		this.securityGroups = builder.securityGroups;
		this.tags = builder.tags;
		this.availabilityZone = builder.availabilityZone;
		this.timeoutMillis = builder.timeoutMillis;
		this.preventTermination = builder.preventTermination;
		this.ebsOptimized = builder.ebsOptimized;
		this.enableMonitoring = builder.enableMonitoring;
		this.rootVolume = builder.rootVolume;
	}

	public String getAmi() {
		return ami;
	}

	public String getKeyName() {
		return keyName;
	}

	public InstanceType getType() {
		return type;
	}

	public List<String> getSecurityGroups() {
		return securityGroups;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public Optional<String> getAvailabilityZone() {
		return availabilityZone;
	}

	public int getTimeoutMillis() {
		return timeoutMillis;
	}

	public boolean isPreventTermination() {
		return preventTermination;
	}

	public boolean isEbsOptimized() {
		return ebsOptimized;
	}

	public boolean isEnableMonitoring() {
		return enableMonitoring;
	}

	public Optional<RootVolume> getRootVolume() {
		return rootVolume;
	}

}
