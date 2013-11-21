package org.kuali.common.devops.aws.sysadmin.model;

import java.util.EnumSet;

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

	// Installed on the CI master and build slaves
	public static final EnumSet<Packages> SCM = EnumSet.of(SVN, GIT);

}
