package org.kuali.db.jdbc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.spring.util.PropertiesPlaceholderConfigurer;
import org.kuali.spring.util.PropertyLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class DBATest {
	@Autowired
	private ApplicationContext applicationContext;

	@Test
	public void importTest() {
		PropertyLogger plogger = (PropertyLogger) applicationContext.getBean("kuali.plogger");
		System.out.println(plogger.isFlattenPropertyValues());
		PropertiesPlaceholderConfigurer pphc = (PropertiesPlaceholderConfigurer) applicationContext
				.getBean("kuali.pconfigurer");
		PropertyLogger plogger2 = pphc.getLoader().getPlogger();
		System.out.println(plogger2.isFlattenPropertyValues());

	}
}
