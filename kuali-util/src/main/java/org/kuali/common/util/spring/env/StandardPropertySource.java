package org.kuali.common.util.spring.env;

import static org.springframework.util.Assert.notNull;

import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.springframework.core.env.SystemEnvironmentPropertySource;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;

/**
 * Automatically check the environment variable equivalent if a normal key is not found.
 */
public class StandardPropertySource extends SystemEnvironmentPropertySource {

	private final Map<String, ImmutableSet<String>> cache = Maps.newConcurrentMap();

	/**
	 * Automatically check the environment variable equivalent if a normal key is not found.
	 */
	public StandardPropertySource(Properties properties) {
		this("standard", properties);
	}

	public StandardPropertySource(String name, Properties properties) {
		super(name, convert(properties));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getProperty(String name) {
		notNull(name, "property name must not be null");
		Set<String> aliases = getAliases(name);
		String actualName = getActualName(name, aliases);
		if (logger.isDebugEnabled() && !name.equals(actualName)) {
			logger.debug(String.format("PropertySource [%s] does not contain '%s', but found equivalent '%s'", this.getName(), name, actualName));
		}
		return super.getProperty(actualName);
	}

	/**
	 * <pre>
	 *   foo.barBaz -> env.FOO_BAR_BAZ
	 *                 FOO_BAR_BAZ
	 *                 env.foo_bar_baz
	 *                 foo_bar_baz
	 * </pre>
	 */
	protected ImmutableSet<String> getAliases(String name) {
		ImmutableSet<String> aliases = cache.get(name);
		if (aliases == null) {
			// foo.barBaz -> env.FOO_BAR_BAZ
			String env1 = EnvUtils.getEnvironmentVariableKey(name);
			// foo.barBaz -> FOO_BAR_BAZ
			String env2 = EnvUtils.toUnderscore(name).toUpperCase();
			// foo.barBaz -> env.foo_bar_baz
			String env3 = env1.toLowerCase();
			// foo.barBaz -> foo_bar_baz
			String env4 = env2.toLowerCase();
			aliases = ImmutableSet.of(env1, env2, env3, env4);
			cache.put(name, aliases);
		}
		return aliases;
	}

	public void clearCache() {
		cache.clear();
	}

	protected String getActualName(String name, Set<String> aliases) {
		if (super.getProperty(name) != null) {
			return name;
		}
		for (String alias : aliases) {
			if (super.getProperty(alias) != null) {
				return alias;
			}
		}
		return name;
	}

	protected static Map<String, Object> convert(Properties properties) {
		Map<String, Object> map = Maps.newHashMap();
		for (String key : properties.stringPropertyNames()) {
			map.put(key, properties.getProperty(key));
		}
		return map;
	}

}
