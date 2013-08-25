package org.kuali.common.util.xml.jaxb;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;

import org.kuali.common.util.Assert;

import com.google.common.collect.ImmutableList;

public final class Sport {

	@XmlAttribute
	private final String name;
	private final List<String> teams;

	public String getName() {
		return name;
	}

	Sport() {
		this.name = null;
		this.teams = null;
	}

	public Sport(String name, String... teams) {
		this(name, ImmutableList.<String> copyOf(teams));
	}

	public Sport(String name, List<String> teams) {
		Assert.noBlanks(name);
		Assert.noNulls(teams);
		this.name = name;
		this.teams = ImmutableList.copyOf(teams);
	}

	public List<String> getTeams() {
		return teams;
	}

}
