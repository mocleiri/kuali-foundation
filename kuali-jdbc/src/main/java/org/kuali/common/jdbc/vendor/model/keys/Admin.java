package org.kuali.common.jdbc.vendor.model.keys;

public enum Admin implements KeySuffix {

	VALIDATE("validate"), DROP("drop"), CREATE("create");

	private Admin(String value) {
		this.value = value;
	}

	private final String value;

	@Override
	public String getKeySuffix() {
		return value;
	}

}
