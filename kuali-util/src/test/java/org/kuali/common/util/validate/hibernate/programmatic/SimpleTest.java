package org.kuali.common.util.validate.hibernate.programmatic;

import org.junit.Test;

public class SimpleTest {

	@Test
	public void test() {
		try {
			A c = A.builder().build();
			System.out.println(c.getFoo());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
