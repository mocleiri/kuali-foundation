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

import org.kuali.common.util.Assert;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.ThreadUtils;
import org.kuali.common.util.execute.Executable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

/**
 * Create a new thread for each executable in the list and run them all concurrently.
 */
public class ConcurrentExecutables implements Executable, UncaughtExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(ConcurrentExecutables.class);

	private final List<Executable> executables;
	private final boolean skip;
	private final boolean timed;

	private Optional<IllegalStateException> uncaughtException;

	public ConcurrentExecutables(Executable... executables) {
		this(ImmutableList.copyOf(executables));
	}

	public ConcurrentExecutables(List<Executable> executables) {
		this(executables, false);
	}

	public ConcurrentExecutables(List<Executable> executables, boolean skip) {
		this(executables, skip, false);
	}

	public ConcurrentExecutables(List<Executable> executables, boolean skip, boolean timed) {
		Assert.noNulls(executables);
		this.executables = ImmutableList.copyOf(executables);
		this.skip = skip;
		this.timed = timed;
	}

	@Override
	public void execute() {
		if (skip) {
			logger.info("Skipping execution of {} executables", executables.size());
			return;
		}
		long start = System.currentTimeMillis();
		List<Thread> threads = new ArrayList<Thread>();
		for (Executable executable : executables) {
			Runnable runnable = new ExecutableRunner(executable);
			Thread thread = new Thread(runnable, "Executable");
			thread.setUncaughtExceptionHandler(this);
			threads.add(thread);
		}
		ThreadUtils.start(threads);
		ThreadUtils.join(threads);
		if (uncaughtException.isPresent()) {
			throw uncaughtException.get();
		}
		if (timed) {
			logger.info("------------------------------------------------------------------------");
			logger.info("Total Time: {} (Wall Clock)", FormatUtils.getTime(System.currentTimeMillis() - start));
			logger.info("------------------------------------------------------------------------");
		}
	}

	@Override
	public synchronized void uncaughtException(Thread thread, Throwable uncaughtException) {
		// Report the first exception we encounter
		// Only matters if there is more than one thread that errors out
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

}
