package org.kuali.common.jdbc.service.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DatabaseVendorsPropertySourceConfig.class)
public class DatabaseVendorsConfigTest {

	@Autowired
	PropertySource<?> propertySource;

	@Test
	public void test() {
		try {
			System.out.println(propertySource);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
