/**
 * Copyright 2004-2014 The Kuali Foundation
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
package org.kuali.common.aws.ec2.model;

import static com.google.common.base.Optional.absent;
import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import org.kuali.common.aws.ec2.model.security.KualiSecurityGroup;
import org.kuali.common.core.build.ValidatingBuilder;
import org.kuali.common.core.ssh.PublicKey;
import org.kuali.common.util.FormatUtils;

import com.amazonaws.services.ec2.model.BlockDeviceMapping;
import com.amazonaws.services.ec2.model.InstanceType;
import com.amazonaws.services.ec2.model.Tag;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

public final class LaunchInstanceContext {

	private final String ami;

	// To launch an instance the only part of the KeyPair object that is *required* is the key name
	// If you supply the name only (ie no public key), you must have already registered a key pair with Amazon under that name
	// If you supply both the name and the public key, the launch routine automatically registers a key pair with Amazon
	private final PublicKey publicKey;
	private final InstanceType type;
	private final ImmutableList<KualiSecurityGroup> securityGroups;

	// If true, permissions on any existing security groups are overridden by the permissions from the security groups provided here.
	// This happens prior to the instance being launched
	private final boolean overrideExistingSecurityGroupPermissions;
	private final ImmutableList<Tag> tags;
	private final Optional<String> availabilityZone;
	private final int timeoutMillis;
	private final boolean preventTermination;
	private final boolean ebsOptimized;
	private final boolean enableMonitoring;

	// Default root volume size is provided by the AMI
	private final Optional<RootVolume> rootVolume;
	private final ImmutableList<BlockDeviceMapping> additionalMappings;

	private LaunchInstanceContext(Builder builder) {
		this.ami = builder.ami;
		this.publicKey = builder.publicKey;
		this.type = builder.type;
		this.securityGroups = ImmutableList.copyOf(builder.securityGroups);
		this.overrideExistingSecurityGroupPermissions = builder.overrideExistingSecurityGroupPermissions;
		this.tags = ImmutableTag.copyOf(builder.tags);
		this.availabilityZone = builder.availabilityZone;
		this.timeoutMillis = builder.timeoutMillis;
		this.preventTermination = builder.preventTermination;
		this.ebsOptimized = builder.ebsOptimized;
		this.enableMonitoring = builder.enableMonitoring;
		this.rootVolume = builder.rootVolume;
		this.additionalMappings = ImmutableBlockDeviceMapping.copyOf(builder.additionalMappings);
	}

	public static Builder builder(String ami, PublicKey publicKey) {
		return new Builder(ami, publicKey);
	}

	public static class Builder extends ValidatingBuilder<LaunchInstanceContext> {

		// Required
		private final String ami;
		private final PublicKey publicKey;

		// Optional
		private InstanceType type = InstanceType.M3Medium;
		private List<KualiSecurityGroup> securityGroups = newArrayList();
		private boolean overrideExistingSecurityGroupPermissions;
		private List<Tag> tags = newArrayList();
		private Optional<String> availabilityZone = absent();
		private int timeoutMillis = FormatUtils.getMillisAsInt("15m");
		private boolean preventTermination = false;
		private boolean ebsOptimized = false;
		private boolean enableMonitoring = false;
		private Optional<RootVolume> rootVolume = absent();
		private List<BlockDeviceMapping> additionalMappings = newArrayList();

		public Builder(String ami, PublicKey publicKey) {
			this.ami = ami;
			this.publicKey = publicKey;
		}

		public Builder withType(InstanceType type) {
			this.type = type;
			return this;
		}

		public Builder withAdditionalMappings(List<BlockDeviceMapping> additionalMappings) {
			this.additionalMappings = additionalMappings;
			return this;
		}

		public Builder withSecurityGroups(List<KualiSecurityGroup> securityGroups) {
			this.securityGroups = securityGroups;
			return this;
		}

		public Builder withSecurityGroup(KualiSecurityGroup securityGroup) {
			return withSecurityGroups(ImmutableList.of(securityGroup));
		}

		public Builder withOverrideExistingSecurityGroupPermissions(boolean overrideExistingSecurityGroupPermissions) {
			this.overrideExistingSecurityGroupPermissions = overrideExistingSecurityGroupPermissions;
			return this;
		}

		public Builder withTags(List<Tag> tags) {
			this.tags = tags;
			return this;
		}

		public Builder withAvailabilityZone(Optional<String> availabilityZone) {
			this.availabilityZone = availabilityZone;
			return this;
		}

		public Builder withTimeoutMillis(int timeoutMillis) {
			this.timeoutMillis = timeoutMillis;
			return this;
		}

		public Builder withPreventTermination(boolean preventTermination) {
			this.preventTermination = preventTermination;
			return this;
		}

		public Builder withEbsOptimized(boolean ebsOptimized) {
			this.ebsOptimized = ebsOptimized;
			return this;
		}

		public Builder withEnableMonitoring(boolean enableMonitoring) {
			this.enableMonitoring = enableMonitoring;
			return this;
		}

		public Builder withRootVolume(Optional<RootVolume> rootVolume) {
			this.rootVolume = rootVolume;
			return this;
		}

		public Builder withRootVolume(RootVolume rootVolume) {
			return withRootVolume(Optional.of(rootVolume));
		}

		@Override
		public LaunchInstanceContext build() {
			return validate(new LaunchInstanceContext(this));
		}

		public InstanceType getType() {
			return type;
		}

		public void setType(InstanceType type) {
			this.type = type;
		}

		public List<KualiSecurityGroup> getSecurityGroups() {
			return securityGroups;
		}

		public void setSecurityGroups(List<KualiSecurityGroup> securityGroups) {
			this.securityGroups = securityGroups;
		}

		public boolean isOverrideExistingSecurityGroupPermissions() {
			return overrideExistingSecurityGroupPermissions;
		}

		public void setOverrideExistingSecurityGroupPermissions(boolean overrideExistingSecurityGroupPermissions) {
			this.overrideExistingSecurityGroupPermissions = overrideExistingSecurityGroupPermissions;
		}

		public List<Tag> getTags() {
			return tags;
		}

		public void setTags(List<Tag> tags) {
			this.tags = tags;
		}

		public Optional<String> getAvailabilityZone() {
			return availabilityZone;
		}

		public void setAvailabilityZone(Optional<String> availabilityZone) {
			this.availabilityZone = availabilityZone;
		}

		public int getTimeoutMillis() {
			return timeoutMillis;
		}

		public void setTimeoutMillis(int timeoutMillis) {
			this.timeoutMillis = timeoutMillis;
		}

		public boolean isPreventTermination() {
			return preventTermination;
		}

		public void setPreventTermination(boolean preventTermination) {
			this.preventTermination = preventTermination;
		}

		public boolean isEbsOptimized() {
			return ebsOptimized;
		}

		public void setEbsOptimized(boolean ebsOptimized) {
			this.ebsOptimized = ebsOptimized;
		}

		public boolean isEnableMonitoring() {
			return enableMonitoring;
		}

		public void setEnableMonitoring(boolean enableMonitoring) {
			this.enableMonitoring = enableMonitoring;
		}

		public Optional<RootVolume> getRootVolume() {
			return rootVolume;
		}

		public void setRootVolume(Optional<RootVolume> rootVolume) {
			this.rootVolume = rootVolume;
		}

		public String getAmi() {
			return ami;
		}

		public PublicKey getPublicKey() {
			return publicKey;
		}
	}

	public String getAmi() {
		return ami;
	}

	public PublicKey getPublicKey() {
		return publicKey;
	}

	public InstanceType getType() {
		return type;
	}

	public List<KualiSecurityGroup> getSecurityGroups() {
		return securityGroups;
	}

	public boolean isOverrideExistingSecurityGroupPermissions() {
		return overrideExistingSecurityGroupPermissions;
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

	public List<BlockDeviceMapping> getAdditionalMappings() {
		return additionalMappings;
	}

}
