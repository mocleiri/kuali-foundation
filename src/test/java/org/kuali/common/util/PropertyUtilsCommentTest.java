package org.kuali.common.util;

import org.junit.Test;

public class PropertyUtilsCommentTest {

	@Test
	public void test() {
		System.out.println(PropertyUtils.getComment(null, null, true));
		System.out.println(PropertyUtils.getComment(null, null, false));
		System.out.println(PropertyUtils.getComment(null, "MacRoman", false));
		System.out.println(PropertyUtils.getComment(null, "MacRoman", true));
		System.out.println(PropertyUtils.getComment("Hi", null, false));
		System.out.println(PropertyUtils.getComment("Hi", null, true));
		System.out.println(PropertyUtils.getComment("Hi", "MacRoman", false));
		System.out.println(PropertyUtils.getComment("Hi", "MacRoman", true));
	}
}
