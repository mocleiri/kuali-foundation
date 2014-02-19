package org.kuali.common.util.system;

import static com.google.common.base.Optional.absent;
import static org.kuali.common.util.validate.Validation.checkConstraints;

import java.io.File;
import java.util.TimeZone;

import org.kuali.common.util.bind.api.Alias;
import org.kuali.common.util.spring.format.OptionalType;
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

	public static class Builder implements org.apache.commons.lang3.builder.Builder<User> {

		private String name;
		private File home;
		private File dir;
		private Optional<String> language = absent();
		private Optional<String> country = absent();
		@OptionalType(TimeZone.class)
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
		public User build() {
			return checkConstraints(new User(this));
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
