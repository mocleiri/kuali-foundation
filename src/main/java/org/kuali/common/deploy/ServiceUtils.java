package org.kuali.common.deploy;

import java.util.Arrays;
import java.util.List;

import org.kuali.common.util.LoggerLevel;
import org.kuali.common.util.LoggerUtils;
import org.kuali.common.util.SimpleFormatter;
import org.kuali.common.util.secure.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServiceUtils {

	private static final Logger logger = LoggerFactory.getLogger(ServiceUtils.class);
	private static final SimpleFormatter SF = new SimpleFormatter();

	public static void logResult(Result result, Logger logger) {
		logger.info("[{}] - {}", result.getCommand(), SF.getTime(result.getElapsed()));
		LoggerUtils.logLines(result.getStdout(), logger, LoggerLevel.INFO);
		LoggerUtils.logLines(result.getStderr(), logger, LoggerLevel.WARN);
		if (result.getExitValue() != 0) {
			logger.warn("Exit value = {}", result.getExitValue());
		}
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
