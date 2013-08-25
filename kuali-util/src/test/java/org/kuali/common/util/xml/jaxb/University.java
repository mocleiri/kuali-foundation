package org.kuali.common.util.xml.jaxb;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.kuali.common.util.Assert;

import com.google.common.collect.ImmutableList;

@XmlRootElement
public class University {

	@XmlAttribute
	private final String name;
	@XmlElement(name = "sport")
	private final List<Sport> sports;
	@XmlAttribute
	private final List<String> colors;

	public List<Sport> getSports() {
		return sports;
	}

	public List<String> getColors() {
		return colors;
	}

	public String getName() {
		return name;
	}

	University() {
		this.name = null;
		this.sports = null;
		this.colors = null;
	}

	public University(String name, List<Sport> sports, List<String> colors) {
		Assert.noBlanks(name);
		Assert.noNulls(sports, colors);
		this.name = name;
		this.sports = ImmutableList.copyOf(sports);
		this.colors = ImmutableList.copyOf(colors);
	}

}
