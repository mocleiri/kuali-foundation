package org.kuali.common.util.project;

import org.junit.Test;

public class SimpleTest {

	@Test
	public void test() {
		try {
			ProjectIdentifier p1 = new ImmutableProjectIdentifier("org.kuali.common", "kuali-util");
			ProjectIdentifier p2 = new ImmutableProjectIdentifier("org.kuali.common", "kuali-util");
			System.out.println(p1);
			System.out.println(p2);
			System.out.println(p1.equals(p2));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
