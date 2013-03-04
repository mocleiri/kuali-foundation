package org.kuali.common.util.secure;

public class ChannelUtils {

	public static String getLocation(String username, String hostname, RemoteFile file) {
		return getLocation(username, hostname) + ":" + file.getAbsolutePath();
	}

	public static String getLocation(String username, String hostname) {
		return (username == null) ? hostname : username + "@" + hostname;
	}

	public static Result getExecutionResult(int exitValue, long start, String command, String stdin, String stdout, String stderr, String encoding) {
		long stop = System.currentTimeMillis();
		long elapsed = stop - start;
		Result result = new Result();
		result.setEncoding(encoding);
		result.setCommand(command);
		result.setElapsed(elapsed);
		result.setStart(start);
		result.setStop(stop);
		result.setExitValue(exitValue);
		result.setStdin(stdin);
		result.setStdout(stdout);
		result.setStderr(stderr);
		return result;
	}

	public static void closeQuietly(SecureChannel channel) {
		if (channel != null) {
			channel.close();
		}
	}

}
