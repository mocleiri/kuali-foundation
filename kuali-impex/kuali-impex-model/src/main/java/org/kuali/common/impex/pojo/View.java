package org.kuali.common.impex.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.kuali.common.util.Assert;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class View implements NamedElement {

	@XmlAttribute
	private final String name;

	@XmlAttribute
	private final String query;

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
