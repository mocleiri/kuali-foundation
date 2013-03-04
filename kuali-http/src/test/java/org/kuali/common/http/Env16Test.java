package org.kuali.common.http;

import org.junit.Test;
import org.kuali.common.util.service.DefaultSpringService;
import org.kuali.common.util.service.SpringService;

public class Env16Test {

	@Test
	// @Ignore
	public void test() {
		try {
			SpringService ss = new DefaultSpringService();
			ss.load("classpath:org/kuali/common/http/env16-wait-context.xml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
