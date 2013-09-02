package org.kuali.common.deploy.dns.model;

import org.kuali.common.util.Assert;

public class DnsContext {

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

	public static class Builder {
		
		public static final String KUALI_DOMAIN = "kuali.org";

		// Required
		private final String prefix;
		private final String subdomain;

		// Optional
		private String domain = KUALI_DOMAIN;

		// Defaults to prefix + subdomain + domain if not provided
		private String hostname;

		public Builder(String prefix, String subdomain) {
			this.prefix = prefix;
			this.subdomain = subdomain;
		}

		public Builder domain(String domain) {
			this.domain = domain;
			return this;
		}

		public Builder hostname(String hostname) {
			this.hostname = hostname;
			return this;
		}

		public DnsContext build() {
			Assert.noBlanks(prefix, subdomain, domain);
			if (hostname == null) {
				this.hostname = prefix + "." + subdomain + "." + domain;
			}
			return new DnsContext(this);
		}

	}

	private DnsContext(Builder builder) {
		this.domain = builder.domain;
		this.hostname = builder.hostname;
		this.prefix = builder.prefix;
		this.subdomain = builder.subdomain;
	}

}
