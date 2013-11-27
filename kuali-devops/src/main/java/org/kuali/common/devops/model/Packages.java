package org.kuali.common.devops.model;

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

	private final Package pkg;

	private Packages(String name) {
		this.pkg = new Package.Builder(name).build();
	}

	public Package getPackage() {
		return pkg;
	}

	// Basic packages installed on pretty much every server
	public static final Set<Packages> BASIC = Collections.unmodifiableSet(EnumSet.of(MAN, ZIP, UNZIP, WGET, RSYNC, OPENSSH_CLIENTS));

	// Installed on servers that need to interact with an SCM system
	public static final Set<Packages> SCM = Collections.unmodifiableSet(EnumSet.of(SVN, GIT));

	public static final List<Package> of(Collection<Packages> packages) {
		List<Package> list = new ArrayList<Package>();
		for (Packages pkgs : packages) {
			list.add(pkgs.getPackage());
		}
		return ImmutableList.copyOf(list);
	}

}
