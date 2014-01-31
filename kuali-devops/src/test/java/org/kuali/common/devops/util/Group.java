package org.kuali.common.devops.util;

import java.util.List;

public class Group {

	String name;
	List<Environment> environments;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Environment> getEnvironments() {
		return environments;
	}

	public void setEnvironments(List<Environment> environments) {
		this.environments = environments;
	}

}
