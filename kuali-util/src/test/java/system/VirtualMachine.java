package system;

import org.kuali.common.util.build.AwesomeBuilder;
import org.kuali.common.util.validate.IdiotProofImmutable;

@IdiotProofImmutable
public final class VirtualMachine {

	private final String name;
	private final String vendor;
	private final String version;
	private final Specification specification;

	private VirtualMachine(Builder builder) {
		this.specification = builder.specification;
		this.name = builder.name;
		this.vendor = builder.vendor;
		this.version = builder.version;
	}

	public static class Builder extends AwesomeBuilder<VirtualMachine> {

		private Specification specification;
		private String name;
		private String vendor;
		private String version;

		public Builder specification(Specification specification) {
			this.specification = specification;
			return this;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder vendor(String vendor) {
			this.vendor = vendor;
			return this;
		}

		public Builder version(String version) {
			this.version = version;
			return this;
		}

		@Override
		public VirtualMachine getInstance() {
			return new VirtualMachine(this);
		}

		public Specification getSpecification() {
			return specification;
		}

		public void setSpecification(Specification specification) {
			this.specification = specification;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getVendor() {
			return vendor;
		}

		public void setVendor(String vendor) {
			this.vendor = vendor;
		}

		public String getVersion() {
			return version;
		}

		public void setVersion(String version) {
			this.version = version;
		}

	}

	public Specification getSpecification() {
		return specification;
	}

	public String getName() {
		return name;
	}

	public String getVendor() {
		return vendor;
	}

	public String getVersion() {
		return version;
	}

}
