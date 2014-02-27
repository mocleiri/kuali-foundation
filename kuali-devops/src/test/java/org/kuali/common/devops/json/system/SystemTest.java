package org.kuali.common.devops.json.system;

import static com.google.common.collect.Sets.newTreeSet;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.junit.Test;
import org.kuali.common.devops.json.pojo.JacksonContext;
import org.kuali.common.devops.json.pojo.JacksonJsonService;
import org.kuali.common.devops.json.pojo.JsonService;
import org.kuali.common.util.PropertyUtils;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;

public class SystemTest {

	@Test
	public void test() {
		try {
			Properties props = PropertyUtils.duplicate(System.getProperties());
			Map<String, List<String>> aliases = getSystemPropertyAliases();
			translate(props, aliases);
			JsonService service = getJsonService();
			System.out.println(service.writeString(props));
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	protected Map<String, List<String>> getSystemPropertyAliases() {
		Map<String, List<String>> aliases = Maps.newHashMap();
		aliases.put("lineSeparator", ImmutableList.of("line.separator"));
		aliases.put("pathSeparator", ImmutableList.of("path.separator"));
		aliases.put("fileSeparator", ImmutableList.of("file.separator"));
		aliases.put("java.classpath", ImmutableList.of("java.class.path"));
		aliases.put("java.classVersion", ImmutableList.of("java.classVersion"));
		aliases.put("java.tempDir", ImmutableList.of("java.io.tmpdir"));
		return aliases;
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
		mapper.registerModule(getModule());
		List<Module> modules = ImmutableList.of(new GuavaModule(), getModule());
		JacksonContext context = JacksonContext.builder().withModules(modules).build();
		return new JacksonJsonService(context);
	}

	protected Module getModule() {
		SimpleModule module = new SimpleModule();
		module.addSerializer(new NestedPropertiesSerializer(Properties.class));
		return module;
	}
}
