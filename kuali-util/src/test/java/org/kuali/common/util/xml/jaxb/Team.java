package org.kuali.common.util.xml.jaxb;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.kuali.common.util.Assert;
import org.kuali.common.util.nullify.NullUtils;
import org.kuali.common.util.xml.jaxb.adapter.ImmutableListAdapter;

import com.google.common.collect.ImmutableList;

@XmlRootElement
public class Team {

	@XmlAttribute
	private final String name;

	@XmlAttribute
	private final String color;

	@XmlElement
	@XmlJavaTypeAdapter(ImmutableListAdapter.class)
	private final List<String> students;

	public Team() {
		this(NullUtils.NONE, NullUtils.NONE, ImmutableList.<String> of());
	}

	public Team(String name, String color, String student) {
		this(name, color, ImmutableList.<String> of(student));
	}

	public Team(String name, String color, String... students) {
		this(name, color, ImmutableList.<String> copyOf(students));
	}

	public Team(String name, String color, List<String> students) {
		Assert.noBlanks(name, color);
		this.name = name;
		this.color = color;
		this.students = students;
	}

	public String getName() {
		return name;
	}

	public String getColor() {
		return color;
	}

	public List<String> getStudents() {
		return students;
	}

}
