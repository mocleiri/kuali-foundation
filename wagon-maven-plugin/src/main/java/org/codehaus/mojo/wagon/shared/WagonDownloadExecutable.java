package org.codehaus.mojo.wagon.shared;

import static java.lang.String.format;
import static org.apache.commons.io.FileUtils.touch;
import static org.apache.commons.lang3.StringUtils.leftPad;
import static org.kuali.common.util.base.Exceptions.illegalState;
import static org.kuali.common.util.log.Loggers.newLogger;

import java.io.File;

import org.apache.maven.wagon.Wagon;
import org.kuali.common.core.build.ValidatingBuilder;
import org.kuali.common.core.validate.annotation.IdiotProofImmutable;
import org.kuali.common.util.Counter;
import org.kuali.common.util.execute.Executable;
import org.slf4j.Logger;

@IdiotProofImmutable
public final class WagonDownloadExecutable implements Executable {

	private static final Logger logger = newLogger();

	private final String remoteFile;
	private final File destination;
	private final Wagon wagon;
	private final Counter counter;

	@Override
	public void execute() {
		try {
			touch(destination);
			wagon.get(remoteFile, destination);
			counter.increment();
			logger.info(format("%s - %s", leftPad(counter.increment() + "", 5, " "), remoteFile));
		} catch (Exception e) {
			throw illegalState(e);
		}
	}

	private WagonDownloadExecutable(Builder builder) {
		this.remoteFile = builder.remoteFile;
		this.destination = builder.destination;
		this.wagon = builder.wagon;
		this.counter = builder.counter;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder<WagonDownloadExecutable> {

		private String remoteFile;
		private File destination;
		private Wagon wagon;
		private Counter counter;

		public Builder withCounter(Counter counter) {
			this.counter = counter;
			return this;
		}

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

	public String getRemoteFile() {
		return remoteFile;
	}

	public File getDestination() {
		return destination;
	}

	public Wagon getWagon() {
		return wagon;
	}

	public Counter getCounter() {
		return counter;
	}

}
