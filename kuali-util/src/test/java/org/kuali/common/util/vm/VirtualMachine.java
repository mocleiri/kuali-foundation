package org.kuali.common.util.vm;

import java.io.File;

import org.kuali.common.util.bind.api.BindAlias;
import org.kuali.common.util.bind.api.Bound;
import org.kuali.common.util.build.AwesomeBuilder;
import org.kuali.common.util.spring.binder.CanonicalFileFormat;
import org.kuali.common.util.validate.IdiotProofImmutable;

@IdiotProofImmutable
public final class VirtualMachine {

	private final User user;
	private final OperatingSystem operatingSystem;
	private final File tempDirectory;
	private final File home;

	private VirtualMachine(Builder builder) {
		this.user = builder.user;
		this.operatingSystem = builder.operatingSystem;
		this.tempDirectory = builder.tempDirectory;
		this.home = builder.home;
	}

	public static VirtualMachine build() {
		return builder().build();
	}

	private static Builder builder() {
		return new Builder();
	}

	@Bound(prefix = false)
	public static class Builder extends AwesomeBuilder<VirtualMachine> {

		private User user = User.create();
		private OperatingSystem operatingSystem = OperatingSystem.create();
		@BindAlias("java.io.tmpdir")
		@CanonicalFileFormat
		private File tempDirectory;
		@BindAlias("java.home")
		@CanonicalFileFormat
		private File home;

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

}
