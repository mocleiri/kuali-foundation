package org.kuali.common.devops.json.system;

import static com.google.common.collect.Maps.newHashMap;
import static com.google.common.collect.Sets.newTreeSet;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.kuali.common.util.base.Precondition.checkNotNull;

import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.kuali.common.util.validate.IdiotProofImmutable;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

/**
 * Convert default JDK system properties into the properties needed by {@code VirtualSystem}, and in the process remove all blank properties except for {@code line.separator}
 */
@IdiotProofImmutable
public final class SystemPropertiesFunction implements Function<Properties, Properties> {

	private static final Map<String, String> DEFAULT_MAPPINGS = getDefaultMappings();

	public SystemPropertiesFunction() {
		this(ImmutableSet.of("line.separator"), DEFAULT_MAPPINGS);
	}

	public SystemPropertiesFunction(Set<String> blanksAllowed, Map<String, String> mappings) {
		this.blanksAllowed = ImmutableSet.copyOf(blanksAllowed);
		this.mappings = ImmutableMap.copyOf(mappings);
	}

	private final ImmutableSet<String> blanksAllowed;
	private final ImmutableMap<String, String> mappings;

	@Override
	public Properties apply(Properties oldProperties) {
		checkNotNull(oldProperties, "oldProperties");
		Properties newProperties = new Properties();
		newProperties.putAll(oldProperties);
		removeBlanks(newProperties, blanksAllowed);
		for (String newKey : newTreeSet(mappings.keySet())) {
			String oldKey = mappings.get(newKey);
			newProperties.setProperty(newKey, newProperties.getProperty(oldKey));
			newProperties.remove(oldKey);
		}
		return newProperties;
	}

	protected void removeBlanks(Properties properties, Set<String> exceptions) {
		for (String key : newTreeSet(properties.stringPropertyNames())) {
			if (isBlank(properties.getProperty(key)) && !blanksAllowed.contains(key)) {
				properties.remove(key);
			}
		}
	}

	public static Map<String, String> getDefaultMappings() {
		Map<String, String> mappings = newHashMap();
		mappings.put("pathSeparator", "path.separator");
		mappings.put("fileSeparator", "file.separator");
		mappings.put("lineSeparator", "line.separator");
		mappings.put("java.classpath", "java.class.path");
		mappings.put("java.classVersion", "java.class.version");
		mappings.put("java.tmpDir", "java.io.tmpdir");
		mappings.put("java.extensionDirs", "java.ext.dirs");
		mappings.put("java.endorsedDirs", "java.endorsed.dirs");
		mappings.put("java.libraryPaths", "java.library.path");
		mappings.put("java.runtime.version", "java.version");
		mappings.put("java.runtime.vendor", "java.vendor");
		mappings.put("java.runtime.vendorUrl", "java.vendor.url");
		mappings.put("java.runtime.specification.version", "java.specification.version");
		mappings.put("java.runtime.specification.vendor", "java.specification.vendor");
		mappings.put("java.runtime.specification.name", "java.specification.name");
		return ImmutableMap.copyOf(mappings);

	}

	public Set<String> getBlanksAllowed() {
		return blanksAllowed;
	}

	public Map<String, String> getMappings() {
		return mappings;
	}
}
