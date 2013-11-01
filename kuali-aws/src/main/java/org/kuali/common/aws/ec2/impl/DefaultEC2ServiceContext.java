package org.kuali.common.aws.ec2.impl;

import org.kuali.common.aws.model.ImmutableAwsCredentials;
import org.kuali.common.util.Assert;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.enc.EncUtils;
import org.kuali.common.util.wait.WaitService;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.ec2.model.Region;
import com.google.common.base.Optional;

public final class DefaultEC2ServiceContext {

	private final AWSCredentials credentials;
	private final WaitService service;
	private final int sleepMillis;
	private final int initialPauseMillis;
	private final int terminationTimeoutMillis;
	private final Optional<Region> region;
	private final Optional<String> endpoint;
	private final Optional<ClientConfiguration> configuration;
	private final Optional<Integer> timeOffsetInSeconds;

	public static class Builder {

		// Required
		private final AWSCredentials credentials;
		private final WaitService service;

		// Optional
		private int sleepMillis = FormatUtils.getMillisAsInt("15s"); // 15 seconds
		private int initialPauseMillis = FormatUtils.getMillisAsInt("1s"); // 1 second
		private int terminationTimeoutMillis = FormatUtils.getMillisAsInt("15m"); // 15 minutes
		private Optional<Region> region = Optional.absent(); // Every AWS account has a default region
		private Optional<String> endpoint = Optional.absent(); // Every AWS account has a default endpoint
		private Optional<ClientConfiguration> configuration = Optional.absent(); // This allows advanced customization (eg connecting to AWS through a proxy)
		private Optional<Integer> timeOffsetInSeconds = Optional.absent(); // Number of seconds the system clock where this client is running is ahead of (or behind) correct time

		public Builder(String accessKey, String secretKey, WaitService service) {
			this(new ImmutableAwsCredentials(accessKey, secretKey), service);
		}

		public Builder(AWSCredentials credentials, WaitService service) {
			this.credentials = credentials;
			this.service = service;
		}

		public Builder timeOffsetInSeconds(Integer timeOffsetInSeconds) {
			this.timeOffsetInSeconds = Optional.fromNullable(timeOffsetInSeconds);
			return this;
		}

		public Builder region(Region region) {
			this.region = Optional.fromNullable(region);
			return this;
		}

		public Builder endpoint(String endpoint) {
			this.endpoint = Optional.fromNullable(endpoint);
			return this;
		}

		public Builder configuration(ClientConfiguration configuration) {
			this.configuration = Optional.fromNullable(configuration);
			return this;
		}

		public Builder sleepMillis(int sleepMillis) {
			this.sleepMillis = sleepMillis;
			return this;
		}

		public Builder initialPauseMillis(int initialPauseMillis) {
			this.initialPauseMillis = initialPauseMillis;
			return this;
		}

		public Builder terminationTimeoutMillis(int terminationTimeoutMillis) {
			this.terminationTimeoutMillis = terminationTimeoutMillis;
			return this;
		}

		public DefaultEC2ServiceContext build() {
			Assert.noNulls(service, credentials, timeOffsetInSeconds, region, endpoint, configuration);
			Assert.noNegatives(sleepMillis, initialPauseMillis, terminationTimeoutMillis);
			Assert.isFalse(EncUtils.isEncrypted(credentials.getAWSAccessKeyId()), "AWS Access Key ID is encrypted");
			Assert.isFalse(EncUtils.isEncrypted(credentials.getAWSSecretKey()), "AWS Secret Key is encrypted");
			return new DefaultEC2ServiceContext(this);
		}

	}

	private DefaultEC2ServiceContext(Builder builder) {
		this.credentials = builder.credentials;
		this.service = builder.service;
		this.sleepMillis = builder.sleepMillis;
		this.initialPauseMillis = builder.initialPauseMillis;
		this.terminationTimeoutMillis = builder.terminationTimeoutMillis;
		this.region = builder.region;
		this.endpoint = builder.endpoint;
		this.configuration = builder.configuration;
		this.timeOffsetInSeconds = builder.timeOffsetInSeconds;
	}

	public WaitService getService() {
		return service;
	}

	public int getSleepMillis() {
		return sleepMillis;
	}

	public int getInitialPauseMillis() {
		return initialPauseMillis;
	}

	public int getTerminationTimeoutMillis() {
		return terminationTimeoutMillis;
	}

	public Optional<Region> getRegion() {
		return region;
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

	public AWSCredentials getCredentials() {
		return credentials;
	}

}
