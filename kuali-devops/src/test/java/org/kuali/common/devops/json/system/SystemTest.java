package org.kuali.common.devops.json.system;

import static org.kuali.common.util.PropertyUtils.newTreeMap;

import org.junit.Test;
import org.kuali.common.devops.json.pojo.JacksonJsonService;
import org.kuali.common.devops.json.pojo.JsonService;

public class SystemTest {

	@Test
	public void test() {
		try {
			JsonService service = new JacksonJsonService();
			String json = service.writeString(newTreeMap(System.getProperties()));
			System.out.println(json);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}
