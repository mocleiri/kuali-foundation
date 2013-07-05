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
	DataType columnDataType;
	String tableName;
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
	public Column(String name, DataType dataType, String tableName) {
		this.name = name;
		this.columnDataType = dataType;
		this.tableName = tableName;
	}

	@XmlAttribute
	public DataType getColumnDataType() {
		return columnDataType;
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

	public void setColumnDataType(DataType columnDataType) {
		this.columnDataType = columnDataType;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlAttribute
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
}
