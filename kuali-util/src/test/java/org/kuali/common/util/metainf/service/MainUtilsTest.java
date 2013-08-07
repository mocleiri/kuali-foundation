package org.kuali.common.util.metainf.service;

import org.junit.Test;
import org.kuali.common.util.spring.main.MainUtils;

public class MainUtilsTest {

	@Test
	public void test() {
		try {
			MainUtils.runAndExit(MainUtilsTest.class, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
