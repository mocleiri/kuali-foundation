package org.kuali.common.util.xml.jaxb;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.kuali.common.util.Assert;
import org.kuali.common.util.xml.jaxb.adapter.ImmutableListAdapter;
import org.kuali.common.util.xml.jaxb.adapter.TrimmingCSVStringAdapter;

import com.google.common.collect.ImmutableList;

@XmlRootElement
@XmlClassBindings(classes = Sport.class)
public class University {

	@XmlAttribute
	private final String name;
	@XmlElement
	@XmlJavaTypeAdapter(ImmutableListAdapter.class)
	private final List<Sport> sports;
	@XmlElement
	@XmlJavaTypeAdapter(TrimmingCSVStringAdapter.class)
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
		this.sports = ImmutableList.of();
		this.colors = ImmutableList.of();
	}

	public University(String name, List<Sport> sports, List<String> colors) {
		Assert.noBlanks(name);
		Assert.noNulls(sports, colors);
		this.name = name;
		this.sports = ImmutableList.copyOf(sports);
		this.colors = ImmutableList.copyOf(colors);
	}

}
