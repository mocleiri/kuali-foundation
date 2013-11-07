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
package org.kuali.common.util.channel;

import org.kuali.common.util.Str;

import com.google.common.base.Optional;

public class ChannelUtils {

	public static String getLocation(Optional<String> username, String hostname, RemoteFile file) {
		return getLocation(username, hostname) + ":" + file.getAbsolutePath();
	}

	public static String getLocation(Optional<String> username, String hostname) {
		if (username.isPresent()) {
			return username.get() + "@" + hostname;
		} else {
			return hostname;
		}
	}

	/**
	 * Execute <code>command</code> on the channel and validate the exit value.
	 * 
	 * @throws IllegalStateException
	 *             If the command returns with a non-zero exit value
	 */
	public static void exec(SecureChannel channel, String command) {
		Result result = channel.executeCommand(command);
		if (result.getExitValue() != 0) {
			StringBuilder sb = new StringBuilder();
			sb.append("\n");
			sb.append(command + "\n");
			sb.append("Non-zero exit value: " + result.getExitValue() + "\n");
			sb.append("stdout:[" + Str.flatten(result.getStdout()) + "]\n");
			sb.append("stderr:[" + Str.flatten(result.getStderr()) + "]\n");
			sb.append("\n");
			throw new IllegalStateException(sb.toString());
		}
	}

}
