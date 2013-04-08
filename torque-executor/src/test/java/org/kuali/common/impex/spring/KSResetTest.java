package org.kuali.common.impex.spring;

import org.junit.Test;
import org.kuali.common.util.service.DefaultSpringService;
import org.kuali.common.util.service.SpringService;

public class KSResetTest {

	@Test
	public void test() {
		try {
			SpringService ss = new DefaultSpringService();
			ss.load(KSResetConfig.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
