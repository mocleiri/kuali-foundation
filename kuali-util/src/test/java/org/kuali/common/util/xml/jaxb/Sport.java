package org.kuali.common.util.xml.jaxb;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import org.kuali.common.util.Assert;

import com.google.common.collect.ImmutableList;

public final class Sport {

	@XmlAttribute
	private final String name;

	@XmlElement
	private final List<String> teams;

	public String getName() {
		return name;
	}

	@SuppressWarnings("unused")
	private Sport() {
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
		this.teams = teams;
	}

	public List<String> getTeams() {
		return teams;
	}

}
