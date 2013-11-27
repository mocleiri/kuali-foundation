package org.kuali.common.devops.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import com.google.common.collect.ImmutableList;

public enum Pkgs {

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

	private final Pkg pkg;

	private Pkgs(String name) {
		this.pkg = new Pkg.Builder(name).build();
	}

	public Pkg getPackage() {
		return pkg;
	}

	// Basic packages installed on pretty much every server
	public static final Set<Pkgs> BASIC = Collections.unmodifiableSet(EnumSet.of(MAN, ZIP, UNZIP, WGET, RSYNC, OPENSSH_CLIENTS));

	// Installed on servers that need to interact with an SCM system
	public static final Set<Pkgs> SCM = Collections.unmodifiableSet(EnumSet.of(SVN, GIT));

	public static final List<Pkg> of(Collection<Pkgs> packages) {
		List<Pkg> list = new ArrayList<Pkg>();
		for (Pkgs pkgs : packages) {
			list.add(pkgs.getPackage());
		}
		return ImmutableList.copyOf(list);
	}

}
