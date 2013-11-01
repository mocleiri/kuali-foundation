package org.kuali.common.devops.aws;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ImmutableList;

public enum SecurityGroups {

	SSH("ssh"), // Allow ssh from anywhere
	HTTP("http"), // Allow http (port 80) from anywhere
	HTTPS("https"); // Allow https (port 443) from anywhere

	private final String value;

	private SecurityGroups(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static final List<String> getValues() {
		return getValues(values());
	}

	public static final List<String> getValues(SecurityGroups... groupNames) {
		List<String> names = new ArrayList<String>();
		for (SecurityGroups groupName : groupNames) {
			names.add(groupName.getValue());
		}
		return ImmutableList.copyOf(names);
	}
}
