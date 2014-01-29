package org.kuali.common.aws.status;

import org.junit.Test;

public class GetStatusTest {

	@Test
	public void test() {
		try {
			String password = Passwords.getEncPassword();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
