package org.kuali.common.util.spring.env;

import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.springframework.util.Assert;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;

public class SystemEnvPropertySource extends SystemEnvironmentPropertySource {

	private final Map<String, ImmutableSet<String>> cache = Maps.newConcurrentMap();

	public SystemEnvPropertySource(String name, Properties properties) {
		this(name, convert(properties));
	}

	public SystemEnvPropertySource(String name, Map<String, Object> source) {
		super(name, source);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getProperty(String name) {
		Assert.notNull(name, "property name must not be null");
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
		for (String alias : aliases) {
			if (super.containsProperty(alias)) {
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
