package org.kuali.common.aws.ec2.model;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.Assert;
import org.kuali.common.util.FormatUtils;

import com.amazonaws.services.ec2.model.InstanceType;
import com.amazonaws.services.ec2.model.Tag;
import com.google.common.collect.ImmutableList;

public final class LaunchInstanceRequest {

	private final String ami;
	private final String key;
	private final InstanceType type;
	private final List<String> securityGroups;
	private final List<Tag> tags;
	private final WaitControl waitControl;

	public static class Builder {

		public static final String DEFAULT_WAIT_FOR_STATE = InstanceStates.RUNNING.getValue(); // "running"
		public static final int DEFAULT_TIMEOUT_MILLIS = FormatUtils.getMillisAsInt("15m"); // 15 minutes

		private final String ami;
		private final String key;
		private InstanceType type = InstanceType.C1Medium;
		private List<String> securityGroups = ImmutableList.of();
		private List<Tag> tags = ImmutableList.of();
		private WaitControl waitControl = new WaitControl.Builder(DEFAULT_WAIT_FOR_STATE, DEFAULT_TIMEOUT_MILLIS).build();

		public Builder(String ami, String key) {
			this(ami, key, DEFAULT_TIMEOUT_MILLIS);
		}

		public Builder(String ami, String key, String timeout) {
			this(ami, key, FormatUtils.getMillisAsInt(timeout));
		}

		public Builder(String ami, String key, int timeoutMillis) {
			this.ami = ami;
			this.key = key;
			this.waitControl = new WaitControl.Builder(DEFAULT_WAIT_FOR_STATE, timeoutMillis).build();
		}

		public Builder waitControl(WaitControl waitControl) {
			this.waitControl = waitControl;
			return this;
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

		public Builder tags(List<Tag> tags) {
			this.tags = tags;
			return this;
		}

		public LaunchInstanceRequest build() {
			Assert.noBlanks(ami, key);
			Assert.noNulls(type, securityGroups, tags, waitControl);
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
		this.waitControl = builder.waitControl;
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

	public List<Tag> getTags() {
		return tags;
	}

	public WaitControl getWaitControl() {
		return waitControl;
	}

}
