package org.kuali.common.util.property;

public class PropertyEncContext {

	PropertyEncMode mode = PropertyEncMode.NONE;
	PropertyEncStrength strength;
	PropertyEncryptor encryptor;
	String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public PropertyEncMode getMode() {
		return mode;
	}

	public void setMode(PropertyEncMode mode) {
		this.mode = mode;
	}

	public PropertyEncStrength getStrength() {
		return strength;
	}

	public void setStrength(PropertyEncStrength strength) {
		this.strength = strength;
	}

	public PropertyEncryptor getEncryptor() {
		return encryptor;
	}

	public void setEncryptor(PropertyEncryptor encryptor) {
		this.encryptor = encryptor;
	}

}
