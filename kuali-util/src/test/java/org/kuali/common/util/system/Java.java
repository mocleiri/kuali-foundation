package org.kuali.common.util.system;

import java.io.File;
import java.util.List;

import org.kuali.common.util.build.SimpleValidatingBuilder;
import org.kuali.common.util.validate.IdiotProofImmutable;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.collect.ImmutableList;

@IdiotProofImmutable
@JsonDeserialize(builder = Java.Builder.class)
@JsonPropertyOrder(alphabetic = true)
public final class Java {

	private final File home;
	private final File tmpDir;
	private final String classVersion;
	private final ImmutableList<File> classpath;
	private final ImmutableList<File> libraryPaths;
	private final ImmutableList<File> extensionDirs;
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

		@JsonDeserialize(using = PathDeserializer.class)
		private List<File> classpath = ImmutableList.of();

		@JsonDeserialize(using = PathDeserializer.class)
		private List<File> libraryPaths = ImmutableList.of();

		@JsonDeserialize(using = PathDeserializer.class)
		private List<File> extensionDirs = ImmutableList.of();

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

		public Builder withClasspath(List<File> classpath) {
			this.classpath = classpath;
			return this;
		}

		public Builder withLibraryPaths(List<File> libraryPaths) {
			this.libraryPaths = libraryPaths;
			return this;
		}

		public Builder withExtensionDirs(List<File> extensionDirs) {
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

	public List<File> getClasspath() {
		return classpath;
	}

	public List<File> getLibraryPaths() {
		return libraryPaths;
	}

	public List<File> getExtensionDirs() {
		return extensionDirs;
	}

	public RuntimeEnvironment getRuntime() {
		return runtime;
	}

	public VirtualMachine getVm() {
		return vm;
	}

}
