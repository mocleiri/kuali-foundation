package org.kuali.common.util.enc;

import org.kuali.common.util.Assert;

public final class EncryptionServiceContext {

	public static final boolean DEFAULT_ENABLED = false;
	public static final String DEFAULT_DISABLED_PASSWORD = "DISABLED";
	public static final EncryptionServiceContext DISABLED = new EncryptionServiceContext(DEFAULT_DISABLED_PASSWORD);

	public EncryptionServiceContext(String password) {
		this(password, DEFAULT_ENABLED);
	}

	public EncryptionServiceContext(String password, boolean enabled) {
		this(password, EncStrength.DEFAULT_VALUE, enabled);
	}

	public EncryptionServiceContext(String password, EncStrength strength) {
		this(password, strength, DEFAULT_ENABLED);
	}

	public EncryptionServiceContext(String password, EncStrength strength, boolean enabled) {
		Assert.noNulls(password, strength);
		Assert.noBlanks(password);
		this.password = password;
		this.strength = strength;
		this.enabled = enabled;
	}

	private final String password;
	private final EncStrength strength;
	private final boolean enabled;

	public String getPassword() {
		return password;
	}

	public EncStrength getStrength() {
		return strength;
	}

	public boolean isEnabled() {
		return enabled;
	}

}
