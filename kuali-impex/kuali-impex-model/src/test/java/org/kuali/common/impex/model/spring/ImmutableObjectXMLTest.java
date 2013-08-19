package org.kuali.common.impex.model.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.common.util.xml.XmlService;
import org.kuali.common.util.xml.spring.XmlServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = XmlServiceConfig.class)
public class ImmutableObjectXMLTest {

	@Autowired
	XmlService service;

	@Test
	public void test() {
		try {
			System.out.println("hello world");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
