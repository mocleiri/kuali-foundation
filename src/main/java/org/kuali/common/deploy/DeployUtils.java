package org.kuali.common.deploy;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.codehaus.plexus.util.StringUtils;
import org.kuali.common.util.Assert;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.MonitorTextFileResult;
import org.kuali.common.util.ThreadUtils;
import org.kuali.common.util.UnixCmds;
import org.kuali.common.util.UnixProcess;
import org.kuali.common.util.log.LoggerLevel;
import org.kuali.common.util.log.LoggerUtils;
import org.kuali.common.util.property.Constants;
import org.kuali.common.util.secure.channel.RemoteFile;
import org.kuali.common.util.secure.channel.Result;
import org.kuali.common.util.secure.channel.SecureChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.PropertyPlaceholderHelper;

public class DeployUtils {

	private static final Logger logger = LoggerFactory.getLogger(DeployUtils.class);

	private static final String CMD = "CMD";
	private static final String TRAVERSE_SYMBOLIC_LINKS = "-L";
	private static final UnixCmds CMDS = new UnixCmds();
	private static final PropertyPlaceholderHelper HELPER = Constants.DEFAULT_PROPERTY_PLACEHOLDER_HELPER;

	/**
	 * Examine the contents of a text file, stopping as soon as it contains <code>token</code>, or <code>timeout</code> is exceeded, whichever comes first.
	 */
	public static MonitorTextFileResult monitorTextFile(SecureChannel channel, String path, String token, int intervalMillis, int timeoutMillis, String encoding) {

		// Make sure we are configured correctly
		Assert.notNull(channel, "channel is null");
		Assert.notNull(path, "path is null");
		Assert.hasText(token, "token has no text");
		Assert.hasText(encoding, "encoding has no text");
		Assert.isTrue(intervalMillis > 0, "interval must be a positive integer");
		Assert.isTrue(timeoutMillis > 0, "timeout must be a positive integer");

		// Setup some member variables to record what happens
		long start = System.currentTimeMillis();
		long stop = start + timeoutMillis;
		boolean exists = false;
		boolean contains = false;
		boolean timeoutExceeded = false;
		long now = -1;
		String content = null;

		// loop until timeout is exceeded or we find the token inside the file
		for (;;) {

			// Always pause (unless this is the first iteration)
			if (now != -1) {
				ThreadUtils.sleep(intervalMillis);
			}

			// Check to make sure we haven't exceeded our timeout limit
			now = System.currentTimeMillis();
			if (now > stop) {
				timeoutExceeded = true;
				break;
			}

			// If the file does not exist yet, there is nothing more we can do
			exists = channel.exists(path);
			if (!exists) {
				continue;
			}

			// The file exists, check to see if the token we are looking for is present in the file
			RemoteFile remoteFile = new RemoteFile.Builder(path).build();
			content = channel.toString(remoteFile);
			contains = StringUtils.contains(content, token);
			if (contains) {
				// We found what we are looking for, we are done
				break;
			}
		}

		// Record how long the overall process took
		long elapsed = now - start;

		// Fill in a pojo detailing what happened
		MonitorTextFileResult mtfr = new MonitorTextFileResult(exists, contains, timeoutExceeded, elapsed);
		mtfr.setAbsolutePath(path);
		mtfr.setContent(content);
		return mtfr;
	}

	public static void killMatchingProcesses(SecureChannel channel, String user, String cmd, String processLabel) {
		List<UnixProcess> processes = getUnixProcesses(channel, user);

		// No existing processes, we are done
		if (processes.size() == 0) {
			logger.info("  no running processes for user [{}]", user);
			return;
		}

		// Figure out if any of the running processes are matches
		List<UnixProcess> matches = getMatchingProcesses(processes, cmd);

		if (CollectionUtils.isEmpty(matches)) {
			// Nothing to do
			logger.info("  no machine agents detected. total running processes - {}", processes.size());
			return;
		} else {
			// Kill any matching processes
			for (UnixProcess match : matches) {
				logger.info("  killing {} - [pid:{}]", processLabel, match.getProcessId());
				kill(channel, match);
			}
		}
	}

	/**
	 * Execute <code>cmd</code> as <code>user</code> using <code>nohup</code> and running it in the background.
	 */
	public static String getNohupBackgroundProcess(String user, String cmd) {
		StringBuilder sb = new StringBuilder();
		sb.append("su");
		sb.append(" - ");
		sb.append(user);
		sb.append(" ");
		sb.append("--command");
		sb.append("=");
		sb.append("'");
		sb.append(CMDS.nohup(cmd));
		sb.append(" ");
		sb.append("&");
		sb.append("'");
		return sb.toString();
	}

