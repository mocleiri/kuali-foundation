package org.kuali.common.util.property;

public class PropertyEncryptorContext {

	PropertyEncStrength strength;
	String password;

	public PropertyEncStrength getStrength() {
		return strength;
	}

	public void setStrength(PropertyEncStrength strength) {
		this.strength = strength;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
