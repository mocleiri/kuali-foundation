package org.kuali.common.impex.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.kuali.common.util.Assert;

/**
 * This interface provides an implementation-independent API to access database table model information
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public final class ImmutableTable implements NamedElement {

	private final String name;
	private final String description;

	public ImmutableTable(String name, String description) {
		Assert.noBlanks(name);
		this.name = name;
		this.description = description;
	}

	@Override
	@XmlAttribute
	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

}
