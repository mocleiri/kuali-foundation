package org.kuali.common.util.validate.hibernate.programmatic;

import org.junit.Test;

public class SimpleTest {

	@Test
	public void test() {
		try {
			A a = A.builder().withWeight(-1).build();
			System.out.println(a.getWeight());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
