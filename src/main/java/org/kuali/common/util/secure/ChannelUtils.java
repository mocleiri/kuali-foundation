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
package org.kuali.common.util.secure;

/**
 * @deprecated
 */
@Deprecated
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
