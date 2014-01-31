package org.kuali.common.devops.util;

import java.util.List;

import com.google.common.collect.Lists;

public class Group {

	String name = "na";
	List<Environment> environments = Lists.newArrayList();

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
