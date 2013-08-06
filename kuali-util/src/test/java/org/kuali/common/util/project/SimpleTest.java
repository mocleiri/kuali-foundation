package org.kuali.common.util.project;

import junit.framework.Assert;

import org.junit.Test;
import org.kuali.common.util.project.model.ProjectId;

public class SimpleTest {

	@Test
	public void test() {
		try {
			ProjectId p1 = new ProjectId("org.kuali.common", "kuali-util");
			ProjectId p2 = new ProjectId("org.kuali.common", "kuali-util");
			ProjectId p3 = new ProjectId("org.kuali.common", "kuali-jdbc");
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
