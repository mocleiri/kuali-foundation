package org.kuali.common.devops.aws;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ImmutableList;

public enum SecurityGroupName {

	SSH("ssh"), //
	HTTP("http"), //
	HTTPS("https"); //

	private final String value;

	private SecurityGroupName(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static final List<String> getValues() {
		return getValues(values());
	}

	public static final List<String> getValues(SecurityGroupName... groupNames) {
		List<String> names = new ArrayList<String>();
		for (SecurityGroupName groupName : groupNames) {
			names.add(groupName.getValue());
		}
		return ImmutableList.copyOf(names);
	}
}
