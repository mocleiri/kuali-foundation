package org.kuali.common.util.enc;

import java.util.List;

import org.jasypt.util.text.TextEncryptor;
import org.kuali.common.util.Assert;
import org.kuali.common.util.nullify.NullUtils;
import org.kuali.common.util.spring.SpringUtils;
import org.kuali.common.util.spring.env.EnvUtils;
import org.kuali.common.util.spring.env.EnvironmentService;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

public final class EncContext {

	public static final EncContext DEFAULT = new EncContext.Builder().build();

	private final boolean enabled;
	private final boolean passwordRequired;
	private final boolean removePasswordSystemProperty;
	private final Optional<String> password;
	private final EncStrength strength;
	private final List<String> passwordKeys = Builder.PASSWORD_KEYS;

	public static class Builder {

		private boolean passwordRequired = false;
		private boolean removePasswordSystemProperty = true;
		private Optional<String> password = Optional.absent();
		private EncStrength strength = EncStrength.BASIC;
		private Optional<TextEncryptor> textEncryptor = Optional.absent();

		private Optional<EnvironmentService> env = Optional.absent();
		private static final List<String> PASSWORD_KEYS = ImmutableList.of("enc.password", "properties.enc.password");
		private static final List<String> STRENGTH_KEYS = ImmutableList.of("enc.strength", "properties.enc.strength");
		private static final List<String> PASSWORD_REQUIRED_KEYS = ImmutableList.of("enc.password.required", "properties.decrypt");

		// For convenience only. enabled is always equal to -> password.isPresent()
		private boolean enabled = password.isPresent();

		public Builder env(EnvironmentService env) {
			this.env = Optional.of(env);
			return this;
		}

		public Builder removePasswordSystemProperty(boolean removePasswordSystemProperty) {
			this.removePasswordSystemProperty = removePasswordSystemProperty;
			return this;
		}

		public Builder passwordRequired(boolean passwordRequired) {
			this.passwordRequired = passwordRequired;
			return this;
		}

		public Builder password(String password) {
			this.password = NullUtils.toAbsent(password);
			return this;
		}

		public Builder strength(EncStrength strength) {
			this.strength = strength;
			return this;
		}

		private void override() {
			password(SpringUtils.getString(env, PASSWORD_KEYS, password).orNull());
			strength(EnvUtils.getProperty(env, STRENGTH_KEYS, EncStrength.class, strength));
			passwordRequired(EnvUtils.getProperty(env, PASSWORD_REQUIRED_KEYS, Boolean.class, passwordRequired));
		}

		private void validate(EncContext ctx) {
			Assert.noNulls(ctx.getPassword(), ctx.getStrength(), ctx.getPasswordKeys());
			if (ctx.isPasswordRequired()) {
				Assert.isTrue(ctx.getPassword().isPresent(), "Encryption password is required");
			}
			// If the password is present, it can't be blank
			if (ctx.getPassword().isPresent()) {
				Assert.noBlanks(ctx.getPassword().get());
			}
		}

		private void finish() {
			override();
			this.enabled = password.isPresent();
		}

		public EncContext build() {
			finish();
			EncContext ctx = new EncContext(this);
			validate(ctx);
			return ctx;
		}

	}

	private EncContext(Builder builder) {
		this.enabled = builder.enabled;
		this.passwordRequired = builder.passwordRequired;
		this.strength = builder.strength;
		this.removePasswordSystemProperty = builder.removePasswordSystemProperty;
		this.password = builder.password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public EncStrength getStrength() {
		return strength;
	}

	public boolean isPasswordRequired() {
		return passwordRequired;
	}

	public boolean isRemovePasswordSystemProperty() {
		return removePasswordSystemProperty;
	}

	public List<String> getPasswordKeys() {
		return passwordKeys;
	}

	public Optional<String> getPassword() {
		return password;
	}

}
