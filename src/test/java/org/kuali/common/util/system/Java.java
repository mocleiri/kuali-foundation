package org.kuali.common.util.system;

import java.io.File;
import java.util.List;

import javax.validation.Valid;

import org.kuali.common.util.bind.api.Bind;
import org.kuali.common.util.bind.api.BindingAlias;
import org.kuali.common.util.bind.api.BindingPrefix;
import org.kuali.common.util.bind.api.NoBindingPrefix;
import org.kuali.common.util.build.AwesomeBuilder;
import org.kuali.common.util.validate.IdiotProofImmutable;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

@IdiotProofImmutable
public final class Java {

	private final File home;

	@BindingAlias("io.tmpdir")
	private final File temporaryDirectory;

	@BindingAlias("compiler")
	private final Optional<String> jitCompiler;

	@BindingAlias("class.version")
	private final String classVersion;

	@BindingAlias("class.path")
	private final ImmutableList<String> classPath;

	@BindingAlias("library.path")
	private final ImmutableList<String> libraryPaths;

	@BindingAlias("ext.dirs")
	private final ImmutableList<String> extensionDirectories;

	@Valid
	@Bind
	@NoBindingPrefix
	private final RuntimeEnvironment runtimeEnvironment;

	@Valid
	@Bind
	@BindingPrefix("vm")
	private final VirtualMachine virtualMachine;

	private Java(Builder builder) {
		this.home = builder.home;
		this.temporaryDirectory = builder.temporaryDirectory;
		this.jitCompiler = builder.jitCompiler;
		this.classVersion = builder.classVersion;
		this.classPath = ImmutableList.copyOf(builder.classPath);
		this.libraryPaths = ImmutableList.copyOf(builder.libraryPaths);
		this.extensionDirectories = ImmutableList.copyOf(builder.extensionDirectories);
		this.runtimeEnvironment = builder.runtimeEnvironment;
		this.virtualMachine = builder.virtualMachine;
	}

	public static class Builder extends AwesomeBuilder<Java> {

		private File home;
		private File temporaryDirectory;
		private Optional<String> jitCompiler;
		private String classVersion;
		private List<String> classPath;
		private List<String> libraryPaths;
		private List<String> extensionDirectories;
		private RuntimeEnvironment runtimeEnvironment;
		private VirtualMachine virtualMachine;

		public Builder home(File home) {
			this.home = home;
			return this;
		}

		public Builder temporaryDirectory(File temporaryDirectory) {
			this.temporaryDirectory = temporaryDirectory;
			return this;
		}

		public Builder jitCompiler(Optional<String> jitCompiler) {
			this.jitCompiler = jitCompiler;
			return this;
		}

		public Builder classVersion(String classVersion) {
			this.classVersion = classVersion;
			return this;
		}

		public Builder classPath(List<String> classPath) {
			this.classPath = classPath;
			return this;
		}

		public Builder libraryPaths(List<String> libraryPaths) {
			this.libraryPaths = libraryPaths;
			return this;
		}

		public Builder extensionDirectories(List<String> extensionDirectories) {
			this.extensionDirectories = extensionDirectories;
			return this;
		}

		public Builder runtimeEnvironment(RuntimeEnvironment runtimeEnvironment) {
			this.runtimeEnvironment = runtimeEnvironment;
			return this;
		}

		public Builder virtualMachine(VirtualMachine virtualMachine) {
			this.virtualMachine = virtualMachine;
			return this;
		}

		@Override
		public Java getInstance() {
			return new Java(this);
		}

		public File getHome() {
			return home;
		}

		public void setHome(File home) {
			this.home = home;
		}

		public File getTemporaryDirectory() {
			return temporaryDirectory;
		}

		public void setTemporaryDirectory(File temporaryDirectory) {
			this.temporaryDirectory = temporaryDirectory;
		}

		public Optional<String> getJitCompiler() {
			return jitCompiler;
		}

		public void setJitCompiler(Optional<String> jitCompiler) {
			this.jitCompiler = jitCompiler;
		}

		public String getClassVersion() {
			return classVersion;
		}

		public void setClassVersion(String classVersion) {
			this.classVersion = classVersion;
		}

		public List<String> getClassPath() {
			return classPath;
		}

		public void setClassPath(List<String> classPath) {
			this.classPath = classPath;
		}

		public List<String> getLibraryPaths() {
			return libraryPaths;
		}

		public void setLibraryPaths(List<String> libraryPaths) {
			this.libraryPaths = libraryPaths;
		}

		public List<String> getExtensionDirectories() {
			return extensionDirectories;
		}

		public void setExtensionDirectories(List<String> extensionDirectories) {
			this.extensionDirectories = extensionDirectories;
		}

		public RuntimeEnvironment getRuntimeEnvironment() {
			return runtimeEnvironment;
		}

		public void setRuntimeEnvironment(RuntimeEnvironment runtimeEnvironment) {
			this.runtimeEnvironment = runtimeEnvironment;
		}

		public VirtualMachine getVirtualMachine() {
			return virtualMachine;
		}

		public void setVirtualMachine(VirtualMachine virtualMachine) {
			this.virtualMachine = virtualMachine;
		}

	}

	public File getHome() {
		return home;
	}

	public File getTemporaryDirectory() {
		return temporaryDirectory;
	}

	public Optional<String> getJitCompiler() {
		return jitCompiler;
	}

	public String getClassVersion() {
		return classVersion;
	}

	public ImmutableList<String> getClassPath() {
		return classPath;
	}

	public ImmutableList<String> getLibraryPaths() {
		return libraryPaths;
	}

	public ImmutableList<String> getExtensionDirectories() {
		return extensionDirectories;
	}

	public RuntimeEnvironment getRuntimeEnvironment() {
		return runtimeEnvironment;
	}

	public VirtualMachine getVirtualMachine() {
		return virtualMachine;
	}

}
