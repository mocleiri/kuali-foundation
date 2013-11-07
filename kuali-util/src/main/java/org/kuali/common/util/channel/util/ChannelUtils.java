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
package org.kuali.common.util.channel.util;

import org.kuali.common.util.Str;
import org.kuali.common.util.channel.api.SecureChannel;
import org.kuali.common.util.channel.model.RemoteFile;
import org.kuali.common.util.channel.model.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Optional;

public class ChannelUtils {

	private static final Logger logger = LoggerFactory.getLogger(ChannelUtils.class);

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
	public static Result exec(SecureChannel channel, String command) {
		return exec(channel, command, false);
	}

	/**
	 * Execute <code>command</code> on the channel and validate the exit value.
	 * 
	 * @throws IllegalStateException
	 *             If the command returns a non-zero exit value
	 */
	public static Result exec(SecureChannel channel, String command, boolean echo) {
		if (echo) {
			logger.info(command);
		}
		Result result = channel.executeCommand(command);
		if (result.getExitValue() != 0) {
			StringBuilder sb = new StringBuilder();
			sb.append("Non-zero exit value: [" + result.getExitValue() + "]\n");
			sb.append("\n");
			sb.append("[" + command + "]");
			sb.append("\n");
			sb.append("stdout:[" + Str.flatten(result.getStdout()) + "]\n");
			sb.append("stderr:[" + Str.flatten(result.getStderr()) + "]\n");
			sb.append("\n");
			throw new IllegalStateException(sb.toString());
		} else {
			return result;
		}
	}
}