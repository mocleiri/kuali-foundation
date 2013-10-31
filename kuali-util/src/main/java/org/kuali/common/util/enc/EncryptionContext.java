package org.kuali.common.util.enc;

import org.kuali.common.util.Assert;

import com.google.common.base.Optional;

public final class EncryptionContext {

	public static final EncryptionContext DEFAULT = new EncryptionContext();

	public EncryptionContext() {
		this(false, Optional.<String> absent(), EncStrength.DEFAULT_VALUE);
	}

	public EncryptionContext(boolean passwordRequired, Optional<String> password, EncStrength strength) {
		Assert.noNulls(password, strength);
		if (passwordRequired) {
			Assert.isTrue(password.isPresent(), "Encryption password is required");
		}
		if (password.isPresent()) {
			Assert.noBlanks(password.get());
		}
		this.passwordRequired = passwordRequired;
		this.enabled = password.isPresent();
		this.password = password;
		this.strength = strength;
	}

	private final boolean enabled;
	private final boolean passwordRequired;
	private final Optional<String> password;
	private final EncStrength strength;

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

}
