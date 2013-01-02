package org.kuali.common.util.secure;

public class ChannelUtils {

	public static Result getExecutionResult(int exitValue, long start, byte[] stdin, byte[] stdout, byte[] stderr, String command) {
		long stop = System.currentTimeMillis();
		long elapsed = stop - start;
		Result result = new Result();
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
