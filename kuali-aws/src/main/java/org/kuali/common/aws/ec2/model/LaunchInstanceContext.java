package org.kuali.common.aws.ec2.model;

import java.util.List;

import org.kuali.common.util.Assert;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.nullify.NullUtils;
import org.kuali.common.util.wait.WaitContext;

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
	private final Optional<WaitContext> waitContext;
	private final InstanceStateEnum targetState;
	private final Reachability targetReachability;

	public static class Builder {

		public static final long DEFAULT_TIMEOUT_MILLIS = FormatUtils.getMillis("15m"); // 15 minutes

		// Required
		private final String ami;
		private final String keyName;

		// Optional
		private InstanceType type = InstanceType.C1Medium;
		private List<String> securityGroups = ImmutableList.of();
		private List<Tag> tags = ImmutableList.of();
		private Optional<WaitContext> waitContext = Optional.of(new WaitContext.Builder(DEFAULT_TIMEOUT_MILLIS).build());
		private Optional<String> availabilityZone = Optional.absent();
		private InstanceStateEnum targetState = InstanceStateEnum.RUNNING;
		private Reachability targetReachability = Reachability.OK;

		public Builder(String ami, String keyName) {
			this(ami, keyName, DEFAULT_TIMEOUT_MILLIS);
		}

		public Builder(String ami, String keyName, long timeoutMillis) {
			this.ami = ami;
			this.keyName = keyName;
			this.waitContext = Optional.of(new WaitContext.Builder(timeoutMillis).build());
		}

		public Builder targetReachability(Reachability targetReachability) {
			this.targetReachability = targetReachability;
			return this;
		}

		public Builder targetState(InstanceStateEnum targetState) {
			this.targetState = targetState;
			return this;
		}

		public Builder availabilityZone(String availabilityZone) {
			this.availabilityZone = Optional.fromNullable(NullUtils.trimToNull(availabilityZone));
			return this;
		}

		public Builder waitContext(WaitContext waitContext) {
			this.waitContext = Optional.of(waitContext);
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
			Assert.noNulls(type, securityGroups, tags, waitContext, availabilityZone, targetState, targetReachability);
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
		this.waitContext = builder.waitContext;
		this.availabilityZone = builder.availabilityZone;
		this.targetState = builder.targetState;
		this.targetReachability = builder.targetReachability;
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

	public Optional<WaitContext> getWaitContext() {
		return waitContext;
	}

	public InstanceStateEnum getTargetState() {
		return targetState;
	}

	public Reachability getTargetReachability() {
		return targetReachability;
	}

}
