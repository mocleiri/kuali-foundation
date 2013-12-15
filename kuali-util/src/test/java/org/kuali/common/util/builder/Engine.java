package org.kuali.common.util.builder;

import javax.validation.constraints.Min;

import org.kuali.common.util.env.annotation.Env;
import org.kuali.common.util.validate.NoBlanks;
import org.kuali.common.util.validate.NoNulls;

@NoNulls
@NoBlanks
public final class Engine {

	public int getCylinders() {
		return cylinders;
	}

	public void setCylinders(int cylinders) {
		this.cylinders = cylinders;
	}

	public String getCylinderHead() {
		return cylinderHead;
	}

	public void setCylinderHead(String cylinderHead) {
		this.cylinderHead = cylinderHead;
	}

	public String getBlockMaterial() {
		return blockMaterial;
	}

	public void setBlockMaterial(String blockMaterial) {
		this.blockMaterial = blockMaterial;
	}

	@Min(1)
	private int cylinders = 8;
	private String cylinderHead = "aluminum";
	private String blockMaterial = "cast iron";

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

		private int cylinders;
		private String cylinderHead;
		private String blockMaterial;

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
