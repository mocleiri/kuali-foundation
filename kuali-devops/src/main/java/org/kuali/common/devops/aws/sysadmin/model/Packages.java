package org.kuali.common.devops.aws.sysadmin.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.List;

import com.google.common.collect.ImmutableList;

public enum Packages {

	MAN("man"), //
	ZIP("zip"), //
	UNZIP("unzip"), //
	WGET("wget"), //
	RSYNC("rsync"), //
	OPENSSH_CLIENTS("openssh-clients"), //
	SVN("subversion"), //
	GIT("git"), //
	GRAPHVIZ("graphviz"), //
	MYSQL("mysql"), //
	ANT("ant"), //
	TOMCAT6("tomcat6"), //
	TOMCAT7("tomcat7"); //

	private final String name;

	private Packages(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	// Basic packages installed on pretty much every server
	public static final EnumSet<Packages> BASIC = EnumSet.of(MAN, ZIP, UNZIP, WGET, RSYNC, OPENSSH_CLIENTS);

	// Installed on servers that need to interact with an SCM system
	public static final EnumSet<Packages> SCM = EnumSet.of(SVN, GIT);

	public static final List<String> of(Collection<Packages> packages) {
		List<String> names = new ArrayList<String>();
		for (Packages pkgs : packages) {
			names.add(pkgs.getName());
		}
		return ImmutableList.copyOf(names);
	}

}
