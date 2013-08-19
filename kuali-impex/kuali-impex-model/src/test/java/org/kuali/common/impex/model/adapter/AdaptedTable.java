package org.kuali.common.impex.model.adapter;

import javax.xml.bind.annotation.XmlAttribute;

public class AdaptedTable {

	public AdaptedTable(String name) {
		this(name, null);
	}

	public AdaptedTable(String name, String description) {
		this.name = name;
		this.description = description;
	}

	@XmlAttribute
	private String name;

	@XmlAttribute
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
