package org.kuali.common.util.system;

import static com.google.common.base.Optional.absent;
import static org.kuali.common.util.validate.Validation.checkConstraints;

import java.util.TimeZone;

import org.kuali.common.util.bind.api.Alias;
import org.kuali.common.util.bind.api.Bind;
import org.kuali.common.util.validate.IdiotProofImmutable;

import com.google.common.base.Optional;

@IdiotProofImmutable
public final class VirtualMachine {

	private final String name;
	private final String vendor;
	private final String version;
	private final Optional<String> language;
	private final Optional<String> country;
	@Alias("timezone")
	private final Optional<TimeZone> timeZone;

	@Bind
	private final Specification specification;

	private VirtualMachine(Builder builder) {
		this.specification = builder.specification;
		this.name = builder.name;
		this.vendor = builder.vendor;
		this.version = builder.version;
		this.language = builder.language;
		this.country = builder.country;
		this.timeZone = builder.timeZone;
	}

	public static class Builder implements org.apache.commons.lang3.builder.Builder<VirtualMachine> {

		private Specification specification;
		private String name;
		private String vendor;
		private String version;
		private Optional<String> language = absent();
		private Optional<String> country = absent();
		private Optional<TimeZone> timeZone = absent();

		public Builder country(Optional<String> country) {
			this.country = country;
			return this;
		}

		public Builder language(Optional<String> language) {
			this.language = language;
			return this;
		}

		public Builder timeZone(Optional<TimeZone> timeZone) {
			this.timeZone = timeZone;
			return this;
		}

		public Builder timeZone(TimeZone timeZone) {
			return timeZone(Optional.of(timeZone));
		}

		public Builder specification(Specification specification) {
			this.specification = specification;
			return this;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder vendor(String vendor) {
			this.vendor = vendor;
			return this;
		}

		public Builder version(String version) {
			this.version = version;
			return this;
		}

		@Override
		public VirtualMachine build() {
			return checkConstraints(new VirtualMachine(this));
		}

		public Specification getSpecification() {
			return specification;
		}

		public void setSpecification(Specification specification) {
			this.specification = specification;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getVendor() {
			return vendor;
		}

		public void setVendor(String vendor) {
			this.vendor = vendor;
		}

		public String getVersion() {
			return version;
		}

		public void setVersion(String version) {
			this.version = version;
		}

		public Optional<String> getLanguage() {
			return language;
		}

		public void setLanguage(Optional<String> language) {
			this.language = language;
		}

		public Optional<String> getCountry() {
			return country;
		}

		public void setCountry(Optional<String> country) {
			this.country = country;
		}

		public Optional<TimeZone> getTimeZone() {
			return timeZone;
		}

		public void setTimeZone(Optional<TimeZone> timeZone) {
			this.timeZone = timeZone;
		}

	}

	public Specification getSpecification() {
		return specification;
	}

	public String getName() {
		return name;
	}

	public String getVendor() {
		return vendor;
	}

	public String getVersion() {
		return version;
	}

	public Optional<String> getLanguage() {
		return language;
	}

	public Optional<String> getCountry() {
		return country;
	}

	public Optional<TimeZone> getTimeZone() {
		return timeZone;
	}

}
