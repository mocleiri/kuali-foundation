package org.kuali.common.impex.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.kuali.common.util.Assert;
import org.kuali.common.util.nullify.NullUtils;
import org.kuali.common.util.xml.jaxb.OmitFalseAdapter;
import org.kuali.common.util.xml.jaxb.OmitOptionalStringAdapter;
import org.kuali.common.util.xml.jaxb.OmitTrueAdapter;

import com.google.common.base.Optional;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public final class Column implements NamedElement {

	@XmlAttribute
	private final String name;

	@XmlAttribute
	private final DataType type;

	@XmlElement
	private final DataTypeSize size;

	@XmlElement
	@XmlJavaTypeAdapter(OmitOptionalStringAdapter.class)
	private final Optional<String> defaultValue;

	@XmlAttribute
	@XmlJavaTypeAdapter(OmitOptionalStringAdapter.class)
	private final Optional<String> description;

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

	public Optional<String> getDefaultValue() {
		return defaultValue;
	}

	public Optional<String> getDescription() {
		return description;
	}

	public boolean getPrimaryKey() {
		return primaryKey;
	}

	public boolean getNullable() {
		return nullable;
	}

	public static class Builder {

		// Required
		private final String name;
		private final DataType type;
		private final DataTypeSize size;

		// Optional
		private Optional<String> defaultValue = Optional.<String> absent();
		private Optional<String> description = Optional.<String> absent();
		private boolean primaryKey = false;
		private boolean nullable = true;

		public Builder(String name, DataType type, DataTypeSize size) {
			this.name = name;
			this.type = type;
			this.size = size;
		}

		public Builder defaultValue(String defaultValue) {
			this.defaultValue = Optional.<String> fromNullable(defaultValue);
			return this;
		}

		public Builder description(String description) {
			this.description = Optional.<String> fromNullable(description);
			return this;
		}

		public Builder primaryKey(boolean primaryKey) {
			this.primaryKey = primaryKey;
			return this;
		}

		public Builder nullable(boolean nullable) {
			this.nullable = nullable;
			return this;
		}

		private Builder finish() {
			Assert.noBlanks(name);
			Assert.noNulls(type, size, defaultValue, description);
			return this;
		}

		public Column build() {
			return new Column(this);
		}

	}

	private Column() {
		this(new Builder(NullUtils.NONE, DataType.BIT, new DataTypeSize(0)).finish());
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
