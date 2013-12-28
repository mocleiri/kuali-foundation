/**
 * Copyright 2010-2013 The Kuali Foundation
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
package org.kuali.common.util.execute.impl;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.ArrayList;
import java.util.List;

import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.ThreadUtils;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.log.LoggerUtils;
import org.slf4j.Logger;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;

/**
 * Create a new thread for each executable in the list and run them all concurrently.
 */
public final class ConcurrentExecutables implements Executable, UncaughtExceptionHandler {

	private static final Logger logger = LoggerUtils.make();

	private final List<Executable> executables;
	private final boolean skip;
	private final boolean timed;

	// If any thread throws an exception, this gets filled in
	private Optional<IllegalStateException> uncaughtException = Optional.absent();

	public static class Builder {

		// Required
		private final List<Executable> executables;

		// Optional
		private boolean skip = false;
		private boolean timed = false;

		public Builder(Executable... executables) {
			this(ImmutableList.copyOf(executables));
		}

		public Builder(List<Executable> executables) {
			this.executables = ImmutableList.copyOf(executables);
		}

		public Builder timed(boolean timed) {
			this.timed = timed;
			return this;
		}

		public Builder skip(boolean skip) {
			this.skip = skip;
			return this;
		}

		public ConcurrentExecutables build() {
			ConcurrentExecutables instance = new ConcurrentExecutables(this);
			validate(instance);
			return instance;
		}

		private void validate(ConcurrentExecutables instance) {
			Preconditions.checkNotNull(instance.getExecutables());
		}
	}

	private ConcurrentExecutables(Builder builder) {
		this.executables = builder.executables;
		this.skip = builder.skip;
		this.timed = builder.timed;
	}

	@Override
	public void execute() {
		if (skip) {
			logger.info("Skipping execution of {} executables", executables.size());
			return;
		}
		List<Thread> threads = getThreads(executables);
		long start = System.currentTimeMillis();
		ThreadUtils.start(threads);
		ThreadUtils.join(threads);
		long stop = System.currentTimeMillis();
		if (uncaughtException.isPresent()) {
			throw uncaughtException.get();
		}
		if (timed) {
			logger.info("------------------------------------------------------------------------");
			logger.info("Total Time: {} (Wall Clock)", FormatUtils.getTime(stop - start));
			logger.info("------------------------------------------------------------------------");
		}
	}

	protected List<Thread> getThreads(List<Executable> executables) {
		List<Thread> threads = new ArrayList<Thread>();
		for (Executable executable : executables) {
			Runnable runnable = new ExecutableRunner(executable);
			Thread thread = new Thread(runnable, "Executable");
			thread.setUncaughtExceptionHandler(this);
			threads.add(thread);
		}
		return threads;
	}

	@Override
	public synchronized void uncaughtException(Thread thread, Throwable uncaughtException) {
		// Only report back on the first uncaught exception reported by any thread
		// Any exceptions after the first one get ignored
		if (!this.uncaughtException.isPresent()) {
			String context = "Exception in thread [" + thread.getId() + ":" + thread.getName() + "]";
			this.uncaughtException = Optional.of(new IllegalStateException(context, uncaughtException));
		}
	}

	public List<Executable> getExecutables() {
		return executables;
	}

	public boolean isSkip() {
		return skip;
	}

	public boolean isTimed() {
		return timed;
	}

	public Optional<IllegalStateException> getUncaughtException() {
		return uncaughtException;
	}

}
