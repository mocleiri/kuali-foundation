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

import java.util.Date;
import java.util.List;

import org.kuali.common.util.Assert;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.execute.Executable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableList;

/**
 * Execute the list of <code>executables</code> supplied to this bean
 */
public class ConcurrentExecutables implements Executable {

	private static final Logger logger = LoggerFactory.getLogger(ConcurrentExecutables.class);

	private final List<Executable> executables;
	private final boolean skip;
	private final boolean timed;

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
