package org.kuali.common.util.spring.test;

import org.junit.Test;
import org.kuali.common.util.service.DefaultSpringService;
import org.kuali.common.util.service.SpringService;
import org.kuali.common.util.spring.config.CarConfig;

public class CarConfigTest {

	@Test
	public void test() {
		try {
			SpringService ss = new DefaultSpringService();
			ss.load(CarConfig.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
