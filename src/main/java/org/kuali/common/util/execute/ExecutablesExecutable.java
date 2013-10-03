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
package org.kuali.common.util.execute;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.FormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Execute the list of <code>executables</code> supplied to this bean
 * 
 * @deprecated Use the ExecutablesExecutable from the .impl package instead
 */
@Deprecated
public class ExecutablesExecutable implements Executable {

	private static final Logger logger = LoggerFactory.getLogger(ExecutablesExecutable.class);

	List<? extends Executable> executables;
	boolean skip;
	boolean timed;

	public ExecutablesExecutable() {
		this((List<? extends Executable>) null);
	}

	public ExecutablesExecutable(Executable... executables) {
		this(Arrays.asList(executables));
	}

	public ExecutablesExecutable(List<? extends Executable> executables) {
		this(executables, false);
	}

	public ExecutablesExecutable(List<? extends Executable> executables, boolean skip) {
		this(executables, skip, false);
	}

	public ExecutablesExecutable(List<? extends Executable> executables, boolean skip, boolean timed) {
		super();
		this.executables = executables;
		this.skip = skip;
		this.timed = timed;
	}

	@Override
	public void execute() {
		if (skip) {
			logger.info("Skipping execution of {} executables", CollectionUtils.toEmptyList(executables).size());
			return;
		}
		long start = System.currentTimeMillis();
		for (Executable executable : executables) {
			executable.execute();
		}
		if (timed) {
			long stop = System.currentTimeMillis();
			logger.info("------------------------------------------------------------------------");
			logger.info("Total Time: {}", FormatUtils.getTime(stop - start));
			logger.info("Finished at: {}", new Date(stop));
			logger.info("------------------------------------------------------------------------");
		}
	}

	public List<? extends Executable> getExecutables() {
		return executables;
	}

	public void setExecutables(List<? extends Executable> executables) {
		this.executables = executables;
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

	public boolean isTimed() {
		return timed;
	}

	public void setTimed(boolean timed) {
		this.timed = timed;
	}

}
