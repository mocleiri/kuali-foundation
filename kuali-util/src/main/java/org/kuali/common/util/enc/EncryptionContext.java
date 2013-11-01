package org.kuali.common.util.enc;

import org.kuali.common.util.Assert;
import org.kuali.common.util.nullify.NullUtils;

import com.google.common.base.Optional;

public final class EncryptionContext {

	public static final EncryptionContext DEFAULT = new EncryptionContext.Builder().build();

	private final boolean enabled;
	private final boolean passwordRequired;
	private final boolean removePasswordSystemProperty;
	private final Optional<String> password;
	private final Optional<String> passwordKey;
	private final EncStrength strength;

	public static class Builder {

		private boolean passwordRequired = false;
		private boolean removePasswordSystemProperty = true;
		private Optional<String> password = Optional.absent();
		private Optional<String> passwordKey = Optional.absent();
		private EncStrength strength = EncStrength.BASIC;

		// For convenience only. enabled == password.isPresent()
		private boolean enabled = false;

		public Builder removePasswordSystemProperty(boolean removePasswordSystemProperty) {
			this.removePasswordSystemProperty = removePasswordSystemProperty;
			return this;
		}

		public Builder passwordRequired(boolean passwordRequired) {
			this.passwordRequired = passwordRequired;
			return this;
		}

		public Builder password(String password) {
			String nulled = NullUtils.trimToNull(password);
			Optional<String> fromNullable = Optional.fromNullable(nulled);
			this.password = fromNullable;
			return this;
		}

		public Builder passwordKey(String passwordKey) {
			this.password = Optional.fromNullable(NullUtils.trimToNull(passwordKey));
			return this;
		}

		public Builder strength(EncStrength strength) {
			this.strength = strength;
			return this;
		}

		public EncryptionContext build() {
			Assert.noNulls(password, passwordKey, strength, enabled);
			this.enabled = password.isPresent();
			if (passwordRequired) {
				Assert.isTrue(password.isPresent(), "Encryption password is required");
			}
			if (password.isPresent()) {
				Assert.noBlanks(password.get());
			}
			return new EncryptionContext(this);
		}

	}

	private EncryptionContext(Builder builder) {
		this.enabled = builder.enabled;
		this.passwordKey = builder.passwordKey;
		this.passwordRequired = builder.passwordRequired;
		this.strength = builder.strength;
		this.removePasswordSystemProperty = builder.removePasswordSystemProperty;
		this.password = builder.password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public Optional<String> getPassword() {
		return password;
	}

	public EncStrength getStrength() {
		return strength;
	}

	public boolean isPasswordRequired() {
		return passwordRequired;
	}

	public Optional<String> getPasswordKey() {
		return passwordKey;
	}

	public boolean isRemovePasswordSystemProperty() {
		return removePasswordSystemProperty;
	}

}
