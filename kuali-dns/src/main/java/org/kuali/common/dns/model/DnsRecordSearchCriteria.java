package org.kuali.common.dns.model;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.Assert;

import com.google.common.base.Optional;

public final class DnsRecordSearchCriteria {

	private static final Optional<String> ABSENT = Optional.<String> absent();

	public DnsRecordSearchCriteria(DnsRecordType type) {
		this(ABSENT, Optional.of(type), ABSENT);
	}

	public DnsRecordSearchCriteria(String nameContains) {
		this(Optional.of(nameContains), Optional.<DnsRecordType> absent(), ABSENT);
	}

	public DnsRecordSearchCriteria(Optional<String> nameContains, Optional<DnsRecordType> type, Optional<String> valueContains) {
		Assert.noNulls(nameContains, type, valueContains);
		if (nameContains.isPresent()) {
			Assert.isFalse(StringUtils.isBlank(nameContains.get()));
		}
		if (valueContains.isPresent()) {
			Assert.isFalse(StringUtils.isBlank(valueContains.get()));
		}
		this.nameContains = nameContains;
		this.type = type;
		this.valueContains = valueContains;
	}

	private final Optional<String> nameContains;
	private final Optional<DnsRecordType> type;
	private final Optional<String> valueContains;

	public Optional<String> getNameContains() {
		return nameContains;
	}

	public Optional<DnsRecordType> getType() {
		return type;
	}

	public Optional<String> getValueContains() {
		return valueContains;
	}

}
