package org.kuali.common.aws.s3.model;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Optional.fromNullable;

import javax.validation.constraints.Min;

import org.kuali.common.core.build.ValidatingBuilder;
import org.kuali.common.core.validate.annotation.IdiotProofImmutable;

import com.google.common.base.Optional;

@IdiotProofImmutable
public final class CopyObjectResult {

	private final String etag;
	@Min(0)
	private final long lastModifiedDate;
	private final Optional<String> versionId;
	private final Optional<String> serverSideEncryption;
	private final Optional<Long> expirationTime;
	private final Optional<String> expirationTimeRuleId;

	private CopyObjectResult(Builder builder) {
		this.etag = builder.etag;
		this.lastModifiedDate = builder.lastModifiedDate;
		this.versionId = builder.versionId;
		this.serverSideEncryption = builder.serverSideEncryption;
		this.expirationTime = builder.expirationTime;
		this.expirationTimeRuleId = builder.expirationTimeRuleId;
	}

	public static CopyObjectResult copyOf(com.amazonaws.services.s3.model.CopyObjectResult mutable) {
		Builder builder = builder();
		builder.withEtag(mutable.getETag());
		builder.withLastModifiedDate(mutable.getLastModifiedDate().getTime());
		builder.withVersionId(mutable.getVersionId());
		builder.withServerSideEncryption(mutable.getServerSideEncryption());
		if (mutable.getExpirationTime() != null) {
			builder.withExpirationTime(mutable.getExpirationTime().getTime());
		}
		builder.withExpirationTimeRuleId(mutable.getExpirationTimeRuleId());
		return builder.build();
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder<CopyObjectResult> {

		private String etag;
		private long lastModifiedDate;
		private Optional<String> versionId = absent();
		private Optional<String> serverSideEncryption = absent();
		private Optional<Long> expirationTime = absent();
		private Optional<String> expirationTimeRuleId = absent();

		public Builder withEtag(String etag) {
			this.etag = etag;
			return this;
		}

		public Builder withLastModifiedDate(long lastModifiedDate) {
			this.lastModifiedDate = lastModifiedDate;
			return this;
		}

		public Builder withVersionId(String versionId) {
			this.versionId = fromNullable(versionId);
			return this;
		}

		public Builder withServerSideEncryption(String serverSideEncryption) {
			this.serverSideEncryption = fromNullable(serverSideEncryption);
			return this;
		}

		public Builder withExpirationTime(Optional<Long> expirationTime) {
			this.expirationTime = expirationTime;
			return this;
		}

		public Builder withExpirationTime(long expirationTime) {
			return withExpirationTime(Optional.of(expirationTime));
		}

		public Builder withExpirationTimeRuleId(String expirationTimeRuleId) {
			this.expirationTimeRuleId = Optional.of(expirationTimeRuleId);
			return this;
		}

		@Override
		public CopyObjectResult build() {
			return validate(new CopyObjectResult(this));
		}
	}

	public String getEtag() {
		return etag;
	}

	public long getLastModifiedDate() {
		return lastModifiedDate;
	}

	public Optional<String> getVersionId() {
		return versionId;
	}

	public Optional<String> getServerSideEncryption() {
		return serverSideEncryption;
	}

	public Optional<Long> getExpirationTime() {
		return expirationTime;
	}

	public Optional<String> getExpirationTimeRuleId() {
		return expirationTimeRuleId;
	}

}
