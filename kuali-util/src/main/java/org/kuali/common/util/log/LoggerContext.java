/**
 * Copyright 2010-2013 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you cannot use this file except in compliance with the License.
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
package org.kuali.common.util.log;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

import com.google.common.collect.ImmutableList;

public final class LoggerContext {

	private final LoggerLevel level;
	private final String msg;
	private final ImmutableList<Object> args;
	private final Logger logger;

	private LoggerContext(Builder builder) {
		this.logger = builder.logger;
		this.level = builder.level;
		this.msg = builder.msg;
		this.args = ImmutableList.copyOf(builder.args);
	}

	public static Builder builder(Logger logger, String msg) {
		return new Builder(logger, msg);
	}

	public static class Builder {

		public Builder(Logger logger, String msg) {
			this.logger = logger;
			this.msg = msg;
		}

		// Required
		private final String msg;
		private Logger logger;

		// Optional
		private LoggerLevel level = LoggerLevel.INFO;
		private List<Object> args = ImmutableList.of();

		public Builder level(LoggerLevel level) {
			this.level = level;
			return this;
		}

		public Builder args(List<Object> args) {
			this.args = args;
			return this;
		}

		public LoggerContext build() {
			LoggerContext instance = new LoggerContext(this);
			validate(instance);
			return instance;
		}

		private static void validate(LoggerContext instance) {
			checkNotNull(instance.logger, "logger cannot be null");
			checkNotNull(instance.level, "level cannot be null");
			checkArgument(!StringUtils.isBlank(instance.msg), "msg cannot be blank");
			checkNotNull(instance.args, "args cannot be null");
		}
	}

	public LoggerLevel getLevel() {
		return level;
	}

	public String getMsg() {
		return msg;
	}

	public ImmutableList<Object> getArgs() {
		return args;
	}

	public Logger getLogger() {
		return logger;
	}

}
