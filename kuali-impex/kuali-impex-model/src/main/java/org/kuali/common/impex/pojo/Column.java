package org.kuali.common.impex.pojo;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.kuali.common.util.Assert;
import org.kuali.common.util.xml.jaxb.adapter.FlattenOptionalStringAdapter;
import org.kuali.common.util.xml.jaxb.adapter.OmitFalseAdapter;
import org.kuali.common.util.xml.jaxb.adapter.OmitOptionalIntegerAdapter;
import org.kuali.common.util.xml.jaxb.adapter.OmitOptionalStringAdapter;
import org.kuali.common.util.xml.jaxb.adapter.OmitTrueAdapter;

import com.google.common.base.Optional;

@XmlRootElement
public final class Column implements NamedElement {

	@XmlAttribute
	private final String name;

	@XmlAttribute
	private final DataType type;

	@XmlAttribute
	@XmlJavaTypeAdapter(OmitOptionalIntegerAdapter.class)
	private final Optional<Integer> size;

	@XmlAttribute
	@XmlJavaTypeAdapter(OmitOptionalIntegerAdapter.class)
	private final Optional<Integer> scale; // Number of digits to the right of the decimal point for a numeric value

	@XmlAttribute(name = "default")
	@XmlJavaTypeAdapter(OmitOptionalStringAdapter.class)
	private final Optional<String> defaultValue;

	@XmlAttribute
	@XmlJavaTypeAdapter(OmitFalseAdapter.class)
	private final Boolean primaryKey;

	@XmlAttribute
	@XmlJavaTypeAdapter(OmitTrueAdapter.class)
	private final Boolean nullable;

	@XmlAttribute
	@XmlJavaTypeAdapter(FlattenOptionalStringAdapter.class)
	private final Optional<String> description;

	@Override
	public String getName() {
		return name;
	}

	public DataType getType() {
		return type;
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

		// Optional
		private Optional<Integer> size = Optional.<Integer> absent();
		private Optional<Integer> scale = Optional.<Integer> absent();
		private Optional<String> defaultValue = Optional.<String> absent();
		private Optional<String> description = Optional.<String> absent();
		private boolean primaryKey = false;
		private boolean nullable = true;

		public Builder(String name, DataType type) {
			this.name = name;
			this.type = type;
		}

		public Builder size(int size) {
			this.size = Optional.<Integer> of(size);
			return this;
		}

		public Builder scale(int scale) {
			this.scale = Optional.<Integer> of(scale);
			return this;
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

		private Builder initialized() {
			return this;
		}

		public Column build() {
			Assert.noBlanks(name);
			Assert.noNulls(type, size, scale, defaultValue, description);
			if (size.isPresent()) {
				Assert.isTrue(DataType.isSizeable(type), "size is invalid for type [" + type + "]");
			}
			if (scale.isPresent()) {
				Assert.isTrue(DataType.isScalable(type), "scale is invalid for type [" + type + "]");
			}
			return new Column(this);
		}

	}

	private Column() {
		this(new Builder(null, null).initialized());
	}

	private Column(Builder builder) {
		this.name = builder.name;
		this.type = builder.type;
		this.size = builder.size;
		this.scale = builder.scale;
		this.defaultValue = builder.defaultValue;
		this.description = builder.description;
		this.primaryKey = builder.primaryKey;
		this.nullable = builder.nullable;
	}

}
