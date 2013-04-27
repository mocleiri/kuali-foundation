package org.kuali.common.deploy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.codehaus.plexus.util.StringUtils;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.LoggerLevel;
import org.kuali.common.util.LoggerUtils;
import org.kuali.common.util.UnixCmds;
import org.kuali.common.util.UnixProcess;
import org.kuali.common.util.secure.Result;
import org.kuali.common.util.secure.SecureChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

public class DeployUtils {

	private static final Logger logger = LoggerFactory.getLogger(DeployUtils.class);

	private static final String CMD = "CMD";
	private static final UnixCmds CMDS = new UnixCmds();

	/**
	 * Return a list of any processes where the command exactly matches the command passed in.
	 */
	public static List<UnixProcess> getMatchingProcesses(List<UnixProcess> processes, String command) {
		List<UnixProcess> matches = new ArrayList<UnixProcess>();
		for (UnixProcess process : processes) {
			if (StringUtils.equals(process.getCommand(), command)) {
				matches.add(process);
			}
		}
		return matches;
	}

	public static List<UnixProcess> getUnixProcesses(Result result) {
		// Convert stdout to a list of strings
		List<String> lines = DeployUtils.getOutputLines(result);

		// Something has gone wrong
		if (CollectionUtils.isEmpty(lines)) {
			throw new IllegalStateException("There should be a header line");
		}

		// If there are no processes running, exit value is 1
		if (lines.size() == 1 && result.getExitValue() == 1) {
			// return an empty list
			return Collections.emptyList();
		}

		// Make sure exit value was zero
		DeployUtils.validateResult(result);

		// Need the header line in order to parse the process lines
		String header = lines.get(0);

		// Setup some storage for the list of running processes
		List<UnixProcess> processes = new ArrayList<UnixProcess>();

		// Convert each line into a UnixProcess pojo
		for (int i = 1; i < lines.size(); i++) {

			// Extract a line
			String line = lines.get(i);

			// Convert to a pojo
			UnixProcess process = getUnixProcess(header, line);

			// Add to the list
			processes.add(process);
		}

		// return what we've found
		return processes;
	}

	/**
	 * Output looks like this:
	 * 
	 * <pre>
	 *   UID        PID  PPID  C STIME TTY          TIME CMD
	 * 	 tomcat   15461 15460  0 22:51 pts/0    00:00:00 -bash
	 * 	 tomcat   15480 15461  0 22:52 pts/0    00:00:02 java -jar /usr/local/machine-agent/machineagent.jar
	 * </pre>
	 */
	public static UnixProcess getUnixProcess(String header, String line) {
		// Split the strings up into tokens
		String[] tokens = StringUtils.split(line, " ");
		// First token is the user id
		String userId = StringUtils.trim(tokens[0]);
		// Second token is the process id
		String processId = StringUtils.trim(tokens[1]);
		// The command starts where "CMD" starts in the header line
		int pos = header.indexOf(CMD);
		if (pos == -1) {
			throw new IllegalStateException("[" + line + "] does not contain [" + CMD + "]");
		}
		String command = StringUtils.trim(StringUtils.substring(line, pos));

		//
		UnixProcess process = new UnixProcess();
		process.setUserId(userId);
		process.setProcessId(Integer.parseInt(processId));
		process.setCommand(command);
		return process;
	}

	public static void executeCommand(SecureChannel channel, String command, boolean validateResult) {
		Result result = channel.executeCommand(command);
		DeployUtils.logResult(result, logger);
		if (validateResult) {
			DeployUtils.validateResult(result);
		}
	}

	public static void kill(SecureChannel channel, UnixProcess process) {
		String command = CMDS.kill(process.getProcessId());
		Result result = channel.executeCommand(command);
		DeployUtils.logResult(result, logger, LoggerLevel.DEBUG);
		DeployUtils.validateResult(result);
	}

	public static List<UnixProcess> getUnixProcesses(SecureChannel channel, String user) {
		String command = CMDS.psf(user);
		Result result = channel.executeCommand(command);
		return getUnixProcesses(result);

	}

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
