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
package org.kuali.common.aws.s3.model;

import static com.google.common.base.Optional.absent;
import static org.apache.commons.lang3.StringUtils.trimToNull;

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
		if (trimToNull(mutable.getVersionId()) != null) {
			builder.withVersionId(trimToNull(mutable.getVersionId()));
		}
		if (trimToNull(mutable.getServerSideEncryption()) != null) {
			builder.withServerSideEncryption(trimToNull(mutable.getServerSideEncryption()));
		}
		if (trimToNull(mutable.getExpirationTimeRuleId()) != null) {
			builder.withExpirationTimeRuleId(trimToNull(mutable.getExpirationTimeRuleId()));
		}
		if (mutable.getExpirationTime() != null) {
			builder.withExpirationTime(mutable.getExpirationTime().getTime());
		}
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
			this.versionId = Optional.of(versionId);
			return this;
		}

		public Builder withServerSideEncryption(String serverSideEncryption) {
			this.serverSideEncryption = Optional.of(serverSideEncryption);
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
