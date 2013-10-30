package org.kuali.common.util.enc;

import org.kuali.common.util.Assert;

import com.google.common.base.Optional;

public final class EncryptionContext {

	public static final EncryptionContext DEFAULT = new EncryptionContext();

	public EncryptionContext() {
		this(false, Optional.<String> absent(), EncStrength.DEFAULT_VALUE);
	}

	public EncryptionContext(boolean enabled, Optional<String> password, EncStrength strength) {
		Assert.noNulls(password, strength);
		if (enabled) {
			Assert.isTrue(password.isPresent(), "Password is required");
			Assert.noBlanks(password.get());
		}
		this.enabled = enabled;
		this.password = password;
		this.strength = strength;
	}

	private final boolean enabled;
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

}
