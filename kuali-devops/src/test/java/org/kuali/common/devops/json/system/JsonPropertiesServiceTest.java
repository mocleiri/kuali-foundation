package org.kuali.common.devops.json.system;

import java.util.Properties;

import org.junit.Test;

public class JsonPropertiesServiceTest {

	@Test
	public void test() {
		try {
			Properties props = new Properties();
			props.setProperty("foo.bar", "baz");
			JsonPropertiesService service = new JsonPropertiesService();
			String json = service.getJson(props);
			System.out.println(json);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
