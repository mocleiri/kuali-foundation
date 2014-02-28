package org.kuali.common.util.system;

import static com.google.common.base.Optional.absent;

import java.io.File;
import java.util.TimeZone;

import org.kuali.common.util.build.SimpleValidatingBuilder;
import org.kuali.common.util.validate.IdiotProofImmutable;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.Optional;

@IdiotProofImmutable
@JsonDeserialize(builder = User.Builder.class)
public final class User {

	private final String name;
	private final File home;
	private final File dir;
	private final Optional<String> language;
	private final Optional<String> country;
	private final Optional<TimeZone> timezone;

	private User(Builder builder) {
		this.name = builder.name;
		this.home = builder.home;
		this.dir = builder.dir;
		this.language = builder.language;
		this.country = builder.country;
		this.timezone = builder.timezone;
	}

	public static class Builder extends SimpleValidatingBuilder<User> {

		private String name;
		private File home;
		private File dir;
		private Optional<String> language = absent();
		private Optional<String> country = absent();
		private Optional<TimeZone> timezone = absent();

		public Builder withName(String name) {
			this.name = name;
			return this;
		}

		public Builder withHome(File home) {
			this.home = home;
			return this;
		}

		public Builder withDir(File dir) {
			this.dir = dir;
			return this;
		}

		public Builder withLanguage(Optional<String> language) {
			this.language = language;
			return this;
		}

		public Builder withCountry(Optional<String> country) {
			this.country = country;
			return this;
		}

		public Builder withTimezone(Optional<TimeZone> timezone) {
			this.timezone = timezone;
			return this;
		}

		@Override
		public User build() {
			return validate(new User(this));
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

	public Optional<TimeZone> getTimezone() {
		return timezone;
	}

}
