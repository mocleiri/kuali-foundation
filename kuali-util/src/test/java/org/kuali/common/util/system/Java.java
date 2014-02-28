package org.kuali.common.util.system;

import java.io.File;
import java.util.List;

import org.kuali.common.util.build.SimpleValidatingBuilder;
import org.kuali.common.util.validate.IdiotProofImmutable;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.collect.ImmutableList;

@IdiotProofImmutable
@JsonDeserialize(builder = Java.Builder.class)
public final class Java {

	private final File home;
	private final File tmpDir;
	private final String classVersion;
	private final ImmutableList<String> classpath;
	private final ImmutableList<String> libraryPaths;
	private final ImmutableList<String> extensionDirs;
	private final RuntimeEnvironment runtime;
	private final VirtualMachine vm;

	private Java(Builder builder) {
		this.home = builder.home;
		this.tmpDir = builder.tmpDir;
		this.classVersion = builder.classVersion;
		this.classpath = ImmutableList.copyOf(builder.classpath);
		this.libraryPaths = ImmutableList.copyOf(builder.libraryPaths);
		this.extensionDirs = ImmutableList.copyOf(builder.extensionDirs);
		this.runtime = builder.runtime;
		this.vm = builder.vm;
	}

	public static class Builder extends SimpleValidatingBuilder<Java> {

		private File home;
		private File tmpDir;
		private String classVersion;
		private List<String> classpath;
		private List<String> libraryPaths;
		private List<String> extensionDirs;
		private RuntimeEnvironment runtime;
		private VirtualMachine vm;

		public Builder withHome(File home) {
			this.home = home;
			return this;
		}

		public Builder withTmpDir(File tmpDir) {
			this.tmpDir = tmpDir;
			return this;
		}

		public Builder withClassVersion(String classVersion) {
			this.classVersion = classVersion;
			return this;
		}

		public Builder withClasspath(ImmutableList<String> classpath) {
			this.classpath = classpath;
			return this;
		}

		public Builder withLibraryPaths(ImmutableList<String> libraryPaths) {
			this.libraryPaths = libraryPaths;
			return this;
		}

		public Builder withExtensionDirs(ImmutableList<String> extensionDirs) {
			this.extensionDirs = extensionDirs;
			return this;
		}

		public Builder withRuntime(RuntimeEnvironment runtime) {
			this.runtime = runtime;
			return this;
		}

		public Builder withVm(VirtualMachine vm) {
			this.vm = vm;
			return this;
		}

		@Override
		public Java build() {
			return validate(new Java(this));
		}
	}

	public File getHome() {
		return home;
	}

	public File getTmpDir() {
		return tmpDir;
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

	public RuntimeEnvironment getRuntime() {
		return runtime;
	}

	public VirtualMachine getVm() {
		return vm;
	}

}
