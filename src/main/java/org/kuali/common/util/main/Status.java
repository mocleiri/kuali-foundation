package org.kuali.common.util.main;

public enum Status {

	SUCCESS(0), FAILURE(1);

	private Status(int value) {
		this.value = value;
	}

	private final int value;

	public int getValue() {
		return value;
	}

}
