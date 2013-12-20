package org.kuali.common.util.builder;

import javax.validation.constraints.Min;

import org.kuali.common.util.env.annotation.Env;
import org.kuali.common.util.validate.NoBlanks;
import org.kuali.common.util.validate.NoNullFields;

@NoNullFields
@NoBlanks
public final class Engine {

	public int getCylinders() {
		return cylinders;
	}

	public String getCylinderHead() {
		return cylinderHead;
	}

	public String getBlockMaterial() {
		return blockMaterial;
	}

	@Min(1)
	private final int cylinders;
	private final String cylinderHead;
	private final String blockMaterial;

	private Engine(Builder builder) {
		this.cylinders = builder.cylinders;
		this.cylinderHead = builder.cylinderHead;
		this.blockMaterial = builder.blockMaterial;
	}

	public static Builder builder() {
		return new Builder();
	}

	@Env(prefix = "car.engine")
	public static class Builder extends AbstractBuilder<Engine> {

		private int cylinders = 8;
		private String cylinderHead = "aluminum";
		private String blockMaterial = "cast iron";

		public Builder withCylinders(int cylinders) {
			this.cylinders = cylinders;
			return this;
		}

		public Builder withCylinderHead(String cylinderHead) {
			this.cylinderHead = cylinderHead;
			return this;
		}

		public Builder withBlockMaterial(String blockMaterial) {
			this.blockMaterial = blockMaterial;
			return this;
		}

		@Override
		public Engine getInstance() {
			return new Engine(this);
		}

	}

}
