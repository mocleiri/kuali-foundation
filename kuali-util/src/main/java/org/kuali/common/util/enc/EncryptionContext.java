package org.kuali.common.util.enc;

import org.kuali.common.util.Assert;

import com.google.common.base.Optional;

public final class EncryptionContext {

	public static final EncryptionContext DEFAULT = new EncryptionContext();

	public EncryptionContext() {
		this(false,Optional.<String> absent(), EncStrength.DEFAULT_VALUE);
	}

	public EncryptionContext(boolean required, Optional<String> password, EncStrength strength) {
		Assert.noNulls(password, strength);
		if (required) {
			Assert.isTrue(password.isPresent(),"Encryption password is required");
			Assert.noBlanks(password.get());
		}
		this.required = required;
		this.enabled = password.isPresent();
		this.password = password;
		this.strength = strength;
	}

	private final boolean enabled;
	private final boolean required;
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
