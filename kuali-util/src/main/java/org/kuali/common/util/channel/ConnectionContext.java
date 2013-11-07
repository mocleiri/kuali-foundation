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

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.kuali.common.util.Assert;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.nullify.NullUtils;
import org.kuali.common.util.property.ImmutableProperties;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

public final class ConnectionContext {

	private final Optional<String> username;
	private final String hostname;
	private final List<String> privateKeys;
	private final int port;
	private final String encoding;
	private final boolean strictHostKeyChecking;
	private final boolean requestPseudoTerminal;
	private final Optional<Integer> connectTimeout;
	private final Properties options;
	private final File knownHosts;
	private final File config;
	private final boolean useConfigFile;
	private final boolean useKnownHosts;
	private final boolean includeDefaultPrivateKeyLocations;
	private final int waitForClosedSleepMillis;
	private final List<File> privateKeyFiles;

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
		private File knownHosts = SSHUtils.DEFAULT_KNOWN_HOSTS;
		private boolean useKnownHosts = false;
		private File config = SSHUtils.DEFAULT_CONFIG_FILE;
		private boolean useConfigFile = false;
		private boolean includeDefaultPrivateKeyLocations = false;
		private int waitForClosedSleepMillis = 10;
		private List<File> privateKeyFiles = ImmutableList.of();
		private List<String> privateKeys = ImmutableList.of();

		public Builder(String hostname) {
			this(Optional.<String> absent(), hostname);
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

		public Builder knownHosts(File knownHosts) {
			this.knownHosts = knownHosts;
			return this;
		}

		public Builder useKnownHosts(boolean useKnownHosts) {
			this.useKnownHosts = useKnownHosts;
			return this;
		}

		public Builder config(File config) {
			this.config = config;
			return this;
		}

		public Builder useConfigFile(boolean useConfigFile) {
			this.useConfigFile = useConfigFile;
			return this;
		}

		public Builder includeDefaultPrivateKeyLocations(boolean includeDefaultPrivateKeyLocations) {
			this.includeDefaultPrivateKeyLocations = includeDefaultPrivateKeyLocations;
			return this;
		}

		public Builder waitForClosedSleepMillis(int waitForClosedSleepMillis) {
			this.waitForClosedSleepMillis = waitForClosedSleepMillis;
			return this;
		}

		public Builder privateKeyFiles(List<File> privateKeyFiles) {
			this.privateKeyFiles = privateKeyFiles;
			return this;
		}

		public Builder privateKeyString(String privateKeyString) {
			return privateKeys(ImmutableList.of(NullUtils.trimToNull(privateKeyString)));
		}

		public Builder privateKeys(List<String> privateKeys) {
			this.privateKeys = privateKeys;
			return this;
		}

		public ConnectionContext build() {
			Assert.noBlanks(hostname, encoding);
			Assert.noNulls(username, connectTimeout, options);
			Assert.isPort(port);
			Assert.noNulls(knownHosts, config, privateKeyFiles, privateKeys);
			Assert.positive(waitForClosedSleepMillis);
			if (useConfigFile) {
				Assert.exists(config);
				Assert.isTrue(config.canRead(), "[" + config + "] exists but is not readable");
			}
			this.privateKeyFiles = ImmutableList.copyOf(getUniquePrivateKeyFiles(privateKeyFiles, useConfigFile, config, includeDefaultPrivateKeyLocations));
			this.privateKeys = ImmutableList.copyOf(privateKeys);
			if (connectTimeout.isPresent()) {
				Assert.positive(connectTimeout.get());
			}
			this.options = ImmutableProperties.of(getSessionProperties(options, strictHostKeyChecking));
			return new ConnectionContext(this);
		}

		private List<File> getUniquePrivateKeyFiles(List<File> privateKeyFiles, boolean useConfigFile, File config, boolean includeDefaultPrivateKeyLocations) {
			List<String> paths = new ArrayList<String>();
			for (File privateKeyFile : privateKeyFiles) {
				paths.add(LocationUtils.getCanonicalPath(privateKeyFile));
			}
			if (useConfigFile) {
				for (String path : SSHUtils.getFilenames(config)) {
					paths.add(path);
				}
			}
			if (includeDefaultPrivateKeyLocations) {
				for (String path : SSHUtils.PRIVATE_KEY_DEFAULTS) {
					paths.add(path);
				}
			}
			List<String> uniquePaths = CollectionUtils.getUniqueStrings(paths);
			return SSHUtils.getExistingAndReadable(uniquePaths);
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
		this.knownHosts = builder.knownHosts;
		this.config = builder.config;
		this.useConfigFile = builder.useConfigFile;
		this.includeDefaultPrivateKeyLocations = builder.includeDefaultPrivateKeyLocations;
		this.waitForClosedSleepMillis = builder.waitForClosedSleepMillis;
		this.privateKeyFiles = builder.privateKeyFiles;
		this.privateKeys = builder.privateKeys;
		this.useKnownHosts = builder.useKnownHosts;
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

	public File getKnownHosts() {
		return knownHosts;
	}

	public File getConfig() {
		return config;
	}

	public boolean isUseConfigFile() {
		return useConfigFile;
	}

	public boolean isUseKnownHosts() {
		return useKnownHosts;
	}

	public boolean isIncludeDefaultPrivateKeyLocations() {
		return includeDefaultPrivateKeyLocations;
	}

	public int getWaitForClosedSleepMillis() {
		return waitForClosedSleepMillis;
	}

	public List<File> getPrivateKeyFiles() {
		return privateKeyFiles;
	}

	public List<String> getPrivateKeys() {
		return privateKeys;
	}

}
