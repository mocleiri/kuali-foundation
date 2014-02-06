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
package org.kuali.common.util.channel.model;

import org.kuali.common.util.Assert;

public final class CommandResult {

	public CommandResult(byte[] command, int exitValue, long start) {
		this.stop = System.currentTimeMillis();
		Assert.isTrue(stop >= start);
		Assert.noNulls(command);
		Assert.noNegatives(start);
		this.command = command;
		this.exitValue = exitValue;
		this.start = start;
		this.elapsed = stop - start;
	}

	private final byte[] command;
	private final int exitValue;
	private final long start;
	private final long stop;
	private final long elapsed;

	public byte[] getCommand() {
		return command;
	}

	public int getExitValue() {
		return exitValue;
	}

	public long getStart() {
		return start;
	}

	public long getStop() {
		return stop;
	}

	public long getElapsed() {
		return elapsed;
	}

}
