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

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import org.codehaus.plexus.util.cli.StreamConsumer;
import org.kuali.common.util.Assert;
import org.kuali.common.util.Str;
import org.kuali.common.util.stream.NoOpStreamConsumer;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

public final class CommandContext {

	private final byte[] command;
	private final Optional<InputStream> stdin;
	private final Optional<Integer> timeout;
	private final StreamConsumer stdout;
	private final StreamConsumer stderr;
	private final List<Integer> successCodes;
	private final boolean ignoreExitValue;

	public static class Builder {

		private static final int SUCCESS = 0;
		private static final String UTF8 = "UTF-8";

		// Required
		private final byte[] command;

		// Optional
		private Optional<InputStream> stdin = Optional.absent(); // Don't supply anything to stdin by default
		private Optional<Integer> timeout = Optional.absent(); // Default is to wait forever
		private StreamConsumer stdout = NoOpStreamConsumer.INSTANCE; // Ignore anything produced on stdout, by default
		private StreamConsumer stderr = NoOpStreamConsumer.INSTANCE; // Ignore anything produced on stderr, by default
		private List<Integer> successCodes = ImmutableList.of(SUCCESS); // Expect zero as an exit value
		private boolean ignoreExitValue = false; // Set this to true if you want to ignore the exit value of the process

		/**
		 * Pass this command to a remote server as a sequence of bytes encoded using UTF-8
		 */
		public Builder(String command) {
			this(command, UTF8);
		}

		/**
		 * Pass this command to a remote server as a sequence of bytes encoded using the indicated encoding
		 */
		public Builder(String command, String encoding) {
			this(Str.getBytes(command, encoding));
		}

		public Builder(byte[] command) {
			this.command = command;
		}

		/**
		 * Convert the string into a <code>byte[]</code> using UTF-8 encoding
		 */
		public Builder stdin(String stdin) {
			return stdin(stdin, UTF8);
		}

		/**
		 * Convert the string into a <code>byte[]</code> using the indicated encoding
		 */
		public Builder stdin(String stdin, String encoding) {
			Assert.noBlanks(stdin, encoding);
			byte[] bytes = Str.getBytes(stdin, encoding);
			return stdin(new ByteArrayInputStream(bytes));
		}

		public Builder stdin(InputStream stdin) {
			this.stdin = Optional.of(stdin);
			return this;
		}

		public Builder timeout(int timeout) {
			this.timeout = Optional.of(timeout);
			return this;
		}

		public Builder stdout(StreamConsumer stdout) {
			this.stdout = stdout;
			return this;
		}

		public Builder stderr(StreamConsumer stderr) {
			this.stderr = stderr;
			return this;
		}

		public Builder ignoreExitValue(boolean ignoreExitValue) {
			this.ignoreExitValue = ignoreExitValue;
			return this;
		}

		public Builder successCodes(List<Integer> successCodes) {
			this.successCodes = successCodes;
			return this;
		}

		public CommandContext build() {
			Assert.noNulls(command, stdin, timeout, stdout, stderr, successCodes);
			if (timeout.isPresent()) {
				Assert.notNegative(timeout.get());
			}
			this.successCodes = ImmutableList.copyOf(successCodes);
			return new CommandContext(this);
		}

	}

	private CommandContext(Builder builder) {
		this.command = builder.command;
		this.stdin = builder.stdin;
		this.timeout = builder.timeout;
		this.stderr = builder.stderr;
		this.stdout = builder.stdout;
		this.successCodes = builder.successCodes;
		this.ignoreExitValue = builder.ignoreExitValue;

	}

	public byte[] getCommand() {
		return command;
	}

	public Optional<InputStream> getStdin() {
		return stdin;
	}

	public Optional<Integer> getTimeout() {
		return timeout;
	}

	public StreamConsumer getStdout() {
		return stdout;
	}

	public StreamConsumer getStderr() {
		return stderr;
	}

	public List<Integer> getSuccessCodes() {
		return successCodes;
	}

	public boolean isIgnoreExitValue() {
		return ignoreExitValue;
	}

}
