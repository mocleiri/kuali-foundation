package org.kuali.common.util.xml.jaxb;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.kuali.common.util.Assert;

import com.google.common.collect.ImmutableList;

@XmlRootElement
public class University {

	private final List<Student> students;
	private final List<Sport> sports;
	private final List<Team> teams;
	private final List<String> colors;

	public List<Student> getStudents() {
		return students;
	}

	public List<Sport> getSports() {
		return sports;
	}

	public List<Team> getTeams() {
		return teams;
	}

	public List<String> getColors() {
		return colors;
	}

	University() {
		this.students = null;
		this.sports = null;
		this.teams = null;
		this.colors = null;
	}

	public University(List<Student> students, List<Sport> sports, Team... teams) {
		this(students, sports, ImmutableList.copyOf(teams));
	}

	public University(List<Student> students, List<Sport> sports, List<Team> teams) {
		this(students, sports, teams, ImmutableList.of("yellow", "green"));
	}

	public University(List<Student> students, List<Sport> sports, List<Team> teams, List<String> colors) {
		Assert.noNulls(students, sports, teams);
		this.students = ImmutableList.copyOf(students);
		this.sports = ImmutableList.copyOf(sports);
		this.teams = ImmutableList.copyOf(teams);
		this.colors = ImmutableList.copyOf(colors);
	}

}
