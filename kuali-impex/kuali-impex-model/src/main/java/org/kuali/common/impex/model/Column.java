package org.kuali.common.impex.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * This interface provides an implementation-independent API to access database column model information
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Column implements NamedElement {

	public static final Boolean DEFAULT_NULLABLE_VALUE = true;
	public static final Boolean DEFAULT_PRIMARY_KEY_VALUE = false;

	String name;
	DataType dataType;
	Size size;
	String defaultValue;
	String description;
	Boolean primaryKey = DEFAULT_PRIMARY_KEY_VALUE;
	Boolean nullable = DEFAULT_NULLABLE_VALUE;

	/**
	 * This is a copy constructor. It must create a perfect, deep, copy of this object
	 */
	public Column(Column column) {
		this.name = column.getName();
		this.dataType = column.getDataType();
		this.size = new Size(column.getSize());
		this.defaultValue = column.getDefaultValue();
		this.description = column.getDescription();
		this.primaryKey = column.isPrimaryKey();
		this.nullable = column.isNullable();
	}

	public Column() {
		this(null, null);
	}

	/**
	 * Create a new instance of a Column
	 * 
	 * All values are initialized, with a special note that nullable is initially set to true
	 */
	public Column(String name, DataType dataType) {
		this.name = name;
		this.dataType = dataType;
	}

	@XmlAttribute(name = "type")
	public DataType getDataType() {
		return dataType;
	}

	@Override
	@XmlAttribute
	public String getName() {
		return name;
	}

	@XmlAttribute
	public Boolean isPrimaryKey() {
		return primaryKey;
	}

	@XmlAttribute
	public Boolean isNullable() {
		return nullable;
	}

	@XmlAttribute
	public String getDescription() {
		return description;
	}

	public void setPrimaryKey(Boolean primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Size getSize() {
		return size;
	}

	public void setSize(Size typeSize) {
		this.size = typeSize;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public void setNullable(Boolean nullable) {
		this.nullable = nullable;
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
}
