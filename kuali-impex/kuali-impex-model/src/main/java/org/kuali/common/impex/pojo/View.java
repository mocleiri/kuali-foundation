package org.kuali.common.impex.pojo;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.kuali.common.util.Assert;

@XmlRootElement
public final class View implements NamedElement {

	@XmlAttribute
	private final String name;

	@XmlAttribute
	private final String query;

	View() {
		this.name = null;
		this.query = null;
	}

	public View(String name, String query) {
		Assert.noBlanks(name, query);
		this.name = name;
		this.query = query;
	}

	@Override
	public String getName() {
		return name;
	}

	public String getQuery() {
		return query;
	}

}
