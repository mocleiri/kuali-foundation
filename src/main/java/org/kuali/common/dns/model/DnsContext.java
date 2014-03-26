package org.kuali.common.dns.model;

import static org.kuali.common.util.base.Precondition.checkNotBlank;

import com.google.common.base.Joiner;

public final class DnsContext {

	private final String domain;
	private final String prefix;
	private final String subdomain;
	private final String hostname;

	public String getDomain() {
		return domain;
	}

	public String getPrefix() {
		return prefix;
	}

	public String getSubdomain() {
		return subdomain;
	}

	public String getHostname() {
		return hostname;
	}

	public static DnsContext newDnsContext(String prefix, String subdomain, String domain) {
		return builder(prefix, subdomain, domain).build();
	}

	public static Builder builder(String prefix, String subdomain, String domain) {
		return new Builder(prefix, subdomain, domain);
	}

	public static class Builder implements org.apache.commons.lang3.builder.Builder<DnsContext> {

		// Required
		private final String prefix;
		private final String subdomain;
		private final String domain;

		// Defaults to prefix + subdomain + domain if not provided
		private String hostname;

		public Builder(String prefix, String subdomain, String domain) {
			this.prefix = prefix;
			this.subdomain = subdomain;
			this.domain = domain;
		}

		public Builder hostname(String hostname) {
			this.hostname = hostname;
			return this;
		}

		@Override
		public DnsContext build() {
			this.hostname = (hostname == null) ? Joiner.on('.').join(prefix, subdomain, domain) : hostname;
			DnsContext instance = new DnsContext(this);
			validate(instance);
			return instance;
		}

		private static void validate(DnsContext instance) {
			checkNotBlank(instance.prefix, "prefix");
			checkNotBlank(instance.subdomain, "subdomain");
			checkNotBlank(instance.domain, "domain");
			checkNotBlank(instance.hostname, "hostname");
		}

	}

	private DnsContext(Builder builder) {
		this.domain = builder.domain;
		this.hostname = builder.hostname;
		this.prefix = builder.prefix;
		this.subdomain = builder.subdomain;
	}

}
