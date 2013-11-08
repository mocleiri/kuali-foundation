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

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.Assert;
import org.kuali.common.util.Str;
import org.kuali.common.util.channel.api.SecureChannel;
import org.kuali.common.util.channel.model.ChannelContext;
import org.kuali.common.util.channel.model.RemoteFile;
import org.kuali.common.util.channel.model.Result;
import org.kuali.common.util.enc.EncUtils;
import org.kuali.common.util.enc.EncryptionService;
import org.kuali.common.util.nullify.NullUtils;
import org.kuali.common.util.spring.SpringUtils;
import org.kuali.common.util.spring.env.EnvironmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

public class ChannelUtils {

	private static final Logger logger = LoggerFactory.getLogger(ChannelUtils.class);

	private static final ChannelContext DEFAULT = new ChannelContext.Builder(NullUtils.NONE).build();

	private static final String USERNAME_KEY = "ssh.username";
	private static final String HOSTNAME_KEY = "ssh.hostname";
	private static final String PRIVATEKEY_KEY = "ssh.privateKey";
	private static final String REQUEST_PSEUDO_TERMINAL_KEY = "ssh.requestPseudoTerminal";

	public static ChannelContext getContext(EnvironmentService env, EncryptionService enc) {
		return getContext(env, enc, DEFAULT);
	}

	public static ChannelContext getContext(EnvironmentService env, EncryptionService enc, ChannelContext provided) {
		String hostname = NullUtils.trimToNull(env.getString(HOSTNAME_KEY, provided.getHostname()));
		Optional<String> username = SpringUtils.getString(env, USERNAME_KEY, provided.getUsername());
		boolean requestPseudoTerminal = env.getBoolean(REQUEST_PSEUDO_TERMINAL_KEY, provided.isRequestPseudoTerminal());
		List<String> privateKeys = getPrivateKeys(env, enc, provided);
		return new ChannelContext.Builder(hostname, provided).username(username.orNull()).privateKeys(privateKeys).requestPseudoTerminal(requestPseudoTerminal).build();
	}

	protected static List<String> getPrivateKeys(EnvironmentService env, EncryptionService enc, ChannelContext provided) {
		Optional<String> optional = SpringUtils.getString(env, PRIVATEKEY_KEY, Optional.<String> absent());
		if (optional.isPresent()) {
			return getDecrypted(enc, ImmutableList.of(optional.get()));
		} else {
			return getDecrypted(enc, provided.getPrivateKeys());
		}
	}

	protected static List<String> getDecrypted(EncryptionService enc, List<String> strings) {
		Assert.noNulls(enc, strings);
		List<String> list = new ArrayList<String>();
		for (String string : strings) {
			Assert.noBlanks(string);
			if (EncUtils.isEncrypted(string)) {
				list.add(enc.decrypt(string));
			} else {
				list.add(string);
			}
		}
		return ImmutableList.copyOf(list);
	}

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
		return exec(channel, command, true);
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
			if (echo) {
				log(result);
			}
			return result;
		}
	}

	public static void log(Result result) {
		Assert.noNulls(result);
		String stdout = NullUtils.trimToNull(result.getStdout());
		String stderr = NullUtils.trimToNull(result.getStderr());
		if (!StringUtils.isBlank(stdout)) {
			logger.info(" -- Standard Out --\n\n{}\n--Standard Out--\n", stdout);
		}
		if (!StringUtils.isBlank(stderr)) {
			logger.warn(" -- Standard Err --\n\n{}\n--Standard Err--\n", stderr);
		}
	}

	public static void closeQuietly(SecureChannel channel) {
		if (channel != null) {
			channel.close();
		}
	}

}