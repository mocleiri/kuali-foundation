package org.kuali.common.util.spring.car;

import org.junit.Test;
import org.kuali.common.util.service.DefaultSpringService;
import org.kuali.common.util.service.SpringService;

public class CarConfigTest {

	@Test
	public void test() {
		try {
			SpringService ss = new DefaultSpringService();
			ss.load(CompositeConfig.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
