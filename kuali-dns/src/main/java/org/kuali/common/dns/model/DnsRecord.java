package org.kuali.common.dns.model;

import org.kuali.common.util.Assert;

public final class DnsRecord implements Comparable<DnsRecord> {

	public DnsRecord(String name, DnsRecordType type, String value) {
		Assert.noBlanks(name, value);
		Assert.noNulls(type);
		this.name = name;
		this.type = type;
		this.value = value;
	}

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
		if (type != other.getType()) {
			// Sort by type first
			return type.name().compareTo(other.getType().name());
		} else {
			// Sort by name if they are the same type
			return name.compareTo(other.getName());
		}
	}

}
