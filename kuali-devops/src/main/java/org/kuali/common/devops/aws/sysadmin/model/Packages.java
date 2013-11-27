package org.kuali.common.devops.aws.sysadmin.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

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
	MYSQL_SERVER("mysql-server"), //
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
	public static final Set<Packages> BASIC = Collections.unmodifiableSet(EnumSet.of(MAN, ZIP, UNZIP, WGET, RSYNC, OPENSSH_CLIENTS));

	// Installed on servers that need to interact with an SCM system
	public static final Set<Packages> SCM = Collections.unmodifiableSet(EnumSet.of(SVN, GIT));

	public static final List<String> of(Collection<Packages> packages) {
		List<String> names = new ArrayList<String>();
		for (Packages pkgs : packages) {
			names.add(pkgs.getName());
		}
		return ImmutableList.copyOf(names);
	}

}
