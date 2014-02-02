package org.kuali.common.devops.model;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static org.apache.commons.lang3.StringUtils.isBlank;

import org.kuali.common.dns.model.DnsRecordType;

public final class DnsmeRecord {

	private final String dns;
	private final String alias;
	private final DnsRecordType type;
	private final int ttl;

	private DnsmeRecord(Builder builder) {
		this.dns = builder.dns;
		this.alias = builder.alias;
		this.type = builder.type;
		this.ttl = builder.ttl;
	}

	public static class Builder {

		private String dns;
		private String alias;
		private DnsRecordType type;
		private int ttl;

		public Builder dns(String dns) {
			this.dns = dns;
			return this;
		}

		public Builder alias(String alias) {
			this.alias = alias;
			return this;
		}

		public Builder type(DnsRecordType type) {
			this.type = type;
			return this;
		}

		public Builder ttl(int ttl) {
			this.ttl = ttl;
			return this;
		}

		public DnsmeRecord build() {
			DnsmeRecord instance = new DnsmeRecord(this);
			validate(instance);
			return instance;
		}

		private static void validate(DnsmeRecord instance) {
			checkArgument(!isBlank(instance.dns), "'dns' cannot be blank");
			checkArgument(!isBlank(instance.alias), "'alias' cannot be blank");
			checkNotNull(instance.type, "'type' cannot be null");
			checkArgument(instance.ttl > 0, "'ttl' must be greater than zero");
		}

		public String getDns() {
			return dns;
		}

		public void setDns(String dns) {
			this.dns = dns;
		}

		public String getAlias() {
			return alias;
		}

		public void setAlias(String alias) {
			this.alias = alias;
		}

		public DnsRecordType getType() {
			return type;
		}

		public void setType(DnsRecordType type) {
			this.type = type;
		}

		public int getTtl() {
			return ttl;
		}

		public void setTtl(int ttl) {
			this.ttl = ttl;
		}
	}

	public String getDns() {
		return dns;
	}

	public String getAlias() {
		return alias;
	}

	public DnsRecordType getType() {
		return type;
	}

	public int getTtl() {
		return ttl;
	}

}
