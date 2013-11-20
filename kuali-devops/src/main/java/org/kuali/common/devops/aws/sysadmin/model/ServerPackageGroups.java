package org.kuali.common.devops.aws.sysadmin.model;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ImmutableList;

public enum ServerPackageGroups {

	BASIC(of("man", "zip", "unzip", "wget", "rsync", "openssh-clients")), //
	SCM(of("subversion", "git")), //
	TOMCAT6(of(BASIC, Tomcat.TC6.getName())), //
	TOMCAT7(of(BASIC, Tomcat.TC7.getName())), //
	CI(of(of(BASIC, SCM), "graphviz", "ant", Tomcat.TC7.getName()));

	public enum Tomcat implements Named {

		TC6("tomcat6"), TC7("tomcat7");

		private String name;

		private Tomcat(String name) {
			this.name = name;
		}

		@Override
		public String getName() {
			return this.name;
		}
	}

	private interface Named {
		String getName();
	}

	private final List<String> names;

	private ServerPackageGroups(List<String> names) {
		this.names = names;
	}

	public List<String> getNames() {
		return names;
	}

	private static List<String> of(ServerPackageGroups packages, String... names) {
		return of(ImmutableList.of(packages), names);
	}

	private static List<String> of(List<ServerPackageGroups> packages, String... names) {
		List<String> newList = new ArrayList<String>();
		for (ServerPackageGroups pkg : packages) {
			newList.addAll(pkg.getNames());
		}
		newList.addAll(ImmutableList.copyOf(names));
		return newList;
	}

	private static List<ServerPackageGroups> of(ServerPackageGroups... packages) {
		return ImmutableList.copyOf(packages);
	}

	private static List<String> of(String... names) {
		return ImmutableList.copyOf(names);
	}

}
