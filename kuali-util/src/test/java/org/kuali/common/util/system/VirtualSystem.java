package org.kuali.common.util.system;

import static com.google.common.base.Optional.fromNullable;
import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.collect.Sets.newHashSet;
import static org.kuali.common.util.base.Precondition.checkNotBlank;
import static org.kuali.common.util.base.Precondition.checkNotNull;

import java.util.Properties;
import java.util.Set;

import org.kuali.common.util.build.ValidatingBuilder;
import org.kuali.common.util.property.ImmutableProperties;
import org.kuali.common.util.validate.IdiotProofImmutable;
import org.kuali.common.util.validate.IgnoreBlanks;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.Optional;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableSet;

@IdiotProofImmutable
@JsonDeserialize(builder = VirtualSystem.Builder.class)
public final class VirtualSystem {

	/**
	 * Set of system property keys required to be present on every JVM
	 */
	public static final ImmutableSet<String> REQUIRED_SYSTEM_PROPERTY_KEYS = getRequiredPropertyKeys();

	/**
	 * Mappings between required system property keys and the strongly typed field they correspond to in the VirtualSystem object
	 */
	private static final ImmutableBiMap<String, String> REQUIRED_SYSTEM_PROPERTY_KEY_MAPPINGS = getPropertyMappings();

	/**
	 * Required system properties mapped to the strongly typed field they correspond to in the virtual system object
	 */
	public static final ImmutableProperties MAPPED_SYSTEM_PROPERTIES = getMappedSystemProperties();

	private static final String LINE_SEPARATOR_KEY = "line.separator";

	private final User user;
	private final OperatingSystem os;
	private final Java java;
	@IgnoreBlanks
	private final String lineSeparator;
	private final String pathSeparator;
	private final String fileSeparator;
	private final ImmutableProperties properties;
	private final ImmutableProperties environment;

	public static Builder builder() {
		return new Builder();
	}

	private VirtualSystem(Builder builder) {
		this.user = builder.user;
		this.os = builder.os;
		this.java = builder.java;
		this.lineSeparator = builder.lineSeparator;
		this.pathSeparator = builder.pathSeparator;
		this.fileSeparator = builder.fileSeparator;
		this.properties = ImmutableProperties.copyOf(builder.properties);
		this.environment = ImmutableProperties.copyOf(builder.environment);
	}

	public static class Builder extends ValidatingBuilder<VirtualSystem> {

		private User user;
		private OperatingSystem os;
		private Java java;
		private String lineSeparator;
		private String pathSeparator;
		private String fileSeparator;
		private Properties properties = new Properties();
		private Properties environment = new Properties();

		public Builder withUser(User user) {
			this.user = user;
			return this;
		}

		public Builder withOs(OperatingSystem os) {
			this.os = os;
			return this;
		}

		public Builder withJava(Java java) {
			this.java = java;
			return this;
		}

		public Builder withLineSeparator(String lineSeparator) {
			this.lineSeparator = lineSeparator;
			return this;
		}

		public Builder withPathSeparator(String pathSeparator) {
			this.pathSeparator = pathSeparator;
			return this;
		}

		public Builder withFileSeparator(String fileSeparator) {
			this.fileSeparator = fileSeparator;
			return this;
		}

		public Builder withProperties(Properties properties) {
			this.properties = properties;
			return this;
		}

		public Builder withEnvironment(Properties environment) {
			this.environment = environment;
			return this;
		}

		@Override
		public VirtualSystem build() {
			return validate(new VirtualSystem(this));
		}
	}

	private static final ImmutableSet<String> getRequiredPropertyKeys() {
		Set<String> keys = newHashSet();
		keys.add("java.version");
		keys.add("java.vendor");
		keys.add("java.vendor.url");
		keys.add("java.home");
		keys.add("java.vm.specification.version");
		keys.add("java.vm.specification.vendor");
		keys.add("java.vm.specification.name");
		keys.add("java.vm.version");
		keys.add("java.vm.vendor");
		keys.add("java.vm.name");
		keys.add("java.specification.version");
		keys.add("java.specification.vendor");
		keys.add("java.specification.name");
		keys.add("java.class.version");
		keys.add("java.class.path");
		keys.add("java.library.path");
		keys.add("java.io.tmpdir");
		// keys.add("java.compiler"); javadoc states this is a required property but it sure isn't
		keys.add("java.ext.dirs");
		keys.add("os.name");
		keys.add("os.arch");
		keys.add("os.version");
		keys.add("file.separator");
		keys.add("path.separator");
		keys.add("line.separator");
		keys.add("user.name");
		keys.add("user.home");
		keys.add("user.dir");
		return ImmutableSet.copyOf(keys);
	}

	private static final ImmutableBiMap<String, String> getPropertyMappings() {
		BiMap<String, String> mappings = HashBiMap.create();
		mappings.put("path.separator", "pathSeparator");
		mappings.put("file.separator", "fileSeparator");
		mappings.put("line.separator", "lineSeparator");
		mappings.put("java.class.path", "java.classpath");
		mappings.put("java.class.version", "java.classVersion");
		mappings.put("java.io.tmpdir", "java.tmpDir");
		mappings.put("java.ext.dirs", "java.extensionDirs");
		mappings.put("java.library.path", "java.libraryPaths");
		mappings.put("java.version", "java.runtime.version");
		mappings.put("java.vendor", "java.runtime.vendor");
		mappings.put("java.vendor.url", "java.runtime.vendorUrl");
		mappings.put("java.specification.version", "java.runtime.specification.version");
		mappings.put("java.specification.vendor", "java.runtime.specification.vendor");
		mappings.put("java.specification.name", "java.runtime.specification.name");

		Set<String> universal = getRequiredPropertyKeys();
		for (String key : mappings.keySet()) {
			checkArgument(universal.contains(key), "unknown key [%s]", key);
		}

		return ImmutableBiMap.copyOf(mappings);

	}

	private static ImmutableProperties getMappedSystemProperties() {
		Properties properties = new Properties();
		for (String key : REQUIRED_SYSTEM_PROPERTY_KEYS) {
			String value = checkNotNull(System.getProperty(key), key);
			Optional<String> mappedKey = fromNullable(REQUIRED_SYSTEM_PROPERTY_KEY_MAPPINGS.get(key));
			String actualKey = mappedKey.isPresent() ? mappedKey.get() : key;

			// The only required system property allowed to be blank is the line separator
			if (!LINE_SEPARATOR_KEY.equals(key)) {
				checkNotBlank(value, key);
			}

			properties.setProperty(actualKey, value);
		}
		return ImmutableProperties.copyOf(properties);
	}

	public User getUser() {
		return user;
	}

	public OperatingSystem getOs() {
		return os;
	}

	public Java getJava() {
		return java;
	}

	public String getLineSeparator() {
		return lineSeparator;
	}

	public String getPathSeparator() {
		return pathSeparator;
	}

	public String getFileSeparator() {
		return fileSeparator;
	}

	public Properties getProperties() {
		return properties;
	}

	public Properties getEnvironment() {
		return environment;
	}

}
