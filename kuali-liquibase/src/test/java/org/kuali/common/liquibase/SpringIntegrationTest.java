package org.kuali.common.liquibase;

import liquibase.integration.spring.SpringLiquibase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DefaultSpringLiquibaseConfig.class })
public class SpringIntegrationTest {

	@Autowired
	SpringLiquibase springLiquibase;

	@Test
	public void test() {
		try {
			System.out.println("hello world");
			springLiquibase.afterPropertiesSet();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
