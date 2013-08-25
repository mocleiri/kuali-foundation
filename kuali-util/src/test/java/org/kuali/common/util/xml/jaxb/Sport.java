package org.kuali.common.util.xml.jaxb;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

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
		this(null, ImmutableList.<String> of());
	}

	public Sport(String name, String... teams) {
		this(name, ImmutableList.<String> copyOf(teams));
	}

	public Sport(String name, List<String> teams) {
		this.name = name;
		this.teams = teams;
	}

	public List<String> getTeams() {
		return teams;
	}

}
