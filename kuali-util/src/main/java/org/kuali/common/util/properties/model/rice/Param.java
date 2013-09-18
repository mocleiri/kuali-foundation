package org.kuali.common.util.properties.model.rice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

import org.kuali.common.util.Assert;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "value" })
public class Param {

	@XmlAttribute(required = true)
	private final String name;

	@XmlAttribute
	private final boolean override;

	@XmlAttribute
	private final boolean random;

	@XmlAttribute
	private final boolean system;

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
		private boolean override = true;
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
			Assert.noBlanks(name);
			return new Param(this);
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
