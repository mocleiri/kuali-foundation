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
import org.kuali.common.util.nullify.NullUtils;

import com.google.common.base.Optional;

public final class CommandContext {

	private final String command;
	private final Optional<String> stdin;
	private final Optional<Integer> timeout;

	public static class Builder {

		// Required
		private final String command;

		// Optional
		private Optional<String> stdin = Optional.absent();
		private Optional<Integer> timeout = Optional.absent();

		public Builder(String command) {
			this.command = command;
		}

		public Builder stdin(String stdin) {
			this.stdin = Optional.fromNullable(NullUtils.trimToNull(stdin));
			return this;
		}

		public Builder timeout(int timeout) {
			this.timeout = Optional.of(timeout);
			return this;
		}

		public CommandContext build() {
			Assert.noBlanks(command);
			if (stdin.isPresent()) {
				Assert.noBlanks(stdin.get());
			}
			if (timeout.isPresent()) {
				Assert.noNegatives(timeout.get());
			}
			return new CommandContext(this);
		}

	}

	private CommandContext(Builder builder) {
		this.command = builder.command;
		this.stdin = builder.stdin;
		this.timeout = builder.timeout;
	}

	public String getCommand() {
		return command;
	}

	public Optional<String> getStdin() {
		return stdin;
	}

	public Optional<Integer> getTimeout() {
		return timeout;
	}

}
