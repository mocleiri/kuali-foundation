package org.kuali.common.util.spring.car;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CompositeConfig.class)
public class CarConfigTest {

	@Autowired
	ApplicationContext ctx;

	@Test
	public void test() {
		System.out.println("yo");
	}

}