	public static void copyFiles(SecureChannel channel, List<Deployable> deployables, Properties filterProperties) {
		if (CollectionUtils.isEmpty(deployables)) {
			return;
		}
		for (Deployable deployable : deployables) {
			RemoteFile destination = new RemoteFile.Builder(deployable.getRemote()).build();
			String location = deployable.getLocal();
			logger.info("  creating -> [{}]", destination.getAbsolutePath());
			if (deployable.isFilter()) {
				long start = System.currentTimeMillis();
				String originalContent = LocationUtils.toString(location);
				String resolvedContent = HELPER.replacePlaceholders(originalContent, filterProperties);
				channel.copyStringToFile(resolvedContent, destination);
				String elapsed = FormatUtils.getTime(System.currentTimeMillis() - start);
				Object[] args = { filterProperties.size(), location, destination.getAbsolutePath(), elapsed };
				logger.debug("Used {} properties to filter [{}] -> [{}] - {}", args);
			} else {
				long start = System.currentTimeMillis();
				channel.copyLocationToFile(location, destination);
				logCopy(location, destination.getAbsolutePath(), System.currentTimeMillis() - start);
			}
			if (deployable.getPermissions().isPresent()) {
				String path = deployable.getRemote();
				String perms = deployable.getPermissions().get();
				String command = CMDS.chmod(perms, path);
				executePathCommand(channel, command, path);
			}
		}
	}

	protected static void logCopy(String src, String dst, long elapsed) {
		String rate = "";
		String size = "";
		if (LocationUtils.isExistingFile(src)) {
			long bytes = new File(src).length();
			rate = FormatUtils.getRate(elapsed, bytes);
			size = FormatUtils.getSize(bytes);
		}
		Object[] args = { dst, size, FormatUtils.getTime(elapsed), rate };
		logger.debug("Source -> [{}]", src);
		logger.debug("  created [{}] - [{} {} {}]", args);
	}

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

	/**
	 * Output looks like this:
	 * 
	 * <pre>
	 *   UID        PID  PPID  C STIME TTY          TIME CMD
	 * 	 tomcat   15461 15460  0 22:51 pts/0    00:00:00 -bash
	 * 	 tomcat   15480 15461  0 22:52 pts/0    00:00:02 java -jar /usr/local/machine-agent/machineagent.jar
	 * </pre>
	 */
	public static List<UnixProcess> getUnixProcesses(Result result) {
		// Convert stdout to a list of strings
		List<String> lines = getOutputLines(result);

		// Make sure there is at least a header line
		Assert.isFalse(CollectionUtils.isEmpty(lines), "There should be a header line");

		// If there are no processes running, exit value is 1
		if (lines.size() == 1 && result.getExitValue() == 1) {
			// return an empty list
			return Collections.emptyList();
		}

		// Make sure exit value was zero
		validateResult(result);

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
		// Make sure we found the string "CMD"
		Assert.isFalse(pos == -1, "[" + line + "] does not contain [" + CMD + "]");
		// This is the command used to launch the process
		String command = StringUtils.trim(StringUtils.substring(line, pos));

		// Store the information we've parsed out into pojo
		UnixProcess process = new UnixProcess();
		process.setUserId(userId);
		process.setProcessId(Integer.parseInt(processId));
		process.setCommand(command);
		return process;
	}

	public static Result executeCommand(SecureChannel channel, String command, boolean validateResult) {
		Result result = channel.executeCommand(command);
		if (validateResult) {
			validateResult(result);
		}
		return result;
	}

	public static void kill(SecureChannel channel, UnixProcess process) {
		String command = CMDS.kill(process.getProcessId());
		Result result = channel.executeCommand(command);
		logResult(result, logger, LoggerLevel.DEBUG);
		validateResult(result);
	}

	public static List<UnixProcess> getUnixProcesses(SecureChannel channel, String user) {
		String command = CMDS.psf(user);
		Result result = channel.executeCommand(command);
		return getUnixProcesses(result);

	}

	public static Result runscript(SecureChannel channel, String username, String script) {
		return executeCommand(channel, CMDS.su(username, script), true);
	}

	public static Result runscript(SecureChannel channel, String username, String script, boolean validateExitValue) {
		return executeCommand(channel, CMDS.su(username, script), validateExitValue);
	}

	public static Result delete(SecureChannel channel, List<String> paths) {
		return executePathCommand(channel, CMDS.rmrf(paths), paths);
	}

	public static Result mkdirs(SecureChannel channel, List<String> paths) {
		return executePathCommand(channel, CMDS.mkdirp(paths), paths);
	}

	public static Result chown(SecureChannel channel, String owner, String group, List<String> paths) {
		List<String> options = Arrays.asList(TRAVERSE_SYMBOLIC_LINKS);
		String cmd = CMDS.chownr(options, owner, group, paths);
		return executePathCommand(channel, cmd, paths);
	}

	public static void executePathCommand(SecureChannel channel, String command, String path) {
		executePathCommand(channel, command, Collections.singletonList(path));
	}

	public static Result executePathCommand(SecureChannel channel, String command, List<String> paths) {
		Result result = channel.executeCommand(command);
		validateResult(result);
		return result;
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
