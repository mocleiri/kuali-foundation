package org.kuali.common.util.spring.env;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.kuali.common.util.PropertyUtils;
import org.springframework.core.env.MapPropertySource;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;

/**
 * <p>
 * When {@code getProperty(name)} is called, the exact name is checked. If no value is found, the name is then converted into several aliases representing a few different ways the
 * name might be represented as an environment variable. All of the aliases are then also checked.
 * </p>
 * 
 * <pre>
 *   foo.barBaz -> env.FOO_BAR_BAZ
 *                 FOO_BAR_BAZ
 *                 env.foo_bar_baz
 *                 foo_bar_baz
 * </pre>
 */
public class SysEnvPropertySource extends MapPropertySource {

	private final Map<String, ImmutableSet<String>> aliasCache = Maps.newConcurrentMap();

	public SysEnvPropertySource() {
		this(PropertyUtils.getGlobalProperties());
	}

	public SysEnvPropertySource(Properties properties) {
		this("sys-env", properties);
	}

	public SysEnvPropertySource(String name, Properties properties) {
		super(name, convert(properties));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getProperty(String name) {
		checkNotNull(name, "'name' cannot be null");
		Object value = super.getProperty(name);
		if (value != null) {
			return value;
		} else {
			Set<String> aliases = getAliases(name);
			return getProperty(aliases, name);
		}
	}

	protected Object getProperty(Set<String> aliases, String original) {
		for (String alias : aliases) {
			Object value = super.getProperty(alias);
			if (value != null) {
				logger.debug(String.format("PropertySource [%s] does not contain '%s', but found equivalent '%s'", this.getName(), original, alias));
				return value;
			}
		}
		return null;
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
		ImmutableSet<String> aliases = aliasCache.get(name);
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
			aliasCache.put(name, aliases);
		}
		return aliases;
	}

	protected static Map<String, Object> convert(Properties properties) {
		Map<String, Object> map = Maps.newHashMap();
		for (String key : properties.stringPropertyNames()) {
			map.put(key, properties.getProperty(key));
		}
		return map;
	}

	public void clearAliasCache() {
		aliasCache.clear();
	}

}
