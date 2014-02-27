package org.kuali.common.util.system;

import static com.google.common.base.Optional.absent;
import static org.kuali.common.util.validate.Validation.checkConstraints;

import java.io.File;
import java.util.List;

import org.kuali.common.util.validate.IdiotProofImmutable;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

@IdiotProofImmutable
public final class Java {

	private final File home;
	private final File tmpDir;
	private final Optional<String> jitCompiler;
	private final String classVersion;
	private final ImmutableList<String> classpath;
	private final ImmutableList<String> libraryPaths;
	private final ImmutableList<String> extensionDirs;
	private final RuntimeEnvironment runtimeEnvironment;
	private final VirtualMachine virtualMachine;

	private Java(Builder builder) {
		this.home = builder.home;
		this.tmpDir = builder.tmpDir;
		this.jitCompiler = builder.jitCompiler;
		this.classVersion = builder.classVersion;
		this.classpath = ImmutableList.copyOf(builder.classpath);
		this.libraryPaths = ImmutableList.copyOf(builder.libraryPaths);
		this.extensionDirs = ImmutableList.copyOf(builder.extensionDirs);
		this.runtimeEnvironment = builder.runtimeEnvironment;
		this.virtualMachine = builder.virtualMachine;
	}

	public static class Builder implements org.apache.commons.lang3.builder.Builder<Java> {

		private File home;
		private File tmpDir;
		private Optional<String> jitCompiler = absent();
		private String classVersion;
		private List<String> classpath = ImmutableList.of();
		private List<String> libraryPaths = ImmutableList.of();
		private List<String> extensionDirs = ImmutableList.of();
		private RuntimeEnvironment runtimeEnvironment;
		private VirtualMachine virtualMachine;

		public Builder home(File home) {
			this.home = home;
			return this;
		}

		public Builder tmpDir(File tmpDir) {
			this.tmpDir = tmpDir;
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

		public Builder classpath(List<String> classpath) {
			this.classpath = classpath;
			return this;
		}

		public Builder libraryPaths(List<String> libraryPaths) {
			this.libraryPaths = libraryPaths;
			return this;
		}

		public Builder extensionDirs(List<String> extensionDirs) {
			this.extensionDirs = extensionDirs;
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
		public Java build() {
			return checkConstraints(new Java(this));
		}

		public File getHome() {
			return home;
		}

		public void setHome(File home) {
			this.home = home;
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

		public List<String> getClasspath() {
			return classpath;
		}

		public void setClasspath(List<String> classpath) {
			this.classpath = classpath;
		}

		public List<String> getLibraryPaths() {
			return libraryPaths;
		}

		public void setLibraryPaths(List<String> libraryPaths) {
			this.libraryPaths = libraryPaths;
		}

		public List<String> getExtensionDirectories() {
			return extensionDirs;
		}

		public void setExtensionDirectories(List<String> extensionDirectories) {
			this.extensionDirs = extensionDirectories;
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

		public File getTempDir() {
			return tmpDir;
		}

		public void setTempDir(File tempDir) {
			this.tmpDir = tempDir;
		}

	}

	public File getHome() {
		return home;
	}

	public Optional<String> getJitCompiler() {
		return jitCompiler;
	}

	public String getClassVersion() {
		return classVersion;
	}

	public List<String> getClasspath() {
		return classpath;
	}

	public List<String> getLibraryPaths() {
		return libraryPaths;
	}

	public List<String> getExtensionDirs() {
		return extensionDirs;
	}

	public RuntimeEnvironment getRuntimeEnvironment() {
		return runtimeEnvironment;
	}

	public VirtualMachine getVirtualMachine() {
		return virtualMachine;
	}

	public File getTmpDir() {
		return tmpDir;
	}

}
