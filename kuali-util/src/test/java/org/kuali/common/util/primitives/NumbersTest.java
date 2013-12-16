package org.kuali.common.util.primitives;

import org.junit.Assert;
import org.junit.Test;

public class NumbersTest {

	@Test
	public void test() {
		Assert.assertEquals(Byte.class, Numbers.narrow(0L).getClass());
		Assert.assertEquals(Byte.class, Numbers.narrow(Byte.MAX_VALUE).getClass());
		Assert.assertEquals(Byte.class, Numbers.narrow(Byte.MIN_VALUE).getClass());
		Assert.assertEquals(Short.class, Numbers.narrow(Short.MAX_VALUE).getClass());
		Assert.assertEquals(Short.class, Numbers.narrow(Short.MIN_VALUE).getClass());
		Assert.assertEquals(Integer.class, Numbers.narrow(Integer.MAX_VALUE).getClass());
		Assert.assertEquals(Integer.class, Numbers.narrow(Integer.MIN_VALUE).getClass());
		Assert.assertEquals(Long.class, Numbers.narrow(Long.MAX_VALUE).getClass());
		Assert.assertEquals(Long.class, Numbers.narrow(Long.MIN_VALUE).getClass());

		Assert.assertEquals((byte) 0, Numbers.narrow(0L));
		Assert.assertEquals(Byte.MAX_VALUE, Numbers.narrow(Byte.MAX_VALUE));
		Assert.assertEquals(Byte.MIN_VALUE, Numbers.narrow(Byte.MIN_VALUE));
		Assert.assertEquals(Short.MAX_VALUE, Numbers.narrow(Short.MAX_VALUE));
		Assert.assertEquals(Short.MIN_VALUE, Numbers.narrow(Short.MIN_VALUE));
		Assert.assertEquals(Integer.MAX_VALUE, Numbers.narrow(Integer.MAX_VALUE));
		Assert.assertEquals(Integer.MIN_VALUE, Numbers.narrow(Integer.MIN_VALUE));
		Assert.assertEquals(Long.MAX_VALUE, Numbers.narrow(Long.MAX_VALUE));
		Assert.assertEquals(Long.MIN_VALUE, Numbers.narrow(Long.MIN_VALUE));
	}
}
