package org.kuali.common.deploy.dns.model;

import org.kuali.common.util.Assert;

public class DnsmeContext {

	private final int ttl;
	private final String recordName;
	private final String domainName;
	private final RecordType recordType;
	private final String apiKey;
	private final String secretKey;

	public static class Builder {

		// Required
		private final String apiKey;
		private final String secretKey;
		private final String recordName;

		private int ttl = 60;
		private RecordType recordType = RecordType.CNAME;
		private String domainName = DnsContext.Builder.KUALI_DOMAIN;

		public Builder(String apiKey, String secretKey, String recordName) {
			this.apiKey = apiKey;
			this.secretKey = secretKey;
			this.recordName = recordName;
		}

		public Builder ttl(int ttl) {
			this.ttl = ttl;
			return this;
		}

		public Builder recordType(RecordType recordType) {
			this.recordType = recordType;
			return this;
		}

		public Builder domainName(String domainName) {
			this.domainName = domainName;
			return this;
		}

		public DnsmeContext build() {
			Assert.noBlanks(apiKey, secretKey, recordName, domainName);
			Assert.noNulls(recordType);
			return new DnsmeContext(this);
		}

	}

	private DnsmeContext(Builder builder) {
		this.apiKey = builder.apiKey;
		this.secretKey = builder.secretKey;
		this.recordName = builder.recordName;
		this.ttl = builder.ttl;
		this.recordType = builder.recordType;
		this.domainName = builder.domainName;
	}

	public int getTtl() {
		return ttl;
	}

	public String getRecordName() {
		return recordName;
	}

	public String getDomainName() {
		return domainName;
	}

	public RecordType getRecordType() {
		return recordType;
	}

	public String getApiKey() {
		return apiKey;
	}

	public String getSecretKey() {
		return secretKey;
	}

}
