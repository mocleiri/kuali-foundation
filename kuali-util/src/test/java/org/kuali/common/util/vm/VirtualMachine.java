package org.kuali.common.util.vm;

import java.io.File;

import org.kuali.common.util.bind.api.Bind;
import org.kuali.common.util.bind.api.BindAlias;
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
		this.jre = builder.jre;
		this.vm = builder.vm;
	}

	public static VirtualMachine build() {
		return builder().build();
	}

	private static Builder builder() {
		return new Builder();
	}

	@Bind
	public static class Builder extends AwesomeBuilder<VirtualMachine> {

		@Bind("user")
		private User user;

		@Bind("os")
		private OperatingSystem operatingSystem;

		@BindAlias("java.io.tmpdir")
		@CanonicalFileFormat
		private File tempDirectory;

		@BindAlias("java.home")
		@CanonicalFileFormat
		private File home;

		@Bind("java.vm.specification")
		private Specification vm;

		@Bind("java.specification")
		private Specification jre;

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

		public Specification getJre() {
			return jre;
		}

		public void setJre(Specification jre) {
			this.jre = jre;
		}

		public Specification getVm() {
			return vm;
		}

		public void setVm(Specification vm) {
			this.vm = vm;
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
