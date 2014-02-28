package org.kuali.common.devops.json.system;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.google.common.collect.Maps.newHashMap;
import static com.google.common.collect.Sets.newTreeSet;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.kuali.common.util.log.Loggers.newLogger;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.junit.Test;
import org.kuali.common.devops.json.pojo.JacksonContext;
import org.kuali.common.devops.json.pojo.JacksonJsonService;
import org.kuali.common.devops.json.pojo.JsonService;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.system.VirtualSystem;
import org.slf4j.Logger;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

public class SystemTest {

	private static final Logger logger = newLogger();

	@Test
	public void test() {
		try {
			Properties props = PropertyUtils.duplicate(System.getProperties());
			trimBlanks(props, ImmutableSet.of("line.separator"));
			Map<String, List<String>> aliases = getSystemPropertyAliases();
			translate(props, aliases);
			JsonService service = getJsonService();
			String json = service.writeString(props);
			// System.out.println(json);
			VirtualSystem vs = service.readString(json, VirtualSystem.class);
			System.out.println(service.writeString(vs));
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	protected void trimBlanks(Properties properties, Set<String> exceptions) {
		for (String key : newTreeSet(properties.stringPropertyNames())) {
			if (!exceptions.contains(key)) {
				String value = properties.getProperty(key);
				if (isBlank(value)) {
					logger.info(String.format("ignoring blank value -> [%s]", key));
					properties.remove(key);
				}
			}
		}
	}

	protected Map<String, List<String>> getSystemPropertyAliases() {
		Map<String, List<String>> aliases = newHashMap();
		add(aliases, "pathSeparator", "path.separator");
		add(aliases, "fileSeparator", "file.separator");
		add(aliases, "lineSeparator", "line.separator");
		add(aliases, "java.classpath", "java.class.path");
		add(aliases, "java.classVersion", "java.class.version");
		add(aliases, "java.tmpDir", "java.io.tmpdir");
		add(aliases, "java.extensionDirs", "java.ext.dirs");
		add(aliases, "java.endorsedDirs", "java.endorsed.dirs");
		add(aliases, "java.libraryPaths", "java.library.path");
		add(aliases, "java.runtime.version", "java.version");
		add(aliases, "java.runtime.vendor", "java.vendor");
		add(aliases, "java.runtime.url", "java.vendor.url");
		add(aliases, "java.runtime.specification.version", "java.specification.version");
		add(aliases, "java.runtime.specification.vendor", "java.specification.vendor");
		add(aliases, "java.runtime.specification.name", "java.specification.name");
		return aliases;
	}

	protected void add(Map<String, List<String>> aliases, String key, String alias) {
		aliases.put(key, ImmutableList.of(alias));
	}

	protected void translate(Properties props, Map<String, List<String>> aliases) {
		for (String aliasKey : newTreeSet(aliases.keySet())) {
			List<String> aliasKeys = aliases.get(aliasKey);
			translate(props, aliasKey, aliasKeys);
		}
	}

	protected void translate(Properties props, String key, List<String> aliases) {
		for (String alias : aliases) {
			if (props.containsKey(alias)) {
				String value = props.getProperty(alias);
				props.remove(alias);
				props.setProperty(key, value);
				break;
			}
		}
	}

	protected JsonService getJsonService() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
		List<Module> modules = ImmutableList.of(new GuavaModule(), getModule());
		JacksonContext context = JacksonContext.builder().withModules(modules).withMapper(mapper).build();
		return new JacksonJsonService(context);
	}

	protected Module getModule() {
		SimpleModule module = new SimpleModule();
		module.addSerializer(new NestedPropertiesSerializer(Properties.class));
		return module;
	}
}
