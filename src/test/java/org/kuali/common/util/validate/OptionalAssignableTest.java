package org.kuali.common.util.validate;

import java.util.Map;

import org.junit.Test;
import org.kuali.common.util.ReflectionUtils;

import com.google.common.base.Optional;

public class OptionalAssignableTest {

	@Test
	public void test() {
		try {
			Optional<String> absent = Optional.absent();
			Optional<String> present = Optional.of("foo");
			System.out.println(" absent = " + absent.getClass().getCanonicalName());
			System.out.println("present = " + present.getClass().getCanonicalName());
			System.out.println(" absent instanceof Optional is " + (absent instanceof Optional));
			System.out.println("present instanceof Optional is " + (present instanceof Optional));
			System.out.println(" absent.getClass() == Optional.class is " + (absent.getClass() == Optional.class));
			System.out.println("present.getClass() == Optional.class is " + (present.getClass() == Optional.class));
			System.out.println("Optional.class.isAssignableFrom(present.getClass()) is " + (Optional.class.isAssignableFrom(present.getClass())));
			System.out.println("Optional.class.isAssignableFrom(absent.getClass())  is " + (Optional.class.isAssignableFrom(absent.getClass())));

			System.out.println();
			System.out.println(ReflectionUtils.isImmutableGuavaMap(Map.class));
			System.out.println();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
