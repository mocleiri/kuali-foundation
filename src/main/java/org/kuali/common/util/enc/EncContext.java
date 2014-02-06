package org.kuali.common.util.enc;

import java.util.List;

import org.jasypt.util.text.TextEncryptor;
import org.kuali.common.util.Assert;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.Str;
import org.kuali.common.util.spring.SpringUtils;
import org.kuali.common.util.spring.env.BasicEnvironmentService;
import org.kuali.common.util.spring.env.EnvUtils;
import org.kuali.common.util.spring.env.EnvironmentService;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

public final class EncContext {

	private final Optional<TextEncryptor> textEncryptor;
	private final EncStrength strength;

	private EncContext(Builder builder) {
		this.strength = builder.strength;
		this.textEncryptor = builder.textEncryptor;
	}

	public Optional<TextEncryptor> getTextEncryptor() {
		return textEncryptor;
	}

	public EncStrength getStrength() {
		return strength;
	}

	public static Builder builder(String password) {
		return new Builder(password);
	}

	public static Builder builder(EnvironmentService env) {
		return new Builder(env);
	}

	public static class Builder {

		// Required (but optional)
		private final Optional<String> password;
		private final Optional<EnvironmentService> env;

		// Optional
		private Optional<TextEncryptor> textEncryptor = Optional.absent();
		private EncStrength strength = EncStrength.BASIC;
		private boolean required = false;
		private boolean removeSystemProperties = false;

		private static final List<String> PASSWORD_KEYS = ImmutableList.of("enc.password", "properties.enc.password");
		private static final List<String> STRENGTH_KEYS = ImmutableList.of("enc.strength", "properties.enc.strength");
		private static final List<String> PASSWORD_REQUIRED_KEYS = ImmutableList.of("enc.password.required", "properties.decrypt");
		private static final String PASSWORD_REMOVE_KEY = "enc.password.removeSystemProperty";

		/**
		 * Setup encryption using <code>password</code>
		 */
		public Builder(String password) {
			this(EnvUtils.ABSENT, Optional.of(password));
		}

		/**
		 * Use the password they gave us, unless it is overridden by a password in the environment
		 */
		public Builder(EnvironmentService env, String password) {
			this(Optional.of(env), Optional.of(password));
		}

		/**
		 * Use system properties / environment variables to locate the encryption password
		 */
		public Builder() {
			this(new BasicEnvironmentService());
		}

		/**
		 * Locate the encryption password in the environment
		 */
		public Builder(EnvironmentService env) {
			this(Optional.of(env), Optional.<String> absent());
		}

		private Builder(Optional<EnvironmentService> env, Optional<String> password) {
			if (env.isPresent()) {
				this.password = SpringUtils.getString(env, PASSWORD_KEYS, password);
			} else {
				this.password = password;
			}
			this.env = env;
		}

		public Builder removeSystemProperties(boolean removeSystemProperties) {
			this.removeSystemProperties = removeSystemProperties;
			return this;
		}

		public Builder required(boolean required) {
			this.required = required;
			return this;
		}

		public Builder strength(EncStrength strength) {
			this.strength = strength;
			return this;
		}

		private void override() {
			if (env.isPresent()) {
				strength(SpringUtils.getProperty(env, STRENGTH_KEYS, EncStrength.class, strength));
				required(SpringUtils.getProperty(env, PASSWORD_REQUIRED_KEYS, Boolean.class, required));
				removeSystemProperties(env.get().getBoolean(PASSWORD_REMOVE_KEY, removeSystemProperties));
			}
		}

		private void validate(EncContext ctx, boolean required, Optional<String> password) {
			Assert.notNull(ctx.getTextEncryptor(), "'textEncryptor' cannot be null");
			Assert.notNull(ctx.getStrength(), "'strength' cannot be null");
			if (required) {
				Assert.isTrue(ctx.getTextEncryptor().isPresent());
			}
			if (password.isPresent()) {
				Assert.noBlanks(password.get());
				Assert.notEncrypted(password.get());
				Assert.notConcealed(password.get());
			}
		}

		private void finish() {
			override();
			if (password.isPresent()) {
				String revealed = Str.reveal(password.get());
				TextEncryptor enc = EncUtils.getTextEncryptor(revealed, strength);
				this.textEncryptor = Optional.of(enc);
			}
		}

		public EncContext build() {
			// Finish setting up the builder
			finish();

			// Get local references to builder instance variables
			boolean required = this.required;
			boolean removeSystemProperties = this.removeSystemProperties;
			Optional<String> password = Optional.fromNullable(this.password.orNull());

			// Construct the encryption context
			EncContext ctx = new EncContext(this);

			// Validate that it's in good shape
			validate(ctx, required, password);

			// Now that we've successfully created and validated the instance, it's safe to remove the system properties
			if (removeSystemProperties) {
				PropertyUtils.removeSystemProperties(PASSWORD_KEYS);
			}

			// Return the context
			return ctx;
		}

	}

}
