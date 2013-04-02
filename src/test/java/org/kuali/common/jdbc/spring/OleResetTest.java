package org.kuali.common.jdbc.spring;

import org.junit.Test;
import org.kuali.common.util.service.DefaultSpringService;
import org.kuali.common.util.service.SpringService;

public class OleResetTest {

	@Test
	public void test() {
		try {
			SpringService ss = new DefaultSpringService();
			ss.load(OleResetConfig.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
