package org.kuali.common.util.property;

import org.kuali.common.util.EncryptionStrength;

public class PropertyEncryptorContext {

	EncryptionStrength strength = EncryptionStrength.BASIC;
	String password;

	public EncryptionStrength getStrength() {
		return strength;
	}

	public void setStrength(EncryptionStrength strength) {
		this.strength = strength;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
