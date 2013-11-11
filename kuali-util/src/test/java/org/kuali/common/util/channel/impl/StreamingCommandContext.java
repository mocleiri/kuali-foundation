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
package org.kuali.common.util.channel.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.codehaus.plexus.util.cli.StreamConsumer;
import org.kuali.common.util.Assert;
import org.kuali.common.util.Str;

import com.google.common.base.Optional;

public final class StreamingCommandContext {

	private final String command;
	private final Optional<InputStream> stdin;
	private final Optional<Integer> timeout;
	private final Optional<StreamConsumer> stdout;
	private final Optional<StreamConsumer> stderr;

	public static class Builder {

		// Required
		private final String command;

		// Optional
		private Optional<InputStream> stdin = Optional.absent();
		private Optional<Integer> timeout = Optional.absent();
		private Optional<StreamConsumer> stdout = Optional.absent();
		private Optional<StreamConsumer> stderr = Optional.absent();

		public Builder(String command) {
			this.command = command;
		}

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

		public StreamingCommandContext build() {
			Assert.noBlanks(command);
			Assert.noNulls(stdout, timeout, stdout, stderr);
			return new StreamingCommandContext(this);
		}

	}

	private StreamingCommandContext(Builder builder) {
		this.command = builder.command;
		this.stdin = builder.stdin;
		this.timeout = builder.timeout;
		this.stderr = builder.stderr;
		this.stdout = builder.stdout;
	}

	public String getCommand() {
		return command;
	}

	public Optional<InputStream> getStdin() {
		return stdin;
	}

	public Optional<Integer> getTimeout() {
		return timeout;
	}

	public Optional<StreamConsumer> getStdout() {
		return stdout;
	}

	public Optional<StreamConsumer> getStderr() {
		return stderr;
	}

}
