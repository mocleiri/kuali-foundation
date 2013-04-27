package org.kuali.common.deploy;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.LoggerLevel;
import org.kuali.common.util.LoggerUtils;
import org.kuali.common.util.secure.Result;
import org.kuali.common.util.secure.SecureChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

public class DeployUtils {

	private static final Logger logger = LoggerFactory.getLogger(DeployUtils.class);

	public static void executePathCommand(SecureChannel channel, String command, String path) {
		executePathCommand(channel, command, Collections.singletonList(path));
	}

	public static void executePathCommand(SecureChannel channel, String command, List<String> paths) {
		executePathCommand(channel, command, paths, LoggerLevel.INFO);
	}

	public static void executePathCommand(SecureChannel channel, String command, List<String> paths, LoggerLevel level) {
		if (CollectionUtils.isEmpty(paths)) {
			return;
		}
		Result result = channel.executeCommand(command);
		logResult(result, logger, level);
		validateResult(result);
	}

	public static List<String> getOutputLines(Result result) {
		try {
			return IOUtils.readLines(LocationUtils.getBufferedReaderFromString(result.getStdout()));
		} catch (IOException e) {
			throw new IllegalArgumentException("Unexpected IO error", e);
		}
	}

	public static void logResult(Result result, Logger logger, LoggerLevel level) {
		LoggerUtils.logLines("[" + result.getCommand() + "] - " + FormatUtils.getTime(result.getElapsed()), logger, level);
		LoggerUtils.logLines(result.getStdout(), logger, level);
		LoggerUtils.logLines(result.getStderr(), logger, LoggerLevel.WARN);
		if (result.getExitValue() != 0) {
			logger.warn("Exit value = {}", result.getExitValue());
		}
	}

	public static void logResult(Result result, Logger logger) {
		logResult(result, logger, LoggerLevel.INFO);
	}

	public static void validateResult(Result result) {
		validateResult(result, Arrays.asList(0));
		logger.trace("Result is valid");
	}

	public static void validateResult(Result result, List<Integer> exitValues) {
		for (Integer exitValue : exitValues) {
			if (exitValue.equals(result.getExitValue())) {
				return;
			}
		}
		throw new IllegalStateException("Exit value " + result.getExitValue() + " is not allowed");
	}

}
