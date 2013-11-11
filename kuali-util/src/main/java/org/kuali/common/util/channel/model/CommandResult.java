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

import com.google.common.base.Optional;

public final class CommandResult {

	public CommandResult(String command, int exitValue, Optional<String> stdin, Optional<String> stdout, Optional<String> stderr, String encoding, long start) {
		this.stop = System.currentTimeMillis();
		Assert.isTrue(stop >= start);
		Assert.noBlanks(command, encoding);
		Assert.noNegatives(start);
		Assert.noNulls(stdin, stdout, stderr);
		this.command = command;
		this.exitValue = exitValue;
		this.stdin = stdin;
		this.stdout = stdout;
		this.stderr = stderr;
		this.encoding = encoding;
		this.start = start;
		this.elapsed = stop - start;
	}

	private final String command;
	private final int exitValue;
	private final Optional<String> stdin;
	private final Optional<String> stdout;
	private final Optional<String> stderr;
	private final String encoding;
	private final long start;
	private final long stop;
	private final long elapsed;

	public String getCommand() {
		return command;
	}

	public int getExitValue() {
		return exitValue;
	}

	public Optional<String> getStdin() {
		return stdin;
	}

	public Optional<String> getStdout() {
		return stdout;
	}

	public Optional<String> getStderr() {
		return stderr;
	}

	public String getEncoding() {
		return encoding;
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
