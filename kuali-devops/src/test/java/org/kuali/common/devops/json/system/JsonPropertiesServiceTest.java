package org.kuali.common.devops.json.system;

import org.junit.Test;

public class JsonPropertiesServiceTest {

	@Test
	public void test() {
		try {
			JsonPropertiesService service = new JsonPropertiesService();
			String json = service.getJson(System.getProperties());
			System.out.println(json);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
