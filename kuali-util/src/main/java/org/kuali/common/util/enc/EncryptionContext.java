package org.kuali.common.util.enc;

import org.kuali.common.util.Assert;

public final class EncryptionContext {

	public static final EncStrength DEFAULT_ENC_STRENGTH = EncStrength.BASIC;

	public EncryptionContext(String password) {
		this(password, DEFAULT_ENC_STRENGTH);
	}

	public EncryptionContext(String password, EncStrength strength) {
		Assert.noNulls(password, strength);
		Assert.noBlanks(password);
		this.password = password;
		this.strength = strength;
	}

	private final String password;
	private final EncStrength strength;

	public String getPassword() {
		return password;
	}

	public EncStrength getStrength() {
		return strength;
	}

}
