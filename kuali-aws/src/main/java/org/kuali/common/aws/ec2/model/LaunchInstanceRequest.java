package org.kuali.common.aws.ec2.model;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.Assert;

import com.amazonaws.services.ec2.model.InstanceType;
import com.google.common.collect.ImmutableList;

public final class LaunchInstanceRequest {

	private final String ami;
	private final String key;
	private final InstanceType type;
	private final List<String> securityGroups;
	private final List<String> tags;

	public static class Builder {

		private final String ami;
		private final String key;
		private InstanceType type = InstanceType.C1Medium;
		private List<String> securityGroups = ImmutableList.of();
		private List<String> tags = ImmutableList.of();

		public Builder(String ami, String key) {
			this.ami = ami;
			this.key = key;
		}

		public Builder type(InstanceType type) {
			this.type = type;
			return this;
		}

		public Builder type(String type) {
			this.type = InstanceType.valueOf(StringUtils.trimToNull(type));
			return this;
		}

		public Builder securityGroups(List<String> securityGroups) {
			this.securityGroups = securityGroups;
			return this;
		}

		public Builder tags(List<String> tags) {
			this.tags = tags;
			return this;
		}

		public LaunchInstanceRequest build() {
			Assert.noBlanks(ami, key);
			Assert.noNulls(type, securityGroups, tags);
			this.securityGroups = ImmutableList.copyOf(securityGroups);
			this.tags = ImmutableList.copyOf(tags);
			return new LaunchInstanceRequest(this);
		}

	}

	private LaunchInstanceRequest(Builder builder) {
		this.ami = builder.ami;
		this.key = builder.key;
		this.type = builder.type;
		this.securityGroups = builder.securityGroups;
		this.tags = builder.tags;
	}

	public String getAmi() {
		return ami;
	}

	public String getKey() {
		return key;
	}

	public InstanceType getType() {
		return type;
	}

	public List<String> getSecurityGroups() {
		return securityGroups;
	}

	public List<String> getTags() {
		return tags;
	}

}
