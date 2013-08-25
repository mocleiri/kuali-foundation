package org.kuali.common.util.xml.jaxb;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.kuali.common.util.Assert;
import org.kuali.common.util.xml.jaxb.adapter.ImmutableListAdapter;

import com.google.common.collect.ImmutableList;

@XmlRootElement
@XmlBind(classes = Student.class)
public class University {

	@XmlElement
	@XmlJavaTypeAdapter(ImmutableListAdapter.class)
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
		this.students = null;
		this.teams = null;
		this.sports = null;
	}

	public University(List<Student> students, List<Sport> sports, Team... teams) {
		this(students, sports, ImmutableList.copyOf(teams));
	}

	public University(List<Student> students, List<Sport> sports, List<Team> teams) {
		Assert.noNulls(students, sports, teams);
		this.students = students;
		this.teams = teams;
		this.sports = sports;
	}

}
