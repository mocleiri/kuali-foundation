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
package org.kuali.common.util.runonce;

import org.kuali.common.util.Assert;
import org.kuali.common.util.execute.Executable;

public class RunOnceExecutable implements Executable {

	public RunOnceExecutable(Executable executable, RunOnceStateManager stateManager, boolean skip) {
		Assert.noNulls(executable, stateManager);
		this.executable = executable;
		this.stateManager = stateManager;
		this.skip = skip;
	}

	private final Executable executable;
	private final RunOnceStateManager stateManager;
	private final boolean skip;

	@Override
	public void execute() {

		// Skip has been explicitly configured
		if (skip) {
			return;
		}

		// Give the state manager a chance to initialize itself
		stateManager.initialize();

		// Let the state manager tell us if we are in RunOnce mode
		if (!stateManager.isRunOnce()) {
			return;
		}

		// Transition to INPROGRESS
		stateManager.persistState(RunOnceState.INPROGRESS);

		try {
			// Now that the state manager has transitioned things to INPROGRESS it is safe to fire the executable
			// The transition to INPROGRESS is what prevents us from executing the executable more than once
			executable.execute();

			// Transition to COMPLETED
			stateManager.persistState(RunOnceState.COMPLETED);
		} catch (Exception e) {
			// Transition to FAILED
			stateManager.persistState(RunOnceState.FAILED);
			throw new IllegalStateException("Unexpected execution error", e);
		}
	}

	public Executable getExecutable() {
		return executable;
	}

	public RunOnceStateManager getStateManager() {
		return stateManager;
	}

	public boolean isSkip() {
		return skip;
	}
}
