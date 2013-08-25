package org.kuali.common.util.xml.jaxb;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import org.kuali.common.util.Assert;

import com.google.common.collect.ImmutableList;

public class Team {

	@XmlAttribute
	private final String name;

	@XmlAttribute
	private final String sport;

	@XmlElement
	private final List<String> students;

	@SuppressWarnings("unused")
	private Team() {
		this.name = null;
		this.sport = null;
		this.students = null;
	}

	public Team(String name, String sport, String student) {
		this(name, sport, ImmutableList.<String> of(student));
	}

	public Team(String name, String sport, String... students) {
		this(name, sport, ImmutableList.<String> copyOf(students));
	}

	public Team(String name, String sport, List<String> students) {
		Assert.noBlanks(name, sport);
		Assert.noNulls(students);
		this.name = name;
		this.sport = sport;
		this.students = students;
	}

	public String getName() {
		return name;
	}

	public String getSport() {
		return sport;
	}

	public List<String> getStudents() {
		return students;
	}

}
