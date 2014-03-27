/**
 * Copyright 2008-2014 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.codehaus.mojo.wagon.shared;

import static java.lang.String.format;
import static java.lang.System.currentTimeMillis;
import static org.apache.commons.io.FileUtils.touch;
import static org.apache.commons.lang3.StringUtils.leftPad;
import static org.kuali.common.util.FormatUtils.getCount;
import static org.kuali.common.util.FormatUtils.getRate;
import static org.kuali.common.util.FormatUtils.getSize;
import static org.kuali.common.util.FormatUtils.getTime;
import static org.kuali.common.util.base.Exceptions.illegalState;
import static org.kuali.common.util.log.Loggers.newLogger;

import java.io.File;
import java.text.NumberFormat;

import javax.validation.constraints.Min;

import org.apache.maven.wagon.Wagon;
import org.kuali.common.core.build.ValidatingBuilder;
import org.kuali.common.core.validate.annotation.IdiotProofImmutable;
import org.kuali.common.util.Counter;
import org.kuali.common.util.LongCounter;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.inform.Inform;
import org.kuali.common.util.inform.PercentCompleteInformer;
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
	@Min(0)
	private final long start;
	private final NumberFormat numberFormatter;
	private final NumberFormat rateFormatter;
	private final PercentCompleteInformer informer;

	@Override
	public void execute() {
		try {
			touch(destination);
			wagon.get(remoteFile, destination);
			counter.increment();
			if (useInformer()) {
				inform();
			} else {
				stats();
			}
		} catch (Exception e) {
			throw illegalState(e);
		}
	}

	private void inform() {
		synchronized (informer) {
			informer.incrementProgress();
			if (informer.getProgress() % informer.getTotal() == 0) {
				Inform inform = informer.getInform();
				String msg = String.format(" - [%s of %s]%s%s", informer.getProgress(), total, inform.getCompleteToken(), inform.getStartToken());
				inform.getPrintStream().print(msg);
			}
		}
	}

	private boolean useInformer() {
		return "warn".equalsIgnoreCase(System.getProperty("org.slf4j.simpleLogger.log.org.kuali.maven.wagon"));
	}

	private void stats() {
		bytesCounter.increment(destination.length());
		int count = counter.getValue();
		long elapsed = currentTimeMillis() - start;
		String rate = getRate(elapsed, bytesCounter.getValue(), rateFormatter);
		// long millisPerFile = elapsed / count;
		int filesRemaining = total - count;
		// long timeRemaining = millisPerFile * filesRemaining;
		// int percent = new Double((count / (total * 1D)) * 100).intValue();
		String amount = lpad(getSize(bytesCounter.getValue(), numberFormatter), 6);
		Object[] args = { lpad(getCount(count), 6), lpad(getCount(total), 6), lpad(getCount(filesRemaining), 6), ltime(elapsed), lpad(rate, 8), amount };
		logger.info(format("%s of %s - remaining %s [elapsed:%s  rate:%s  downloaded:%s]", args));
	}

	private String ltime(long millis) {
		return leftPad(getTime(millis, numberFormatter), 5, " ");
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
		this.numberFormatter = builder.numberFormatter;
		this.rateFormatter = builder.rateFormatter;
		this.informer = builder.informer;
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
		private NumberFormat numberFormatter = getDefaultNumberFormatter();
		private NumberFormat rateFormatter = getDefaultRateFormatter();
		private PercentCompleteInformer informer;

		public Builder withInformer(PercentCompleteInformer informer) {
			this.informer = informer;
			return this;
		}

		public Builder withRateFormatter(NumberFormat rateFormatter) {
			this.rateFormatter = rateFormatter;
			return this;
		}

		public Builder withNumberFormatter(NumberFormat numberFormatter) {
			this.numberFormatter = numberFormatter;
			return this;
		}

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

		private NumberFormat getDefaultNumberFormatter() {
			NumberFormat nf = NumberFormat.getInstance();
			nf.setMaximumFractionDigits(1);
			nf.setMinimumFractionDigits(1);
			return nf;
		}

		private NumberFormat getDefaultRateFormatter() {
			NumberFormat nf = NumberFormat.getInstance();
			nf.setMaximumFractionDigits(0);
			return nf;
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

	public PercentCompleteInformer getInformer() {
		return informer;
	}

	public LongCounter getBytesCounter() {
		return bytesCounter;
	}

}
