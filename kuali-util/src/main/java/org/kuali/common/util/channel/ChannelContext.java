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

import org.kuali.common.util.Assert;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.nullify.NullUtils;

import com.google.common.collect.ImmutableList;

public final class ChannelContext {

	private final File knownHosts;
	private final File config;
	private final boolean useConfigFile;
	private final boolean useKnownHosts;
	private final boolean includeDefaultPrivateKeyLocations;
	private final int waitForClosedSleepMillis;
	private final List<File> privateKeys;
	private final List<String> privateKeyStrings;

	public static class Builder {

		// Optional
		private File knownHosts = SSHUtils.DEFAULT_KNOWN_HOSTS;
		private boolean useKnownHosts = false;
		private File config = SSHUtils.DEFAULT_CONFIG_FILE;
		private boolean useConfigFile = false;
		private boolean includeDefaultPrivateKeyLocations = true;
		private int waitForClosedSleepMillis = 10;
		private List<File> privateKeys = ImmutableList.of();
		private List<String> privateKeyStrings = ImmutableList.of();

		public ChannelContext build() {
			Assert.noNulls(knownHosts, config, privateKeys, privateKeyStrings);
			Assert.positive(waitForClosedSleepMillis);
			if (useConfigFile) {
				Assert.exists(config);
				Assert.isTrue(config.canRead(), "[" + config + "] exists but is not readable");
			}
			this.privateKeys = ImmutableList.copyOf(getUniquePrivateKeyFiles(privateKeys, useConfigFile, config, includeDefaultPrivateKeyLocations));
			this.privateKeyStrings = ImmutableList.copyOf(privateKeyStrings);
			return new ChannelContext(this);
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

		public Builder privateKeys(List<File> privateKeys) {
			this.privateKeys = privateKeys;
			return this;
		}

		public Builder privateKeyString(String privateKeyString) {
			return privateKeyStrings(ImmutableList.of(NullUtils.trimToNull(privateKeyString)));
		}

		public Builder privateKeyStrings(List<String> privateKeyStrings) {
			this.privateKeyStrings = privateKeyStrings;
			return this;
		}

		private List<File> getUniquePrivateKeyFiles(List<File> privateKeys, boolean useConfigFile, File config, boolean includeDefaultPrivateKeyLocations) {
			List<String> paths = new ArrayList<String>();
			for (File privateKey : privateKeys) {
				paths.add(LocationUtils.getCanonicalPath(privateKey));
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

	}

	private ChannelContext(Builder builder) {
		this.knownHosts = builder.knownHosts;
		this.config = builder.config;
		this.useConfigFile = builder.useConfigFile;
		this.includeDefaultPrivateKeyLocations = builder.includeDefaultPrivateKeyLocations;
		this.waitForClosedSleepMillis = builder.waitForClosedSleepMillis;
		this.privateKeys = builder.privateKeys;
		this.privateKeyStrings = builder.privateKeyStrings;
		this.useKnownHosts = builder.useKnownHosts;
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

	public boolean isIncludeDefaultPrivateKeyLocations() {
		return includeDefaultPrivateKeyLocations;
	}

	public int getWaitForClosedSleepMillis() {
		return waitForClosedSleepMillis;
	}

	public List<File> getPrivateKeys() {
		return privateKeys;
	}

	public List<String> getPrivateKeyStrings() {
		return privateKeyStrings;
	}

	public boolean isUseKnownHosts() {
		return useKnownHosts;
	}

}
