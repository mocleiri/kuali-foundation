package org.kuali.common.util.xml.jaxb;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.kuali.common.util.Assert;

import com.google.common.collect.ImmutableList;

@XmlRootElement
public class University {

	@XmlElement
	private final List<Student> students;

	@XmlElement
	private final List<Team> teams;

	@XmlElement
	private final List<Sport> sports;

	public List<Student> getStudents() {
		return students;
	}

	public List<Team> getTeams() {
		return teams;
	}

	public List<Sport> getSports() {
		return sports;
	}

	@SuppressWarnings("unused")
	private University() {
		this(ImmutableList.<Student> of(), ImmutableList.<Sport> of(), ImmutableList.<Team> of());
	}

	public University(List<Student> students, List<Sport> sports, List<Team> teams) {
		Assert.noNulls(students, teams, sports);
		this.students = students;
		this.teams = teams;
		this.sports = sports;
	}

}
