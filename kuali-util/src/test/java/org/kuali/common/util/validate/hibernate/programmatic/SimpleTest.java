package org.kuali.common.util.validate.hibernate.programmatic;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.kuali.common.util.validate.NoNullFields;
import org.kuali.common.util.validate.ValidationUtils;

public class SimpleTest {

	@Test
	@Ignore
	public void testIsConstraint() {
		boolean condition = ValidationUtils.isConstraint(Foo.class.getAnnotation(NoNullFields.class));
		Assert.assertTrue(NoNullFields.class + " is an annotation", condition);
	}

	@Test
	public void testGetClassLevelAnnotations() {
		try {
			Foo a = Foo.builder().withWeight(1).withFoo("bar").build();
			List<Annotation> constraints = ValidationUtils.getConstraints(a.getClass());
			Assert.assertTrue("should be exactly one constraint", constraints.size() == 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	@Ignore
	public void testGetFieldLevelAnnotations() throws Exception {
		Foo a = Foo.builder().withWeight(1).withFoo("bar").build();
		Field field = a.getClass().getDeclaredField("weight");
		List<Annotation> constraints = ValidationUtils.getConstraints(field);
		Assert.assertTrue("should be exactly one constraint", constraints.size() == 1);
	}

	@Test
	@Ignore
	public void test() {
		try {
			int negativeWeight = -1;
			Foo a = Foo.builder().withWeight(negativeWeight).build();
			System.out.println("a.weight=" + a.getWeight());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
