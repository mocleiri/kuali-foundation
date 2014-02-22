package org.kuali.common.util.system;

import static com.google.common.base.Optional.absent;

import java.io.File;
import java.util.Set;
import java.util.TimeZone;

import javax.validation.ConstraintViolation;

import org.kuali.common.util.bind.api.Alias;
import org.kuali.common.util.build.ValidatingBuilder;
import org.kuali.common.util.spring.format.optional.OptionalStringFormat;
import org.kuali.common.util.spring.format.optional.OptionalTimeZoneFormat;
import org.kuali.common.util.validate.IdiotProofImmutable;

import com.google.common.base.Optional;

@IdiotProofImmutable
public final class User {

	private final String name;
	private final File home;
	private final File dir;
	private final Optional<String> language;
	private final Optional<String> country;
	@Alias("timezone")
	private final Optional<TimeZone> timeZone;

	private User(Builder builder) {
		this.name = builder.name;
		this.home = builder.home;
		this.dir = builder.dir;
		this.language = builder.language;
		this.country = builder.country;
		this.timeZone = builder.timeZone;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder<User> {

		private String name;
		private File home;
		private File dir;
		@OptionalStringFormat
		private Optional<String> language = absent();
		@OptionalStringFormat
		private Optional<String> country = absent();
		@OptionalTimeZoneFormat
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

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder home(File home) {
			this.home = home;
			return this;
		}

		public Builder dir(File dir) {
			this.dir = dir;
			return this;
		}

		@Override
		public Set<ConstraintViolation<User>> violations() {
			return getViolations(new User(this));
		}

		@Override
		public User build() {
			return validate(new User(this));
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public File getHome() {
			return home;
		}

		public void setHome(File home) {
			this.home = home;
		}

		public File getDir() {
			return dir;
		}

		public void setDir(File dir) {
			this.dir = dir;
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

	public String getName() {
		return name;
	}

	public File getHome() {
		return home;
	}

	public File getDir() {
		return dir;
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
