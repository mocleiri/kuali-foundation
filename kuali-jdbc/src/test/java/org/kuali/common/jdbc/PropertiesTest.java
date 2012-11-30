package org.kuali.common.jdbc;

import java.util.Properties;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class PropertiesTest {

	@Autowired
	Properties properties = null;

	@Test
	public void resetDatabaseTest() {
		System.out.println(properties.size());
	}
}
