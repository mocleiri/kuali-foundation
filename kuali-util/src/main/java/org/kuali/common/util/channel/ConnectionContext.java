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

import java.util.Properties;

import org.kuali.common.util.Assert;
import org.kuali.common.util.nullify.NullUtils;
import org.kuali.common.util.property.ImmutableProperties;

import com.google.common.base.Optional;

public final class ConnectionContext {

	private final Optional<String> username;
	private final String hostname;
	private final int port;
	private final Optional<Integer> connectTimeout;
	private final String encoding;
	private final Properties options;
	private final boolean strictHostKeyChecking;
	private final boolean requestPseudoTerminal;

	public static class Builder {

		// Required
		private final String hostname;

		// Optional
		private Optional<String> username = Optional.absent();
		private int port = 22;
		private Optional<Integer> connectTimeout = Optional.absent();
		private String encoding = "UTF-8";
		private Properties options = ImmutableProperties.of();
		private boolean strictHostKeyChecking = false;
		private boolean requestPseudoTerminal = false;

		public ConnectionContext build() {
			Assert.noBlanks(hostname, encoding);
			Assert.noNulls(username, connectTimeout, options);
			Assert.isPort(port);
			if (connectTimeout.isPresent()) {
				Assert.positive(connectTimeout.get());
			}
			this.options = ImmutableProperties.of(getSessionProperties(options, strictHostKeyChecking));
			return new ConnectionContext(this);
		}

		public Builder(String hostname) {
			this((String) null, hostname);
		}

		public Builder(String username, String hostname) {
			this(Optional.fromNullable(NullUtils.trimToNull(username)), hostname);
		}

		public Builder(Optional<String> username, String hostname) {
			this.username = username;
			this.hostname = hostname;
		}

		public Builder requestPseudoTerminal(boolean requestPseudoTerminal) {
			this.requestPseudoTerminal = requestPseudoTerminal;
			return this;
		}

		public Builder username(String username) {
			this.username = Optional.fromNullable(NullUtils.trimToNull(username));
			return this;
		}

		public Builder port(int port) {
			this.port = port;
			return this;
		}

		public Builder encoding(String encoding) {
			this.encoding = encoding;
			return this;
		}

		public Builder connectTimeout(int connectTimeout) {
			this.connectTimeout = Optional.of(connectTimeout);
			return this;
		}

		public Builder options(Properties options) {
			this.options = options;
			return this;
		}

		private Properties getSessionProperties(Properties options, boolean strictHostKeyChecking) {
			Properties properties = new Properties();
			properties.putAll(options);
			if (!strictHostKeyChecking) {
				properties.setProperty(SSHUtils.STRICT_HOST_KEY_CHECKING, SSHUtils.NO);
			}
			return properties;
		}
	}

	private ConnectionContext(Builder builder) {
		this.username = builder.username;
		this.hostname = builder.hostname;
		this.port = builder.port;
		this.encoding = builder.encoding;
		this.connectTimeout = builder.connectTimeout;
		this.options = builder.options;
		this.strictHostKeyChecking = builder.strictHostKeyChecking;
		this.requestPseudoTerminal = builder.requestPseudoTerminal;
	}

	public Optional<String> getUsername() {
		return username;
	}

	public String getHostname() {
		return hostname;
	}

	public int getPort() {
		return port;
	}

	public Optional<Integer> getConnectTimeout() {
		return connectTimeout;
	}

	public String getEncoding() {
		return encoding;
	}

	public Properties getOptions() {
		return options;
	}

	public boolean isStrictHostKeyChecking() {
		return strictHostKeyChecking;
	}

	public boolean isRequestPseudoTerminal() {
		return requestPseudoTerminal;
	}

}
