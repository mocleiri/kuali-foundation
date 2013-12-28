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
package org.kuali.common.util.runonce.smart;

import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.log.LoggerUtils;
import org.slf4j.Logger;

import com.google.common.base.Preconditions;

public class RunOnceExecutable implements Executable {

	private static final Logger logger = LoggerUtils.make();

	private final Executable executable;
	private final RunOnce runOnce;
	private final boolean skip;

	@Override
	public void execute() {

		// Skip has been explicitly configured
		if (skip) {
			logger.info("Skipping RunOnce");
			return;
		}

		// Get the indicator telling us if it's safe to run
		RunOnceIndicator indicator = runOnce.getIndicator();

		// Log what we are doing and why
		logger.info("RunOnce={} - {}", indicator.isRunOnce(), indicator.getReason());

		// If run once is not enabled, we are done
		if (!indicator.isRunOnce()) {
			return;
		}

		// Transition to INPROGRESS
		runOnce.changeState(RunOnceState.INPROGRESS);

		try {
			// Now that we have transitioned things to INPROGRESS it is safe to fire the executable
			// The transition to INPROGRESS is what prevents us from executing the executable more than once
			executable.execute();

			// Transition to COMPLETED
			runOnce.changeState(RunOnceState.COMPLETED);
		} catch (Exception e) {
			// Transition to FAILED
			runOnce.changeState(RunOnceState.FAILED);
			throw new IllegalStateException("Unexpected execution error", e);
		}
	}

	public static Builder builder(Executable executable, RunOnce runOnce) {
		return new Builder(executable, runOnce);
	}

	public static class Builder {

		// Required
		private final Executable executable;
		private final RunOnce runOnce;

		// Optional
		private boolean skip = false;

		public Builder(Executable executable, RunOnce runOnce) {
			this.executable = executable;
			this.runOnce = runOnce;
		}

		public Builder skip(boolean skip) {
			this.skip = skip;
			return this;
		}

		public RunOnceExecutable build() {
			RunOnceExecutable instance = new RunOnceExecutable(this);
			validate(instance);
			return instance;
		}

		private void validate(RunOnceExecutable instance) {
			Preconditions.checkNotNull(executable, "executable cannot be null");
			Preconditions.checkNotNull(runOnce, "runOnce cannot be null");
		}
	}

	private RunOnceExecutable(Builder builder) {
		this.executable = builder.executable;
		this.runOnce = builder.runOnce;
		this.skip = builder.skip;
	}

	public Executable getExecutable() {
		return executable;
	}

	public boolean isSkip() {
		return skip;
	}

}
