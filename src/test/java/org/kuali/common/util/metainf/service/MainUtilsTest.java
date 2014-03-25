package org.kuali.common.util.metainf.service;

import org.junit.Test;

public class MainUtilsTest {

	@Test
	public void test() {
		try {
			SimpleMain.main(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
