package org.kuali.common.devops.aws.sysadmin.model;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ImmutableList;

public enum Packages {

	BASIC(of("man", "zip", "unzip", "wget", "rsync", "openssh-clients")), //
	TOMCAT6("tomcat6"), //
	TOMCAT7("tomcat7"), //
	GRAPHVIZ("graphviz"), //
	ANT("ant"), //
	SCM(of("subversion", "git")), //
	WEBSERVER_TOMCAT6(combine(BASIC, TOMCAT7)), //
	WEBSERVER(combine(BASIC, TOMCAT7)), //
	CI(combine(BASIC, SCM, GRAPHVIZ, ANT, TOMCAT7));

	private final List<String> names;

	private Packages(String name) {
		this(ImmutableList.of(name));
	}

	private Packages(List<String> names) {
		this.names = names;
	}

	public List<String> getNames() {
		return names;
	}

	private static List<String> of(String... names) {
		return ImmutableList.copyOf(names);
	}

	private static List<String> combine(Packages... packages) {
		List<String> strings = new ArrayList<String>();
		for (Packages p : packages) {
			strings.addAll(p.getNames());
		}
		return ImmutableList.copyOf(strings);
	}

}
