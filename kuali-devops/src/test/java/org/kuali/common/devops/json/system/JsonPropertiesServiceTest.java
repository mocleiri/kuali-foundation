package org.kuali.common.devops.json.system;

import java.util.Properties;

import org.junit.Test;
import org.kuali.common.devops.json.pojo.JacksonJsonService;
import org.kuali.common.devops.json.pojo.JsonService;

public class JsonPropertiesServiceTest {

	@Test
	public void test() {
		try {
			Properties props = new Properties();
			props.setProperty("foo.bar", "baz");
			NestedPropertiesSerializer service = new NestedPropertiesSerializer();
			System.out.println(service.getJson(props));
			JsonService json = new JacksonJsonService();
			System.out.println(json.writeString(props));
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
