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
package org.kuali.common.util.channel;

public class Result {

	public Result(String command, int exitValue, String stdin, String stdout, String stderr, String encoding, long start, long stop) {
		this.command = command;
		this.exitValue = exitValue;
		this.stdin = stdin;
		this.stdout = stdout;
		this.stderr = stderr;
		this.encoding = encoding;
		this.start = start;
		this.stop = stop;
		this.elapsed = stop - start;
	}

	private final String command;
	private final int exitValue;
	private final String stdin;
	private final String stdout;
	private final String stderr;
	private final String encoding;
	private final long start;
	private final long stop;
	private final long elapsed;

	public String getEncoding() {
		return encoding;
	}

	public String getCommand() {
		return command;
	}

	public String getStdin() {
		return stdin;
	}

	public int getExitValue() {
		return exitValue;
	}

	public String getStdout() {
		return stdout;
	}

	public String getStderr() {
		return stderr;
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
