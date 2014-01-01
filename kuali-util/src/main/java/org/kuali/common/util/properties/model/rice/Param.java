package org.kuali.common.util.properties.model.rice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.kuali.common.util.xml.jaxb.adapter.OmitFalseAdapter;

import com.google.common.base.Preconditions;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "value" })
public class Param {

	@XmlAttribute(required = true)
	private final String name;

	@XmlAttribute
	@XmlJavaTypeAdapter(OmitFalseAdapter.class)
	private final Boolean override;

	@XmlAttribute
	@XmlJavaTypeAdapter(OmitFalseAdapter.class)
	private final Boolean random;

	@XmlAttribute
	@XmlJavaTypeAdapter(OmitFalseAdapter.class)
	private final Boolean system;

	@XmlValue
	private final String value;

	public String getName() {
		return name;
	}

	public boolean isOverride() {
		return override;
	}

	public boolean isRandom() {
		return random;
	}

	public boolean isSystem() {
		return system;
	}

	public String getValue() {
		return value;
	}

	public static class Builder {

		// Required
		private final String name;

		// Optional
		private boolean override = false;
		private boolean random = false;
		private boolean system = false;
		private String value;

		public Builder(String name) {
			this.name = name;
		}

		public Builder override(boolean override) {
			this.override = override;
			return this;
		}

		public Builder random(boolean random) {
			this.random = random;
			return this;
		}

		public Builder system(boolean system) {
			this.system = system;
			return this;
		}

		public Builder value(String value) {
			this.value = value;
			return this;
		}

		private Builder initialized() {
			return this;
		}

		public Param build() {
			Param instance = new Param(this);
			validate(instance);
			return instance;
		}

		private static void validate(Param instance) {
			Preconditions.checkNotNull(instance.name, "'name' cannot be null");
		}

	}

	private Param() {
		this(new Builder(null).initialized());
	}

	private Param(Builder builder) {
		this.name = builder.name;
		this.override = builder.override;
		this.random = builder.random;
		this.system = builder.system;
		this.value = builder.value;
	}

}
