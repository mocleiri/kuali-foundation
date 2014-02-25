package org.kuali.common.dns.model;

import static org.kuali.common.util.base.Precondition.checkMin;
import static org.kuali.common.util.base.Precondition.checkNotBlank;

public final class CNAMEContext {

	private final String aliasFQDN;
	private final String canonicalFQDN;
	private final int timeToLiveInSeconds;

	public static CNAMEContext newCNAMEContext(String aliasFQDN, String canonicalFQDN) {
		return builder(aliasFQDN, canonicalFQDN).build();
	}

	public static Builder builder(String aliasFQDN, String canonicalFQDN) {
		return new Builder(aliasFQDN, canonicalFQDN);
	}

	public static class Builder implements org.apache.commons.lang3.builder.Builder<CNAMEContext> {

		// Required
		private final String aliasFQDN;
		private final String canonicalFQDN;

		// Optional (default value is usually fine)
		private int timeToLiveInSeconds = 60;

		public Builder(String aliasFQDN, String canonicalFQDN) {
			this.aliasFQDN = aliasFQDN;
			this.canonicalFQDN = canonicalFQDN;
		}

		@Override
		public CNAMEContext build() {
			CNAMEContext instance = new CNAMEContext(this);
			validate(instance);
			return instance;
		}

		private static void validate(CNAMEContext instance) {
			checkNotBlank(instance.aliasFQDN, "aliasFQDN");
			checkNotBlank(instance.canonicalFQDN, "canonicalFQDN");
			checkMin(instance.timeToLiveInSeconds, 0, "timeToLiveInSeconds");
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
