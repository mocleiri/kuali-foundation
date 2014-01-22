package system;

import org.kuali.common.util.build.AwesomeBuilder;
import org.kuali.common.util.validate.IdiotProofImmutable;

@IdiotProofImmutable
public final class Specification {

	private final String vendor;
	private final String version;
	private final String name;

	private Specification(Builder builder) {
		this.vendor = builder.vendor;
		this.version = builder.version;
		this.name = builder.name;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends AwesomeBuilder<Specification> {

		private String vendor;
		private String version;
		private String name;

		public Builder vendor(String vendor) {
			this.vendor = vendor;
			return this;
		}

		public Builder version(String version) {
			this.version = version;
			return this;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		@Override
		public Specification getInstance() {
			return new Specification(this);
		}

		public String getVersion() {
			return version;
		}

		public void setVersion(String version) {
			this.version = version;
		}

		public String getVendor() {
			return vendor;
		}

		public void setVendor(String vendor) {
			this.vendor = vendor;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

	}

	public String getVersion() {
		return version;
	}

	public String getVendor() {
		return vendor;
	}

	public String getName() {
		return name;
	}

}
