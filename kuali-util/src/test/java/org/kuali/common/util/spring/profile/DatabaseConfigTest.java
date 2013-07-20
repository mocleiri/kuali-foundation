package org.kuali.common.util.spring.profile;

import org.junit.Test;
import org.kuali.common.util.service.DefaultSpringService;
import org.kuali.common.util.service.SpringService;

public class DatabaseConfigTest {

	@Test
	public void test() {
		try {
			SpringService ss = new DefaultSpringService();
			ss.load(ShowDatabaseExecutableConfig.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
