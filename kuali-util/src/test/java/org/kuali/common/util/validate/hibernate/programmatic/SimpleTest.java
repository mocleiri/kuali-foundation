package org.kuali.common.util.validate.hibernate.programmatic;

import org.junit.Assert;
import org.junit.Test;
import org.kuali.common.util.validate.NoNullFields;
import org.kuali.common.util.validate.ValidationUtils;

public class SimpleTest {

	@Test
	public void testIsConstraint() {
		boolean condition = ValidationUtils.isConstraint(A.class.getAnnotation(NoNullFields.class));
		Assert.assertTrue(NoNullFields.class + " is an annotation", condition);
	}

	@Test
	public void test() {
		try {
			int negativeWeight = -1;
			A a = A.builder().withWeight(negativeWeight).build();
			System.out.println("a.weight=" + a.getWeight());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
