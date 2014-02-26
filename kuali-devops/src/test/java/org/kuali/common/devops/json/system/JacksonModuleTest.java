package org.kuali.common.devops.json.system;

import java.util.List;
import java.util.Properties;

import org.junit.Test;
import org.kuali.common.devops.json.pojo.JacksonContext;
import org.kuali.common.devops.json.pojo.JacksonJsonService;
import org.kuali.common.devops.json.pojo.JsonService;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.google.common.collect.ImmutableList;

public class JacksonModuleTest {

	@Test
	public void test() {
		try {
			Properties props = new Properties();
			props.setProperty("foo.bar", "baz");
			// props.setProperty("a", "1");
			// props.setProperty("b", "2");
			// props.setProperty("c", "3");

			ObjectMapper mapper = new ObjectMapper();
			mapper.registerModule(getModule());
			List<Module> modules = ImmutableList.of(new GuavaModule(), getModule());
			JacksonContext context = JacksonContext.builder().withModules(modules).build();
			JsonService service1 = new JacksonJsonService(context);
			System.out.println(service1.writeString(props));
			 JsonService service2 = new JacksonJsonService();
			System.out.println(service2.writeString(props));
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	protected Module getModule() {
		SimpleModule module = new SimpleModule();
		module.addSerializer(new NestedPropertiesSerializer(Properties.class));
		return module;
	}
}
