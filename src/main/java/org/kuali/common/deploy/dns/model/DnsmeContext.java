package org.kuali.common.deploy.dns.model;

import org.kuali.common.util.Assert;

public class DnsmeContext {

	private final int ttl;
	private final String recordName;
	private final String domainName;
	private final RecordType recordType;
	private final DnsmeCredentials credentials;

	public static class Builder {

		// Required
		private final DnsmeCredentials credentials;
		private final String recordName;
		private final String domainName;

		// Optional, default values are normally all that is needed
		private int ttl = 60;
		private RecordType recordType = RecordType.CNAME;

		public Builder(DnsmeCredentials credentials, String recordName, String domainName) {
			this.credentials = credentials;
			this.recordName = recordName;
			this.domainName = domainName;
		}

		public Builder ttl(int ttl) {
			this.ttl = ttl;
			return this;
		}

		public Builder recordType(RecordType recordType) {
			this.recordType = recordType;
			return this;
		}

		public DnsmeContext build() {
			Assert.noBlanks(recordName, domainName);
			Assert.noNulls(credentials, recordType);
			return new DnsmeContext(this);
		}

	}

	private DnsmeContext(Builder builder) {
		this.credentials = builder.credentials;
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

	public DnsmeCredentials getCredentials() {
		return credentials;
	}

}
