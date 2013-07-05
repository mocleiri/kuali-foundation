package org.kuali.common.impex.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This interface provides an implementation-independent API to access database table model information
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Table implements NamedElement {

	String name;
	List<Column> columns;
	List<UniqueConstraint> uniqueConstraints;
	List<Index> indices;
	String description;

	public Table() {
		this(null);
	}

	public Table(String n) {
		name = n;
		columns = new ArrayList<Column>();
		uniqueConstraints = new ArrayList<UniqueConstraint>();
		indices = new ArrayList<Index>();
	}

	@XmlElement(name = "column")
	public List<Column> getColumns() {
		return columns;
	}

	@XmlElement(name = "uniqueConstraint")
	public List<UniqueConstraint> getUniqueConstraints() {
		return uniqueConstraints;
	}

	@Override
	@XmlAttribute
	public String getName() {
		return name;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	@XmlAttribute
	public void setDescription(String description) {
		this.description = description;
	}

	public List<Index> getIndices() {
		return indices;
	}

	public void setIndices(List<Index> indices) {
		this.indices = indices;
	}

	public void setUniqueConstraints(List<UniqueConstraint> uniqueConstraints) {
		this.uniqueConstraints = uniqueConstraints;
	}

}
