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
package org.kuali.common.util.service;

import java.util.List;

import org.codehaus.plexus.util.cli.CommandLineException;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.codehaus.plexus.util.cli.Commandline;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.log.LoggerLevel;
import org.kuali.common.util.log.LoggingStreamConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultExecService implements ExecService {

	private static final Logger logger = LoggerFactory.getLogger(DefaultExecService.class);

	@Override
	public int execute(ExecContext context) {
		Commandline cl = getCommandLine(context);
		return execute(context, cl);
	}

	@Override
	public int execute(String executable, List<String> args) {
		DefaultExecContext context = new DefaultExecContext();
		context.setExecutable(executable);
		context.setArgs(args);
		return execute(context);
	}

	protected int executeAndValidate(String executable, List<String> args) {
		int exitValue = execute(executable, args);
		validateExitValue(exitValue);
		return exitValue;
	}

	protected int executeAndValidate(ExecContext context) {
		int exitValue = execute(context);
		validateExitValue(exitValue);
		return exitValue;
	}

	protected int execute(ExecContext context, Commandline cl) {
		try {
			logger.debug("[{}]", cl);
			StreamConsumer stdout = getConsumer(context.getStandardOutConsumer(), logger, LoggerLevel.INFO);
			StreamConsumer stderr = getConsumer(context.getStandardErrConsumer(), logger, LoggerLevel.WARN);
			return CommandLineUtils.executeCommandLine(cl, context.getInput(), stdout, stderr, context.getTimeoutInSeconds());
		} catch (CommandLineException e) {
			throw new IllegalStateException(e);
		}
	}

	protected StreamConsumer getConsumer(StreamConsumer provided, Logger logger, LoggerLevel level) {
		if (provided != null) {
			return provided;
		} else {
			return new LoggingStreamConsumer(logger, level);
		}
	}

	@Deprecated
	protected StreamConsumer getStreamConsumer(StreamConsumer provided, Logger logger, org.kuali.common.util.LoggerLevel level) {
		if (provided != null) {
			return provided;
		} else {
			return new org.kuali.common.util.LoggingStreamConsumer(logger, level);
		}
	}

	protected void validateExitValue(int exitValue) {
		if (exitValue != 0) {
			throw new IllegalStateException("Non-zero exit value - " + exitValue);
		}
	}

	protected Commandline getCommandLine(ExecContext context) {
		Commandline cl = new Commandline();
		cl.setExecutable(context.getExecutable());
		if (context.isAddSystemEnvironment()) {
			try {
				cl.addSystemEnvironment();
			} catch (Exception e) {
				throw new IllegalStateException(e);
			}
		}
		if (context.getArgs() != null) {
			cl.addArguments(CollectionUtils.toStringArray(context.getArgs()));
		}
		if (context.getWorkingDirectory() != null) {
			cl.setWorkingDirectory(context.getWorkingDirectory());
		}
		return cl;
	}

}
