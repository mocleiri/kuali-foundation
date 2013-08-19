package org.kuali.common.impex.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.kuali.common.util.Assert;
import org.kuali.common.util.nullify.NullUtils;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public final class ImmutableTable implements NamedElement {

	@XmlAttribute
	private final String name;

	@XmlAttribute
	private final String description;

	@SuppressWarnings("unused")
	private ImmutableTable() {
		this(NullUtils.NONE);
	}

	public ImmutableTable(String name) {
		this(name, NullUtils.NONE);
	}

	public ImmutableTable(String name, String description) {
		Assert.noBlanks(name, description);
		this.name = name;
		this.description = description;
	}

	@Override
	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

}
