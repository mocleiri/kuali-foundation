package org.kuali.common.util.xml.jaxb;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public final class Student {

	@XmlAttribute
	private final String name;

	@XmlElement
	private final List<Sport> sports;

	@XmlElement
	private final List<Team> teams;

	public String getName() {
		return name;
	}

	public List<Sport> getSports() {
		return sports;
	}

	public List<Team> getTeams() {
		return teams;
	}

	@SuppressWarnings("unused")
	private Student() {
		this(null, null, null);
	}

	public Student(String name, List<Sport> sports, List<Team> teams) {
		this.name = name;
		this.sports = sports;
		this.teams = teams;
	}

}
