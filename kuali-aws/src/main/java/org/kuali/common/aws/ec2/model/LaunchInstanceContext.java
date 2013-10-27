package org.kuali.common.aws.ec2.model;

import java.util.List;

import org.kuali.common.util.Assert;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.nullify.NullUtils;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.model.InstanceType;
import com.amazonaws.services.ec2.model.Tag;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

public final class LaunchInstanceContext {

	private final Regions region;
	private final String ami;
	private final String keyName;
	private final InstanceType type;
	private final List<String> securityGroups;
	private final List<Tag> tags;
	private final Optional<String> availabilityZone;
	private final int timeoutMillis;

	public static class Builder {

		// Required
		private final String ami;
		private final String keyName;

		// Optional
		private InstanceType type = InstanceType.C1Medium;
		private List<String> securityGroups = ImmutableList.of();
		private List<Tag> tags = ImmutableList.of();
		private Optional<String> availabilityZone = Optional.absent();
		private int timeoutMillis = FormatUtils.getMillisAsInt("15m"); // 15 minutes
		private Regions region = Regions.US_EAST_1;

		public Builder(String ami, String keyName) {
			this.ami = ami;
			this.keyName = keyName;
		}

		public Builder region(Regions region) {
			this.region = region;
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
		this.region = builder.region;
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

	public Regions getRegion() {
		return region;
	}

}
