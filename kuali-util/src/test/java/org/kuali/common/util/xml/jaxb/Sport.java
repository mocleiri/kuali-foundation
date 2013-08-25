package org.kuali.common.util.xml.jaxb;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.common.collect.ImmutableList;

@XmlRootElement
public final class Sport {

	@XmlAttribute
	private final String name;

	@XmlElement
	private final List<Team> teams;

	public String getName() {
		return name;
	}

	@SuppressWarnings("unused")
	private Sport() {
		this(null);
	}

	public Sport(String name) {
		this(name, ImmutableList.<Team> of());
	}

	public Sport(String name, Team team) {
		this(name, ImmutableList.of(team));
	}

	public Sport(String name, Team... teams) {
		this(name, ImmutableList.copyOf(teams));
	}

	public Sport(String name, List<Team> teams) {
		this.name = name;
		this.teams = teams;
	}

}
