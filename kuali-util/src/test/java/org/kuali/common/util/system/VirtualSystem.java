package org.kuali.common.util.system;

import static com.google.common.collect.Maps.newHashMap;
import static com.google.common.collect.Sets.newHashSet;

import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.kuali.common.util.build.ValidatingBuilder;
import org.kuali.common.util.property.ImmutableProperties;
import org.kuali.common.util.validate.IdiotProofImmutable;
import org.kuali.common.util.validate.IgnoreBlanks;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

@IdiotProofImmutable
@JsonDeserialize(builder = VirtualSystem.Builder.class)
public final class VirtualSystem {

	/**
	 * Set of system property keys required to be present on every JVM
	 */
	@JsonIgnore
	public static final ImmutableSet<String> UNIVERSAL_PROPERTY_KEYS = getUniversalPropertyKeys();

	/**
	 * Mappings between universal property keys and the strongly typed field they correspond to in the VirtualSystem object
	 */
	@JsonIgnore
	public static final ImmutableMap<String, String> PROPERTY_MAPPINGS = getPropertyMappings();

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

	private static final ImmutableSet<String> getUniversalPropertyKeys() {
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
		keys.add("java.compiler");
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

	private static final ImmutableMap<String, String> getPropertyMappings() {
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
