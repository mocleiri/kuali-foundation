package org.kuali.common.impex.pojo;

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
	String description;
	List<Column> columns = new ArrayList<Column>();
	List<UniqueConstraint> uniqueConstraints = new ArrayList<UniqueConstraint>();
	List<Index> indices = new ArrayList<Index>();

	public Table() {
		this((String) null);
	}

	public Table(String name) {
		this.name = name;
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

	@XmlAttribute
	public void setDescription(String description) {
		this.description = description;
	}

	@XmlElement(name = "index")
	public List<Index> getIndices() {
		return indices;
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

	public void setIndices(List<Index> indices) {
		this.indices = indices;
	}

	public void setUniqueConstraints(List<UniqueConstraint> uniqueConstraints) {
		this.uniqueConstraints = uniqueConstraints;
	}

}
