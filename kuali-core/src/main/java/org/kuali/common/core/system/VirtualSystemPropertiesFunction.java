/**
 * Copyright 2014-2014 The Kuali Foundation
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
package org.kuali.common.core.system;

import static com.google.common.base.Optional.fromNullable;
import static com.google.common.collect.Sets.newHashSet;
import static org.kuali.common.core.system.VirtualSystemHelper.DEFAULT_REQUIRED_SYSTEM_PROPERTY_KEYS;
import static org.kuali.common.core.system.VirtualSystemHelper.DEFAULT_REQUIRED_SYSTEM_PROPERTY_KEY_MAPPINGS;
import static org.kuali.common.util.base.Precondition.checkNotBlank;
import static org.kuali.common.util.base.Precondition.checkNotNull;

import java.util.Properties;
import java.util.Set;

import org.kuali.common.core.build.ValidatingBuilder;
import org.kuali.common.core.validate.annotation.IdiotProofImmutable;
import org.kuali.common.util.property.ImmutableProperties;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableSet;

@IdiotProofImmutable
public final class VirtualSystemPropertiesFunction implements Function<Properties, Properties> {

	private final ImmutableSet<String> requiredSystemPropertyKeys;
	private final ImmutableBiMap<String, String> requiredSystemPropertyKeyMappings;
	private final ImmutableSet<String> blanksAllowedKeys;

	@Override
	public Properties apply(Properties oldProperties) {
		checkNotNull(oldProperties, "oldProperties");
		Properties properties = new Properties();
		for (String key : requiredSystemPropertyKeys) {
			String value = checkNotNull(oldProperties.getProperty(key), key);
			Optional<String> mappedKey = fromNullable(requiredSystemPropertyKeyMappings.get(key));
			String actualKey = mappedKey.isPresent() ? mappedKey.get() : key;

			// By default only line.separator is allowed to be blank
			if (!blanksAllowedKeys.contains(key)) {
				checkNotBlank(value, key);
			}

			properties.setProperty(actualKey, value);
		}
		return ImmutableProperties.copyOf(properties);
	}

	private VirtualSystemPropertiesFunction(Builder builder) {
		this.requiredSystemPropertyKeys = ImmutableSet.copyOf(builder.requiredSystemPropertyKeys);
		this.requiredSystemPropertyKeyMappings = ImmutableBiMap.copyOf(builder.requiredSystemPropertyKeyMappings);
		this.blanksAllowedKeys = ImmutableSet.copyOf(builder.blanksAllowedKeys);
	}

	public static VirtualSystemPropertiesFunction newVirtualSystemPropertiesFunction() {
		return builder().build();
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder<VirtualSystemPropertiesFunction> {

		private static final String LINE_SEPARATOR_KEY = "line.separator";

		private Set<String> requiredSystemPropertyKeys = newHashSet(DEFAULT_REQUIRED_SYSTEM_PROPERTY_KEYS);
		private BiMap<String, String> requiredSystemPropertyKeyMappings = HashBiMap.create(DEFAULT_REQUIRED_SYSTEM_PROPERTY_KEY_MAPPINGS);
		private Set<String> blanksAllowedKeys = newHashSet(ImmutableSet.of(LINE_SEPARATOR_KEY));

		public Builder withRequiredSystemPropertyKeys(Set<String> requiredSystemPropertyKeys) {
			this.requiredSystemPropertyKeys = requiredSystemPropertyKeys;
			return this;
		}

		public Builder withRequiredSystemPropertyKeyMappings(BiMap<String, String> requiredSystemPropertyKeyMappings) {
			this.requiredSystemPropertyKeyMappings = requiredSystemPropertyKeyMappings;
			return this;
		}

		public Builder withBlanksAllowedKeys(Set<String> blanksAllowedKeys) {
			this.blanksAllowedKeys = blanksAllowedKeys;
			return this;
		}

		@Override
		public VirtualSystemPropertiesFunction build() {
			return validate(new VirtualSystemPropertiesFunction(this));
		}
	}

	public Set<String> getRequiredSystemPropertyKeys() {
		return requiredSystemPropertyKeys;
	}

	public BiMap<String, String> getRequiredSystemPropertyKeyMappings() {
		return requiredSystemPropertyKeyMappings;
	}

	public Set<String> getBlanksAllowedKeys() {
		return blanksAllowedKeys;
	}

}
