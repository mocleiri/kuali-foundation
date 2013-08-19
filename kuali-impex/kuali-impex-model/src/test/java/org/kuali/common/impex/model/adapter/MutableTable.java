package org.kuali.common.impex.model.adapter;

import javax.xml.bind.annotation.XmlAttribute;

public class MutableTable {

	public MutableTable() {
		this(null, null);
	}

	public MutableTable(String name) {
		this(name, null);
	}

	public MutableTable(String name, String description) {
		this.name = name;
		this.description = description;
	}

	private String name;
	private String description;

	@XmlAttribute
	public String getName() {
		return name;
	}

	@XmlAttribute
	public String getDescription() {
		return description;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
