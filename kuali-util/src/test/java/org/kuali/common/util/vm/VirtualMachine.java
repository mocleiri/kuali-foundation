package org.kuali.common.util.vm;

import java.io.File;

import org.kuali.common.util.bind.api.Alias;
import org.kuali.common.util.bind.api.Bind;
import org.kuali.common.util.build.AwesomeBuilder;
import org.kuali.common.util.spring.binder.CanonicalFileFormat;
import org.kuali.common.util.validate.IdiotProofImmutable;

@IdiotProofImmutable
public final class VirtualMachine {

	private final User user;
	private final OperatingSystem operatingSystem;
	private final File tempDirectory;
	private final File home;
	private final Specification jre;
	private final Specification vm;

	private VirtualMachine(Builder builder) {
		this.user = builder.user;
		this.operatingSystem = builder.operatingSystem;
		this.tempDirectory = builder.tempDirectory;
		this.home = builder.home;
		this.jre = builder.jreSpecification;
		this.vm = builder.jvmSpecification;
	}

	public static VirtualMachine build() {
		return builder().build();
	}

	private static Builder builder() {
		return new Builder();
	}

	@Bind(prefix = false)
	public static class Builder extends AwesomeBuilder<VirtualMachine> {

		@Bind
		private User user;

		@Bind("os")
		private OperatingSystem operatingSystem;

		@Alias("java.io.tmpdir")
		@CanonicalFileFormat
		private File tempDirectory;

		@Alias("java.home")
		@CanonicalFileFormat
		private File home;

		@Bind("java.vm.specification")
		private Specification jvmSpecification;

		@Bind("java.specification")
		private Specification jreSpecification;

		@Override
		public VirtualMachine getInstance() {
			return new VirtualMachine(this);
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public OperatingSystem getOperatingSystem() {
			return operatingSystem;
		}

		public void setOperatingSystem(OperatingSystem operatingSystem) {
			this.operatingSystem = operatingSystem;
		}

		public File getTempDirectory() {
			return tempDirectory;
		}

		public void setTempDirectory(File tempDirectory) {
			this.tempDirectory = tempDirectory;
		}

		public File getHome() {
			return home;
		}

		public void setHome(File home) {
			this.home = home;
		}

		public Specification getJreSpecification() {
			return jreSpecification;
		}

		public void setJreSpecification(Specification jre) {
			this.jreSpecification = jre;
		}

		public Specification getJvmSpecification() {
			return jvmSpecification;
		}

		public void setJvmSpecification(Specification vm) {
			this.jvmSpecification = vm;
		}

	}

	public OperatingSystem getOperatingSystem() {
		return operatingSystem;
	}

	public User getUser() {
		return user;
	}

	public File getTempDirectory() {
		return tempDirectory;
	}

	public File getHome() {
		return home;
	}

	public Specification getJre() {
		return jre;
	}

	public Specification getVm() {
		return vm;
	}

}
