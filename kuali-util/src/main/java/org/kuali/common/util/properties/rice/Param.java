package org.kuali.common.util.properties.rice;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
public class Param implements Comparable<Param> {

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

	public static Param create(String name, String value) {
		return builder(name, value).build();
	}

	public static Builder builder(String name, String value) {
		return new Builder(name, value);
	}

	public static class Builder {

		// Required
		private final String name;
		private final String value;

		// Optional
		private boolean override = false;
		private boolean random = false;
		private boolean system = false;

		public Builder(String name, String value) {
			this.name = name;
			this.value = value;
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

		private Builder initialized() {
			return this;
		}

		public Param build() {
			Param instance = new Param(this);
			validate(instance);
			return instance;
		}

		private static void validate(Param instance) {
			checkNotNull(instance.name, "'name' cannot be null");
			checkNotNull(instance.value, "'value' cannot be null");
		}

	}

	private Param() {
		this(new Builder(null, null).initialized());
	}

	private Param(Builder builder) {
		this.name = builder.name;
		this.value = builder.value;
		this.override = builder.override;
		this.random = builder.random;
		this.system = builder.system;
	}

	@Override
	public int compareTo(Param param) {
		return this.name.compareTo(param.getName());
	}

}
