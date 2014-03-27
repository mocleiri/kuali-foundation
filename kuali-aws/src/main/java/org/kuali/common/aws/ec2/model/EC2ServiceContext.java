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

import org.kuali.common.core.build.ValidatingBuilder;
import org.kuali.common.util.FormatUtils;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.google.common.base.Optional;

public final class EC2ServiceContext {

	private final AWSCredentials credentials;
	private final int sleepIntervalMillis;
	private final int initialPauseMillis;
	private final int terminationTimeoutMillis;
	private final Optional<String> regionName;
	private final Optional<String> endpoint;
	private final Optional<ClientConfiguration> configuration;
	private final Optional<Integer> timeOffsetInSeconds;

	public static EC2ServiceContext create(AWSCredentials credentials) {
		return builder(credentials).build();
	}

	public static Builder builder(AWSCredentials credentials) {
		return new Builder(credentials);
	}

	private EC2ServiceContext(Builder builder) {
		this.credentials = builder.credentials;
		this.sleepIntervalMillis = builder.sleepIntervalMillis;
		this.initialPauseMillis = builder.initialPauseMillis;
		this.terminationTimeoutMillis = builder.terminationTimeoutMillis;
		this.regionName = builder.regionName;
		this.endpoint = builder.endpoint;
		this.configuration = builder.configuration;
		this.timeOffsetInSeconds = builder.timeOffsetInSeconds;
	}

	public static class Builder extends ValidatingBuilder<EC2ServiceContext> {

		private final AWSCredentials credentials;
		private int sleepIntervalMillis = FormatUtils.getMillisAsInt("15s");
		private int initialPauseMillis = FormatUtils.getMillisAsInt("1s");
		private int terminationTimeoutMillis = FormatUtils.getMillisAsInt("15m");
		private Optional<String> regionName = Optional.of(Regions.DEFAULT_REGION.getName());
		private Optional<String> endpoint = absent();
		private Optional<ClientConfiguration> configuration = absent();
		private Optional<Integer> timeOffsetInSeconds = absent();

		public Builder(AWSCredentials credentials) {
			this.credentials = credentials;
		}

		public Builder withSleepIntervalMillis(int sleepIntervalMillis) {
			this.sleepIntervalMillis = sleepIntervalMillis;
			return this;
		}

		public Builder withInitialPauseMillis(int initialPauseMillis) {
			this.initialPauseMillis = initialPauseMillis;
			return this;
		}

		public Builder withTerminationTimeoutMillis(int terminationTimeoutMillis) {
			this.terminationTimeoutMillis = terminationTimeoutMillis;
			return this;
		}

		public Builder withRegionName(Optional<String> regionName) {
			this.regionName = regionName;
			return this;
		}

		public Builder withRegionName(String regionName) {
			return withRegionName(Optional.of(regionName));
		}

		public Builder withRegion(Regions region) {
			return withRegionName(region.getName());
		}

		public Builder withEndpoint(Optional<String> endpoint) {
			this.endpoint = endpoint;
			return this;
		}

		public Builder withConfiguration(Optional<ClientConfiguration> configuration) {
			this.configuration = configuration;
			return this;
		}

		public Builder withTimeOffsetInSeconds(Optional<Integer> timeOffsetInSeconds) {
			this.timeOffsetInSeconds = timeOffsetInSeconds;
			return this;
		}

		@Override
		public EC2ServiceContext build() {
			return validate(new EC2ServiceContext(this));
		}

		public int getSleepIntervalMillis() {
			return sleepIntervalMillis;
		}

		public void setSleepIntervalMillis(int sleepIntervalMillis) {
			this.sleepIntervalMillis = sleepIntervalMillis;
		}

		public int getInitialPauseMillis() {
			return initialPauseMillis;
		}

		public void setInitialPauseMillis(int initialPauseMillis) {
			this.initialPauseMillis = initialPauseMillis;
		}

		public int getTerminationTimeoutMillis() {
			return terminationTimeoutMillis;
		}

		public void setTerminationTimeoutMillis(int terminationTimeoutMillis) {
			this.terminationTimeoutMillis = terminationTimeoutMillis;
		}

		public Optional<String> getRegionName() {
			return regionName;
		}

		public void setRegionName(Optional<String> regionName) {
			this.regionName = regionName;
		}

		public Optional<String> getEndpoint() {
			return endpoint;
		}

		public void setEndpoint(Optional<String> endpoint) {
			this.endpoint = endpoint;
		}

		public Optional<ClientConfiguration> getConfiguration() {
			return configuration;
		}

		public void setConfiguration(Optional<ClientConfiguration> configuration) {
			this.configuration = configuration;
		}

		public Optional<Integer> getTimeOffsetInSeconds() {
			return timeOffsetInSeconds;
		}

		public void setTimeOffsetInSeconds(Optional<Integer> timeOffsetInSeconds) {
			this.timeOffsetInSeconds = timeOffsetInSeconds;
		}

		public AWSCredentials getCredentials() {
			return credentials;
		}
	}

	public AWSCredentials getCredentials() {
		return credentials;
	}

	public int getSleepIntervalMillis() {
		return sleepIntervalMillis;
	}

	public int getInitialPauseMillis() {
		return initialPauseMillis;
	}

	public int getTerminationTimeoutMillis() {
		return terminationTimeoutMillis;
	}

	public Optional<String> getRegionName() {
		return regionName;
	}

	public Optional<String> getEndpoint() {
		return endpoint;
	}

	public Optional<ClientConfiguration> getConfiguration() {
		return configuration;
	}

	public Optional<Integer> getTimeOffsetInSeconds() {
		return timeOffsetInSeconds;
	}

}
