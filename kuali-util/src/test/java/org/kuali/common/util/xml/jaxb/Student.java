package org.kuali.common.util.xml.jaxb;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import org.kuali.common.util.Assert;

import com.google.common.collect.ImmutableList;

public final class Student {

	@XmlAttribute
	private final String name;

	@XmlElement
	private final List<String> teams;

	@XmlElement
	private final List<String> sports;

	public String getName() {
		return name;
	}

	public List<String> getTeams() {
		return teams;
	}

	public List<String> getSports() {
		return sports;
	}

	@SuppressWarnings("unused")
	private Student() {
		this(null, ImmutableList.<String> of(), ImmutableList.<String> of());
	}

	public Student(String name, List<String> teams, String... sports) {
		this(name, teams, ImmutableList.copyOf(sports));
	}

	public Student(String name, List<String> teams, List<String> sports) {
		Assert.noNulls(teams, sports);
		this.name = name;
		this.teams = teams;
		this.sports = sports;
	}

}
