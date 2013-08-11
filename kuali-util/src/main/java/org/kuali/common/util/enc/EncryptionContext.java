package org.kuali.common.util.enc;

public final class EncryptionContext {

	private EncryptionContext(String password, EncStrength strength) {
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
