package org.kuali.common.util.spring;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class ToStringFactoryBeanTest {

	@Autowired
	private String password;

	@Test
	public void test() {
		Assert.assertNotNull("password is null.", password);
		Assert.assertEquals(password, "foo");
	}

}
