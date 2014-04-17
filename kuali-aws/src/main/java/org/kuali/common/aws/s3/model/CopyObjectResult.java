package org.kuali.common.aws.s3.model;

import javax.validation.constraints.Min;

import org.kuali.common.core.build.ValidatingBuilder;
import org.kuali.common.core.validate.annotation.IdiotProofImmutable;

@IdiotProofImmutable
public final class CopyObjectResult {

	private String etag;
	@Min(0)
	private long lastModifiedDate;
	private String versionId;
	private String serverSideEncryption;
	@Min(0)
	private long expirationTime;
	private String expirationTimeRuleId;

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
		builder.withExpirationTime(mutable.getExpirationTime().getTime());
		builder.withExpirationTimeRuleId(mutable.getExpirationTimeRuleId());
		return builder.build();
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder<CopyObjectResult> {

		private String etag;
		private long lastModifiedDate;
		private String versionId;
		private String serverSideEncryption;
		private long expirationTime;
		private String expirationTimeRuleId;

		public Builder withEtag(String etag) {
			this.etag = etag;
			return this;
		}

		public Builder withLastModifiedDate(long lastModifiedDate) {
			this.lastModifiedDate = lastModifiedDate;
			return this;
		}

		public Builder withVersionId(String versionId) {
			this.versionId = versionId;
			return this;
		}

		public Builder withServerSideEncryption(String serverSideEncryption) {
			this.serverSideEncryption = serverSideEncryption;
			return this;
		}

		public Builder withExpirationTime(long expirationTime) {
			this.expirationTime = expirationTime;
			return this;
		}

		public Builder withExpirationTimeRuleId(String expirationTimeRuleId) {
			this.expirationTimeRuleId = expirationTimeRuleId;
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

	public void setEtag(String etag) {
		this.etag = etag;
	}

	public long getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(long lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getVersionId() {
		return versionId;
	}

	public void setVersionId(String versionId) {
		this.versionId = versionId;
	}

	public String getServerSideEncryption() {
		return serverSideEncryption;
	}

	public void setServerSideEncryption(String serverSideEncryption) {
		this.serverSideEncryption = serverSideEncryption;
	}

	public long getExpirationTime() {
		return expirationTime;
	}

	public void setExpirationTime(long expirationTime) {
		this.expirationTime = expirationTime;
	}

	public String getExpirationTimeRuleId() {
		return expirationTimeRuleId;
	}

	public void setExpirationTimeRuleId(String expirationTimeRuleId) {
		this.expirationTimeRuleId = expirationTimeRuleId;
	}

}
