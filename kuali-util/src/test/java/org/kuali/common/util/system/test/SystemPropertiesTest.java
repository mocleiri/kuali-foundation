package org.kuali.common.util.system.test;

import org.junit.Test;
import org.kuali.common.util.spring.service.DefaultSpringService;
import org.kuali.common.util.spring.service.SpringService;
import org.kuali.common.util.system.spring.DefaultSystemConfig;

public class SystemPropertiesTest {

	@Test
	public void test() {
		try {
			SpringService ss = new DefaultSpringService();
			ss.load(DefaultSystemConfig.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
