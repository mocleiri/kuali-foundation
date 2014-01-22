package org.kuali.common.util.system;

import java.io.File;
import java.util.List;

import org.kuali.common.util.build.AwesomeBuilder;
import org.kuali.common.util.validate.IdiotProofImmutable;

import com.google.common.base.Optional;

@IdiotProofImmutable
public final class Java {

	private final File home;
	private final File temporaryDirectory;
	private final Optional<String> jitCompiler;
	private final String classVersion;
	private final List<String> classPaths;
	private final List<String> libraryPaths;
	private final List<String> extensionDirectories;
	private final RuntimeEnvironment runtimeEnvironment;
	private final VirtualMachine virtualMachine;
	private final String lineSeparator;
	private final String pathSeparator;
	private final String fileSeparator;

	private Java(Builder builder) {
		this.home = builder.home;
		this.temporaryDirectory = builder.temporaryDirectory;
		this.jitCompiler = builder.jitCompiler;
		this.classVersion = builder.classVersion;
		this.classPaths = builder.classPaths;
		this.libraryPaths = builder.libraryPaths;
		this.extensionDirectories = builder.extensionDirectories;
		this.runtimeEnvironment = builder.runtimeEnvironment;
		this.virtualMachine = builder.virtualMachine;
		this.lineSeparator = builder.lineSeparator;
		this.pathSeparator = builder.pathSeparator;
		this.fileSeparator = builder.fileSeparator;
	}

	public static class Builder extends AwesomeBuilder<Java> {

		private File home;
		private File temporaryDirectory;
		private Optional<String> jitCompiler;
		private String classVersion;
		private List<String> classPaths;
		private List<String> libraryPaths;
		private List<String> extensionDirectories;
		private RuntimeEnvironment runtimeEnvironment;
		private VirtualMachine virtualMachine;
		private String lineSeparator;
		private String pathSeparator;
		private String fileSeparator;

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

		public Builder classPaths(List<String> classPaths) {
			this.classPaths = classPaths;
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

		public Builder lineSeparator(String lineSeparator) {
			this.lineSeparator = lineSeparator;
			return this;
		}

		public Builder pathSeparator(String pathSeparator) {
			this.pathSeparator = pathSeparator;
			return this;
		}

		public Builder fileSeparator(String fileSeparator) {
			this.fileSeparator = fileSeparator;
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

		public List<String> getClassPaths() {
			return classPaths;
		}

		public void setClassPaths(List<String> classPaths) {
			this.classPaths = classPaths;
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

		public String getLineSeparator() {
			return lineSeparator;
		}

		public void setLineSeparator(String lineSeparator) {
			this.lineSeparator = lineSeparator;
		}

		public String getPathSeparator() {
			return pathSeparator;
		}

		public void setPathSeparator(String pathSeparator) {
			this.pathSeparator = pathSeparator;
		}

		public String getFileSeparator() {
			return fileSeparator;
		}

		public void setFileSeparator(String fileSeparator) {
			this.fileSeparator = fileSeparator;
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

	public List<String> getClassPaths() {
		return classPaths;
	}

	public List<String> getLibraryPaths() {
		return libraryPaths;
	}

	public List<String> getExtensionDirectories() {
		return extensionDirectories;
	}

	public RuntimeEnvironment getRuntimeEnvironment() {
		return runtimeEnvironment;
	}

	public VirtualMachine getVirtualMachine() {
		return virtualMachine;
	}

	public String getLineSeparator() {
		return lineSeparator;
	}

	public String getPathSeparator() {
		return pathSeparator;
	}

	public String getFileSeparator() {
		return fileSeparator;
	}

}
