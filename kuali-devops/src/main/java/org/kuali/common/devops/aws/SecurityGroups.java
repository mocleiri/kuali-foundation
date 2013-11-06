package org.kuali.common.devops.aws;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ImmutableList;

public enum SecurityGroups {

	SSH("ssh"), // Allow ssh (port 22) from anywhere
	HTTP("http"), // Allow http (port 80) from anywhere
	HTTPS("https"); // Allow https (port 443) from anywhere

	private final String name;

	private SecurityGroups(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public static final List<String> asList() {
		return asList(values());
	}

	public static final List<String> asList(SecurityGroups... groups) {
		List<String> names = new ArrayList<String>();
		for (SecurityGroups group : groups) {
			names.add(group.getName());
		}
		return ImmutableList.copyOf(names);
	}
}
