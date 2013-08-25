package org.kuali.common.util.xml.jaxb;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import com.google.common.collect.ImmutableList;

public class Team {

	@XmlAttribute
	private final String name;

	@XmlAttribute
	private final Sport sport;

	@XmlElement
	private final List<Student> students;

	@SuppressWarnings("unused")
	private Team() {
		this(null, null, (Student) null);
	}

	public Team(String name, Sport sport, Student student) {
		this(name, sport, ImmutableList.<Student> of(student));
	}

	public Team(String name, Sport sport, Student... students) {
		this(name, sport, ImmutableList.<Student> copyOf(students));
	}

	public Team(String name, Sport sport, List<Student> students) {
		this.name = name;
		this.sport = sport;
		this.students = students;
	}

	public String getName() {
		return name;
	}

	public Sport getSport() {
		return sport;
	}

	public List<Student> getStudents() {
		return students;
	}

}
