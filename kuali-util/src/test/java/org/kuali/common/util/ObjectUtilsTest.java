package org.kuali.common.util;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;

import org.junit.Test;
import org.kuali.common.util.project.model.ProjectIdentifier;

public class ObjectUtilsTest {

	@Test
	public void testNotEqual() {
		try {
			ObjectUtils.notEqual(null, null);
			fail("Should have thrown NPE");
		} catch (NullPointerException e) {
			// ignore
		}
		assertTrue(ObjectUtils.notEqual(new Object(), null));
		assertTrue(ObjectUtils.notEqual(new Object(), new String()));
		assertFalse(ObjectUtils.notEqual("", ""));

		ProjectIdentifier p1 = new ProjectIdentifier("org.kuali.common", "kuali-util");
		ProjectIdentifier p2 = new ProjectIdentifier("org.kuali.common", "kuali-util");
		assertFalse(ObjectUtils.notEqual(p1, p2));
	}

}
