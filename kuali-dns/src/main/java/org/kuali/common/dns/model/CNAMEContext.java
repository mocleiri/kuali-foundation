package org.kuali.common.dns.model;

import org.kuali.common.util.Assert;

public final class CNAMEContext {

	private final String aliasFQDN;
	private final String canonicalFQDN;
	private final int timeToLiveInSeconds;

	public static class Builder {

		// Required
		private final String aliasFQDN;
		private final String canonicalFQDN;

		// Optional (default value is usually fine)
		private int timeToLiveInSeconds = 60;

		public Builder(String aliasFQDN, String canonicalFQDN) {
			this.aliasFQDN = aliasFQDN;
			this.canonicalFQDN = canonicalFQDN;
		}

		public CNAMEContext build() {
			Assert.noBlanks(aliasFQDN, canonicalFQDN);
			Assert.notNegative(timeToLiveInSeconds);
			return new CNAMEContext(this);
		}

	}

	private CNAMEContext(Builder builder) {
		this.aliasFQDN = builder.aliasFQDN;
		this.canonicalFQDN = builder.canonicalFQDN;
		this.timeToLiveInSeconds = builder.timeToLiveInSeconds;
	}

	public String getAliasFQDN() {
		return aliasFQDN;
	}

	public String getCanonicalFQDN() {
		return canonicalFQDN;
	}

	public int getTimeToLiveInSeconds() {
		return timeToLiveInSeconds;
	}

}
