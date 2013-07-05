package org.kuali.common.impex.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * This interface provides an implementation-independent API to access database column model information
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Column implements NamedElement {

	public static final boolean DEFAULT_NULLABLE_VALUE = true;

	String name;
	DataType dataType;
	Table table;
	TypeSize typeSize;
	String defaultValue;
	String description;
	boolean primaryKey;
	boolean nullable = DEFAULT_NULLABLE_VALUE;

	public Column() {
		this(null, null, null);
	}

	/**
	 * Create a new instance of a Column
	 * 
	 * All values are initialized, with a special note that nullable is initially set to true
	 */
	public Column(String name, DataType dataType, Table table) {
		this.name = name;
		this.dataType = dataType;
		this.table = table;
	}

	@XmlAttribute
	public DataType getDataType() {
		return dataType;
	}

	@Override
	@XmlAttribute
	public String getName() {
		return name;
	}

	@XmlAttribute
	public boolean isPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(boolean primaryKey) {
		this.primaryKey = primaryKey;
	}

	public TypeSize getTypeSize() {
		return typeSize;
	}

	public void setTypeSize(TypeSize typeSize) {
		this.typeSize = typeSize;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	@XmlAttribute
	public boolean isNullable() {
		return nullable;
	}

	public void setNullable(boolean nullable) {
		this.nullable = nullable;
	}

	@XmlAttribute
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setDataType(DataType columnDataType) {
		this.dataType = columnDataType;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}
}
