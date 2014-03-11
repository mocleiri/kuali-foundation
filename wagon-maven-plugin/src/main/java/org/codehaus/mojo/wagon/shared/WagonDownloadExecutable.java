package org.codehaus.mojo.wagon.shared;

import static java.lang.String.format;
import static java.lang.System.currentTimeMillis;
import static org.apache.commons.io.FileUtils.touch;
import static org.apache.commons.lang3.StringUtils.leftPad;
import static org.kuali.common.util.FormatUtils.getRate;
import static org.kuali.common.util.FormatUtils.getSize;
import static org.kuali.common.util.FormatUtils.getTime;
import static org.kuali.common.util.base.Exceptions.illegalState;
import static org.kuali.common.util.log.Loggers.newLogger;

import java.io.File;

import javax.validation.constraints.Min;

import org.apache.maven.wagon.Wagon;
import org.kuali.common.core.build.ValidatingBuilder;
import org.kuali.common.core.validate.annotation.IdiotProofImmutable;
import org.kuali.common.util.Counter;
import org.kuali.common.util.LongCounter;
import org.kuali.common.util.execute.Executable;
import org.slf4j.Logger;

@IdiotProofImmutable
public final class WagonDownloadExecutable implements Executable {

	private static final Logger logger = newLogger();

	private final String remoteFile;
	private final File destination;
	private final Wagon wagon;
	private final Counter counter;
	private final LongCounter bytesCounter;
	@Min(0)
	private final int total;
	private final long start;

	@Override
	public void execute() {
		try {
			touch(destination);
			wagon.get(remoteFile, destination);
			stats();
		} catch (Exception e) {
			throw illegalState(e);
		}
	}

	private void stats() {
		bytesCounter.increment(destination.length());
		int count = counter.increment();
		long elapsed = currentTimeMillis() - start;
		String rate = getRate(elapsed, bytesCounter.getValue());
		long millisPerFile = elapsed / count;
		int filesRemaining = total - count;
		long timeRemaining = millisPerFile * filesRemaining;
		// int percent = new Double((count / (total * 1D)) * 100).intValue();
		String amount = lpad(getSize(bytesCounter.getValue()), 6);
		Object[] args = { lpad(count, 5), total, ltime(elapsed), ltime(timeRemaining), lpad(rate, 11), amount };
		logger.info(format("%s of %s [elapsed: %s  remaining: %s rate: %s amount: %s]", args));
	}

	private String ltime(long millis) {
		return leftPad(getTime(millis), 6, " ");
	}

	private String lpad(Object object, int size) {
		return leftPad(object.toString(), size, " ");
	}

	private WagonDownloadExecutable(Builder builder) {
		this.remoteFile = builder.remoteFile;
		this.destination = builder.destination;
		this.wagon = builder.wagon;
		this.counter = builder.counter;
		this.total = builder.total;
		this.start = builder.start;
		this.bytesCounter = builder.bytesCounter;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder<WagonDownloadExecutable> {

		private String remoteFile;
		private File destination;
		private Wagon wagon;
		private Counter counter;
		private int total;
		private long start;
		private LongCounter bytesCounter;

		public Builder withBytesCounter(LongCounter bytesCounter) {
			this.bytesCounter = bytesCounter;
			return this;
		}

		public Builder withStart(long start) {
			this.start = start;
			return this;
		}

		public Builder withTotal(int total) {
			this.total = total;
			return this;
		}

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

	public int getTotal() {
		return total;
	}

	public long getStart() {
		return start;
	}

}
