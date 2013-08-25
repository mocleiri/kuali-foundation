package org.kuali.common.util.xml.jaxb;

import java.util.List;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.kuali.common.util.Assert;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

@XmlRootElement
public class University {

	@XmlElement
	private final List<Student> students;

	@XmlElement
	private final List<Sport> sports;

	@XmlElement
	private final List<Team> teams;

	@XmlElement
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

	University() {
		this(Lists.<Student> newArrayList(), Lists.<Sport> newArrayList(), Lists.<Team> newArrayList());
	}

	public University(List<Student> students, List<Sport> sports, Team... teams) {
		this(students, sports, ImmutableList.copyOf(teams));
	}

	public University(List<Student> students, List<Sport> sports, List<Team> teams) {
		Assert.noNulls(students, sports, teams);
		this.students = ImmutableList.copyOf(students);
		this.sports = ImmutableList.copyOf(sports);
		this.teams = ImmutableList.copyOf(teams);
		this.colors = ImmutableList.of("yellow", "green");
	}

	void afterUnmarshal(Unmarshaller unmarshaller, Object parent) {
	}

	static <T> List<T> of(List<T> list) {
		if (list == null) {
			return ImmutableList.<T> of();
		} else {
			return ImmutableList.<T> copyOf(list);
		}
	}

}
