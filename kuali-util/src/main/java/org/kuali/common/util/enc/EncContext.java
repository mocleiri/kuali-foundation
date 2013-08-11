package org.kuali.common.util.enc;

import org.kuali.common.util.Assert;

public final class EncContext {

	public static final EncStrength DEFAULT_ENC_STRENGTH = EncStrength.BASIC;

	public EncContext(String password) {
		this(password, DEFAULT_ENC_STRENGTH);
	}

	public EncContext(String password, EncStrength strength) {
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
