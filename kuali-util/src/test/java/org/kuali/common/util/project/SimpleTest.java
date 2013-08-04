package org.kuali.common.util.project;

import junit.framework.Assert;

import org.junit.Test;

public class SimpleTest {

	@Test
	public void test() {
		try {
			ProjectIdentifier p1 = new ImmutableProjectIdentifier("org.kuali.common", "kuali-util");
			ProjectIdentifier p2 = new ImmutableProjectIdentifier("org.kuali.common", "kuali-util");
			ProjectIdentifier p3 = new ImmutableProjectIdentifier("org.kuali.common", "kuali-jdbc");
			System.out.println(p1.hashCode());
			System.out.println(p2.hashCode());
			System.out.println(p3.hashCode());
			Assert.assertTrue(p1.equals(p2));
			Assert.assertFalse(p1.equals(p3));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
