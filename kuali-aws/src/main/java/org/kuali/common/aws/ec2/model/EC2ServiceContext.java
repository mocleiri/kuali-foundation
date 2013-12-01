package org.kuali.common.aws.ec2.model;

import org.kuali.common.aws.model.ImmutableCredentials;
import org.kuali.common.util.Assert;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.nullify.NullUtils;
import org.kuali.common.util.spring.SpringUtils;
import org.kuali.common.util.spring.env.EnvironmentService;

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

	public static class Builder {

		// Required
		private final AWSCredentials credentials;

		// Optional
		private Optional<EnvironmentService> env = Optional.absent();
		private Optional<String> regionName = Optional.of(Regions.DEFAULT_REGION.getName()); // Java SDK defaults to us-east-1 (Northern Virgina) when no region is provided
		private Optional<String> endpoint = Optional.absent(); // Every AWS account has a default endpoint
		private Optional<Integer> timeOffsetInSeconds = Optional.absent(); // Number of seconds the system clock where this client is running is ahead of (or behind) correct time
		private int sleepIntervalMillis = FormatUtils.getMillisAsInt("15s"); // 15 seconds
		private int initialPauseMillis = FormatUtils.getMillisAsInt("1s"); // 1 second
		private int terminationTimeoutMillis = FormatUtils.getMillisAsInt("15m"); // Throw an exception if it takes longer than 15 minutes to terminate an instance
		private Optional<ClientConfiguration> configuration = Optional.absent(); // This allows advanced customization (eg connecting to AWS through a proxy)

		private static final String REGION_KEY = "ec2.region";
		private static final String TIMEOFFSET_KEY = "ec2.timeOffset";
		private static final String ENDPOINT_KEY = "ec2.endpoint";
		private static final String INITIAL_PAUSE_KEY = "ec2.initialPause";
		private static final String SLEEP_INTERVAL_MILLIS_KEY = "ec2.sleepInterval";
		private static final String TERMINATION_TIMEOUT_KEY = "ec2.terminationTimeout";

		private void override() {
			if (env.isPresent()) {
				regionName(SpringUtils.getString(env.get(), REGION_KEY, regionName).orNull());
				endpoint(SpringUtils.getString(env.get(), ENDPOINT_KEY, endpoint).orNull());
				timeOffsetInSeconds(SpringUtils.getMillisAsInt(env.get(), TIMEOFFSET_KEY, timeOffsetInSeconds).orNull());
				sleepIntervalMillis(env.get().getInteger(SLEEP_INTERVAL_MILLIS_KEY, sleepIntervalMillis));
				initialPauseMillis(env.get().getInteger(INITIAL_PAUSE_KEY, initialPauseMillis));
				terminationTimeoutMillis(env.get().getInteger(TERMINATION_TIMEOUT_KEY, terminationTimeoutMillis));
			}
		}

		public Builder(String accessKey, String secretKey) {
			this(new ImmutableCredentials.Builder(accessKey, secretKey).build());
		}

		public Builder(AWSCredentials credentials) {
			this.credentials = credentials;
		}

		public Builder env(EnvironmentService env) {
			this.env = Optional.of(env);
			return this;
		}

		public Builder timeOffsetInSeconds(Integer timeOffsetInSeconds) {
			this.timeOffsetInSeconds = Optional.fromNullable(timeOffsetInSeconds);
			return this;
		}

		public Builder regionName(String regionName) {
			this.regionName = NullUtils.toAbsent(regionName);
			return this;
		}

		public Builder endpoint(String endpoint) {
			this.endpoint = NullUtils.toAbsent(endpoint);
			return this;
		}

		public Builder configuration(ClientConfiguration configuration) {
			this.configuration = Optional.fromNullable(configuration);
			return this;
		}

		public Builder sleepIntervalMillis(int sleepIntervalMillis) {
			this.sleepIntervalMillis = sleepIntervalMillis;
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

		private void validate(EC2ServiceContext ctx) {
			Assert.noNulls(ctx.credentials, ctx.timeOffsetInSeconds, ctx.regionName, ctx.endpoint, ctx.configuration);
			Assert.noNegatives(ctx.sleepIntervalMillis, ctx.initialPauseMillis, ctx.terminationTimeoutMillis);
			Assert.notEncrypted(ctx.credentials.getAWSSecretKey());
			Assert.notConcealed(ctx.credentials.getAWSSecretKey());
		}

		public EC2ServiceContext build() {
			override();
			EC2ServiceContext ctx = new EC2ServiceContext(this);
			validate(ctx);
			return ctx;
		}

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

	public AWSCredentials getCredentials() {
		return credentials;
	}

}
