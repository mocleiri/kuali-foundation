package org.kuali.common.devops.ci.model;

import org.kuali.common.core.build.ValidatingBuilder;
import org.kuali.common.core.validate.annotation.IdiotProofImmutable;
import org.kuali.common.devops.aws.Tags;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;

@IdiotProofImmutable
public final class JenkinsContext {

	private final String dnsPrefix;
	private final Tags.Name name;
	private final Tags.Stack stack;
	private final Region region;
	private final BackupMode backupMode;

	private JenkinsContext(Builder builder) {
		this.dnsPrefix = builder.dnsPrefix;
		this.name = builder.name;
		this.stack = builder.stack;
		this.region = builder.region;
		this.backupMode = builder.backupMode;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder<JenkinsContext> {

		private String dnsPrefix;
		private Tags.Name name;
		private Tags.Stack stack;
		private Region region = Region.getRegion(Regions.DEFAULT_REGION);
		private BackupMode backupMode;

		public Builder withBackupMode(BackupMode backupMode) {
			return withBackupMode(backupMode);
		}

		public Builder withRegion(String region) {
			return withRegion(Regions.fromName(region));
		}

		public Builder withRegion(Region region) {
			this.region = region;
			return this;
		}

		public Builder withRegion(Regions region) {
			return withRegion(Region.getRegion(region));
		}

		public Builder withDnsPrefix(String dnsPrefix) {
			this.dnsPrefix = dnsPrefix;
			return this;
		}

		public Builder withName(Tags.Name name) {
			this.name = name;
			return this;
		}

		public Builder withStack(Tags.Stack stack) {
			this.stack = stack;
			return this;
		}

		@Override
		public JenkinsContext build() {
			return validate(new JenkinsContext(this));
		}

		public String getDnsPrefix() {
			return dnsPrefix;
		}

		public void setDnsPrefix(String dnsPrefix) {
			this.dnsPrefix = dnsPrefix;
		}

		public Tags.Name getName() {
			return name;
		}

		public void setName(Tags.Name name) {
			this.name = name;
		}

		public Tags.Stack getStack() {
			return stack;
		}

		public void setStack(Tags.Stack stack) {
			this.stack = stack;
		}

		public Region getRegion() {
			return region;
		}

		public void setRegion(Region region) {
			this.region = region;
		}

		public BackupMode getBackupMode() {
			return backupMode;
		}

		public void setBackupMode(BackupMode backupMode) {
			this.backupMode = backupMode;
		}
	}

	public String getDnsPrefix() {
		return dnsPrefix;
	}

	public Tags.Name getName() {
		return name;
	}

	public Tags.Stack getStack() {
		return stack;
	}

	public Region getRegion() {
		return region;
	}

	public BackupMode getBackupMode() {
		return backupMode;
	}

}
