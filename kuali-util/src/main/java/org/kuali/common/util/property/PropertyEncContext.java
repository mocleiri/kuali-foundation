package org.kuali.common.util.property;

public class PropertyEncContext {

	PropertyEncMode mode = PropertyEncMode.NONE;
	PropertyEncStrength strength;
	String password;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
