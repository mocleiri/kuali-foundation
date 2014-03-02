package org.kuali.common.devops.json.system;

import static com.google.common.base.Optional.fromNullable;
import static org.kuali.common.util.base.Precondition.checkNotBlank;
import static org.kuali.common.util.base.Precondition.checkNotNull;
import static org.kuali.common.util.system.VirtualSystem.UNIVERSAL_SYSTEM_PROPERTY_KEYS;
import static org.kuali.common.util.system.VirtualSystem.UNIVERSAL_SYSTEM_PROPERTY_KEY_MAPPINGS;

import java.util.Properties;

import org.kuali.common.util.validate.IdiotProofImmutable;

import com.google.common.base.Function;
import com.google.common.base.Optional;

/**
 * Convert default JDK system properties into the properties needed by {@code VirtualSystem}
 */
@IdiotProofImmutable
public final class SystemPropertiesFunction implements Function<Properties, Properties> {

	private static final String LINE_SEPARATOR_KEY = "line.separator";

	@Override
	public Properties apply(Properties oldProperties) {
		checkNotNull(oldProperties, "oldProperties");
		Properties newProperties = new Properties();
		for (String key : UNIVERSAL_SYSTEM_PROPERTY_KEYS) {
			String value = checkNotNull(oldProperties.getProperty(key), key);
			Optional<String> mappedKey = fromNullable(UNIVERSAL_SYSTEM_PROPERTY_KEY_MAPPINGS.get(key));
			String actualKey = mappedKey.isPresent() ? mappedKey.get() : key;
			if (LINE_SEPARATOR_KEY.equals(key)) {
				// The only universal system property allowed to be blank is the line separator
				newProperties.setProperty(actualKey, value);
			} else {
				// All other properties cannot be blank
				newProperties.setProperty(actualKey, checkNotBlank(value, key));
			}
		}
		return newProperties;
	}

}
