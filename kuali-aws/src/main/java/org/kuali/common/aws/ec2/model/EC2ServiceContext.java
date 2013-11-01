package org.kuali.common.aws.ec2.model;

import org.kuali.common.aws.model.ImmutableAwsCredentials;
import org.kuali.common.aws.model.Regions;
import org.kuali.common.util.Assert;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.enc.EncUtils;
import org.kuali.common.util.nullify.NullUtils;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.google.common.base.Optional;

public final class EC2ServiceContext {

	private final AWSCredentials credentials;
	private final int sleepMillis;
	private final int initialPauseMillis;
	private final int terminationTimeoutMillis;
	private final Optional<String> regionName;
	private final Optional<String> endpoint;
	private final Optional<ClientConfiguration> configuration;
	private final Optional<Integer> timeOffsetInSeconds;

	public static class Builder {

		// Required
		private final AWSCredentials credentials;

		// Optional
		private int sleepMillis = FormatUtils.getMillisAsInt("15s"); // 15 seconds
		private int initialPauseMillis = FormatUtils.getMillisAsInt("1s"); // 1 second
		private int terminationTimeoutMillis = FormatUtils.getMillisAsInt("15m"); // Throw an exception if it takes longer than 15 minutes to terminate an instance
		private Optional<String> regionName = Optional.of(Regions.DEFAULT_REGION.getName());
		private Optional<String> endpoint = Optional.absent(); // Every AWS account has a default endpoint
		private Optional<ClientConfiguration> configuration = Optional.absent(); // This allows advanced customization (eg connecting to AWS through a proxy)
		private Optional<Integer> timeOffsetInSeconds = Optional.absent(); // Number of seconds the system clock where this client is running is ahead of (or behind) correct time

		public Builder(String accessKey, String secretKey) {
			this(new ImmutableAwsCredentials(accessKey, secretKey));
		}

		public Builder(AWSCredentials credentials) {
			this.credentials = credentials;
		}

		public Builder timeOffsetInSeconds(Integer timeOffsetInSeconds) {
			this.timeOffsetInSeconds = Optional.fromNullable(timeOffsetInSeconds);
			return this;
		}

		public Builder regionName(String regionName) {
			this.regionName = Optional.fromNullable(NullUtils.trimToNull(regionName));
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

		public EC2ServiceContext build() {
			Assert.noNulls(credentials, timeOffsetInSeconds, regionName, endpoint, configuration);
			Assert.noNegatives(sleepMillis, initialPauseMillis, terminationTimeoutMillis);
			Assert.isFalse(EncUtils.isEncrypted(credentials.getAWSAccessKeyId()), "AWS Access Key ID is encrypted");
			Assert.isFalse(EncUtils.isEncrypted(credentials.getAWSSecretKey()), "AWS Secret Key is encrypted");
			return new EC2ServiceContext(this);
		}

	}

	private EC2ServiceContext(Builder builder) {
		this.credentials = builder.credentials;
		this.sleepMillis = builder.sleepMillis;
		this.initialPauseMillis = builder.initialPauseMillis;
		this.terminationTimeoutMillis = builder.terminationTimeoutMillis;
		this.regionName = builder.regionName;
		this.endpoint = builder.endpoint;
		this.configuration = builder.configuration;
		this.timeOffsetInSeconds = builder.timeOffsetInSeconds;
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

	public AWSCredentials getCredentials() {
		return credentials;
	}

}
