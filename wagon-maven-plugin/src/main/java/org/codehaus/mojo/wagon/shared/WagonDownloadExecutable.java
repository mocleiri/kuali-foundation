package org.codehaus.mojo.wagon.shared;

import static org.apache.commons.io.FileUtils.touch;
import static org.kuali.common.util.base.Exceptions.illegalState;

import java.io.File;

import org.apache.maven.wagon.Wagon;
import org.kuali.common.core.build.ValidatingBuilder;
import org.kuali.common.core.validate.annotation.IdiotProofImmutable;
import org.kuali.common.util.execute.Executable;

@IdiotProofImmutable
public final class WagonDownloadExecutable implements Executable {

	private final String remoteFile;
	private final File destination;
	private final Wagon wagon;

	@Override
	public void execute() {
		try {
			touch(destination);
			wagon.get(remoteFile, destination);
		} catch (Exception e) {
			throw illegalState(e);
		}
	}

	private WagonDownloadExecutable(Builder builder) {
		this.remoteFile = builder.remoteFile;
		this.destination = builder.destination;
		this.wagon = builder.wagon;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder<WagonDownloadExecutable> {

		private String remoteFile;
		private File destination;
		private Wagon wagon;

		public Builder withRemoteFile(String remoteFile) {
			this.remoteFile = remoteFile;
			return this;
		}

		public Builder withDestination(File destination) {
			this.destination = destination;
			return this;
		}

		public Builder withWagon(Wagon wagon) {
			this.wagon = wagon;
			return this;
		}

		@Override
		public WagonDownloadExecutable build() {
			return validate(new WagonDownloadExecutable(this));
		}
	}

}
