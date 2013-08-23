package org.kuali.common.impex.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.kuali.common.util.nullify.NullUtils;
import org.kuali.common.util.xml.jaxb.OmitFalseAdapter;
import org.kuali.common.util.xml.jaxb.OmitNoneStringAdapter;
import org.kuali.common.util.xml.jaxb.OmitTrueAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
public class Column implements NamedElement {

	public static final Boolean DEFAULT_NULLABLE_VALUE = true;
	public static final Boolean DEFAULT_PRIMARY_KEY_VALUE = false;

	@XmlAttribute
	private final String name;

	@XmlAttribute
	private final DataType type;

	@XmlAttribute
	private final DataTypeSize size;

	@XmlAttribute
	private final String defaultValue;

	@XmlAttribute
	@XmlJavaTypeAdapter(OmitNoneStringAdapter.class)
	private final String description;

	@XmlAttribute
	@XmlJavaTypeAdapter(OmitFalseAdapter.class)
	private final Boolean primaryKey;

	@XmlAttribute
	@XmlJavaTypeAdapter(OmitTrueAdapter.class)
	private final Boolean nullable;

	@Override
	public String getName() {
		return name;
	}

	public DataType getType() {
		return type;
	}

	public DataTypeSize getSize() {
		return size;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public String getDescription() {
		return description;
	}

	public boolean getPrimaryKey() {
		return primaryKey;
	}

	public boolean getNullable() {
		return nullable;
	}

	public static class Builder {

		private final String name;
		private final DataType type;

		private DataTypeSize size;
		private String defaultValue;
		private String description = NullUtils.NONE;
		private boolean primaryKey = false;
		private boolean nullable = true;

		public Builder(String name, DataType type) {
			this.name = name;
			this.type = type;
		}

		public Column build() {
			return new Column(this);
		}
	}

	private Column(Builder builder) {
		this.name = builder.name;
		this.type = builder.type;
		this.size = builder.size;
		this.defaultValue = builder.defaultValue;
		this.description = builder.description;
		this.primaryKey = builder.primaryKey;
		this.nullable = builder.nullable;
	}

}
