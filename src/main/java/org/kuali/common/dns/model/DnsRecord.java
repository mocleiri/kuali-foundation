package org.kuali.common.dns.model;

import static org.kuali.common.util.base.Precondition.checkNotBlank;
import static org.kuali.common.util.base.Precondition.checkNotNull;

public final class DnsRecord implements Comparable<DnsRecord> {

	private final String name;
	private final DnsRecordType type;
	private final String value;

	public String getName() {
		return name;
	}

	public DnsRecordType getType() {
		return type;
	}

	public String getValue() {
		return value;
	}

	/**
	 * Sorts by type + name
	 */
	@Override
	public int compareTo(DnsRecord other) {
		if (type.equals(other.getType())) {
			// Sort by name if they are the same type
			return name.compareTo(other.getName());
		} else {
			// Otherwise sort by type
			return type.name().compareTo(other.getType().name());
		}
	}

	private DnsRecord(Builder builder) {
		this.name = builder.name;
		this.type = builder.type;
		this.value = builder.value;
	}

	public static DnsRecord newDnsRecord(String name, DnsRecordType type, String value) {
		return builder(name, type, value).build();
	}

	public static Builder builder(String name, DnsRecordType type, String value) {
		return new Builder().name(name).type(type).value(value);
	}

	public static class Builder implements org.apache.commons.lang3.builder.Builder<DnsRecord> {

		private String name;
		private DnsRecordType type;
		private String value;

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder type(DnsRecordType type) {
			this.type = type;
			return this;
		}

		public Builder value(String value) {
			this.value = value;
			return this;
		}

		@Override
		public DnsRecord build() {
			DnsRecord instance = new DnsRecord(this);
			validate(instance);
			return instance;
		}

		private static void validate(DnsRecord instance) {
			checkNotBlank(instance.name, "name");
			checkNotNull(instance.type, "type");
			checkNotBlank(instance.value, "value");
		}
	}

}
