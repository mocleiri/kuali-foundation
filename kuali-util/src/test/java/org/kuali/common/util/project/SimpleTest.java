package org.kuali.common.util.project;

import junit.framework.Assert;

import org.junit.Test;
import org.kuali.common.util.project.model.ProjectIdentifier;

public class SimpleTest {

	@Test
	public void test() {
		ProjectIdentifier p1 = new ProjectIdentifier("org.kuali.common", "kuali-util");
		ProjectIdentifier p2 = new ProjectIdentifier("org.kuali.common", "kuali-util");
		ProjectIdentifier p3 = new ProjectIdentifier("org.kuali.common", "kuali-jdbc");
		System.out.println(p1.hashCode());
		System.out.println(p2.hashCode());
		System.out.println(p3.hashCode());
		Assert.assertTrue(p1.equals(p2));
		Assert.assertFalse(p1.equals(p3));
	}

}
