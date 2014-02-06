package org.kuali.common.devops.model;

import org.kuali.common.util.build.ValidatingBuilder;
import org.kuali.common.util.validate.IdiotProofImmutable;

@IdiotProofImmutable
public final class EnvironmentBasics {

	private final FileCache manifest;
	private final FileCache heap;
	private final FileCache releaseNotes;
	private final FileCache environment;

	private EnvironmentBasics(Builder builder) {
		this.manifest = builder.manifest;
		this.heap = builder.heap;
		this.releaseNotes = builder.releaseNotes;
		this.environment = builder.environment;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder<EnvironmentBasics> {

		private FileCache manifest;
		private FileCache heap;
		private FileCache releaseNotes;
		private FileCache environment;

		public Builder manifest(FileCache manifest) {
			this.manifest = manifest;
			return this;
		}

		public Builder heap(FileCache heap) {
			this.heap = heap;
			return this;
		}

		public Builder releaseNotes(FileCache releaseNotes) {
			this.releaseNotes = releaseNotes;
			return this;
		}

		public Builder environment(FileCache environment) {
			this.environment = environment;
			return this;
		}

		@Override
		public EnvironmentBasics getInstance() {
			return new EnvironmentBasics(this);
		}

		public FileCache getManifest() {
			return manifest;
		}

		public void setManifest(FileCache manifest) {
			this.manifest = manifest;
		}

		public FileCache getHeap() {
			return heap;
		}

		public void setHeap(FileCache heap) {
			this.heap = heap;
		}

		public FileCache getReleaseNotes() {
			return releaseNotes;
		}

		public void setReleaseNotes(FileCache releaseNotes) {
			this.releaseNotes = releaseNotes;
		}

		public FileCache getEnvironment() {
			return environment;
		}

		public void setEnvironment(FileCache environment) {
			this.environment = environment;
		}

	}

	public FileCache getManifest() {
		return manifest;
	}

	public FileCache getHeap() {
		return heap;
	}

	public FileCache getReleaseNotes() {
		return releaseNotes;
	}

	public FileCache getEnvironment() {
		return environment;
	}

}
