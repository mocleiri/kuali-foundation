package org.kuali.common.devops.aws.sysadmin.model;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ImmutableList;

public enum Packages {

	BASIC(of("man", "zip", "unzip", "wget", "rsync", "openssh-clients")), //
	SCM(of("subversion", "git")), //
	TOMCAT6(of(BASIC, Tomcat.SIX.getName())), //
	TOMCAT7(of(BASIC, Tomcat.SEVEN.getName())), //
	CI(of(of(BASIC, SCM), "graphviz", "ant", Tomcat.SEVEN.getName()));

	public enum Tomcat implements Named {

		SIX("tomcat6"), SEVEN("tomcat7");

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

	private Packages(List<String> names) {
		this.names = names;
	}

	public List<String> getNames() {
		return names;
	}

	private static List<String> of(Packages packages, String... names) {
		return of(ImmutableList.of(packages), names);
	}

	private static List<String> of(List<Packages> packages, String... names) {
		List<String> newList = new ArrayList<String>();
		for (Packages pkg : packages) {
			newList.addAll(pkg.getNames());
		}
		newList.addAll(ImmutableList.copyOf(names));
		return newList;
	}

	private static List<Packages> of(Packages... packages) {
		return ImmutableList.copyOf(packages);
	}

	private static List<String> of(String... names) {
		return ImmutableList.copyOf(names);
	}

}
