package org.kuali.common.dns.model;

import static com.google.common.base.Optional.absent;
import static org.kuali.common.util.base.Precondition.checkNotBlank;
import static org.kuali.common.util.base.Precondition.checkNotNull;

import com.google.common.base.Optional;

public final class DnsRecordSearchCriteria {

	private final Optional<String> nameContains;
	private final Optional<DnsRecordType> type;
	private final Optional<String> valueContains;

	private DnsRecordSearchCriteria(Builder builder) {
		this.nameContains = builder.nameContains;
		this.type = builder.type;
		this.valueContains = builder.valueContains;
	}

	public static DnsRecordSearchCriteria newDnsRecordSearchCriteria(DnsRecordType type) {
		return builder().type(type).build();
	}

	public static DnsRecordSearchCriteria newDnsRecordSearchCriteria() {
		return builder().build();
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder implements org.apache.commons.lang3.builder.Builder<DnsRecordSearchCriteria> {

		private Optional<String> nameContains = absent();
		private Optional<DnsRecordType> type = absent();
		private Optional<String> valueContains = absent();

		public Builder nameContains(Optional<String> nameContains) {
			this.nameContains = nameContains;
			return this;
		}

		public Builder type(Optional<DnsRecordType> type) {
			this.type = type;
			return this;
		}

		public Builder type(DnsRecordType type) {
			return type(Optional.of(type));
		}

		public Builder valueContains(Optional<String> valueContains) {
			this.valueContains = valueContains;
			return this;
		}

		@Override
		public DnsRecordSearchCriteria build() {
			DnsRecordSearchCriteria instance = new DnsRecordSearchCriteria(this);
			validate(instance);
			return instance;
		}

		private static void validate(DnsRecordSearchCriteria instance) {
			checkNotBlank(instance.nameContains, "nameContains");
			checkNotBlank(instance.valueContains, "valueContains");
			checkNotNull(instance.type, "type");
		}
	}

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
