package org.kuali.common.util.validate.hibernate;

import javax.validation.constraints.NotNull;

public class ContactDetails {

	@NotNull(message = "Name is mandatory", payload = Severity.Error.class)
	private final String name;

	@NotNull(message = "Phone number not specified, but not mandatory", payload = Severity.Info.class)
	private final String phoneNumber;

	public ContactDetails(String name, String phoneNumber) {
		this.name = name;
		this.phoneNumber = phoneNumber;
	}

	public String getName() {
		return name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

}