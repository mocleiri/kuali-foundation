package org.kuali.common.util.spring.env;

import java.util.Properties;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.core.env.Environment;

public class DefaultEnvironmentServiceTest {

	@Test
	public void testGetEnvironmentVariableKey() {
		Properties properties = new Properties();
		Environment env = new PropertiesEnvironment(properties);
		DefaultEnvironmentService des = new DefaultEnvironmentService(env);
		try {
			Assert.assertEquals("env.FOO_BAR", des.getEnvironmentVariableKey("foo.bar"));
			Assert.assertEquals("env.FOO_BAR_BAZ", des.getEnvironmentVariableKey("foo.barBaz"));
			Assert.assertEquals("env.FOO_BAR_BAZ", des.getEnvironmentVariableKey("foo.BarBaz"));
			Assert.assertEquals("env.FOO_BAR_BBAZ", des.getEnvironmentVariableKey("foo.BarBBaz"));
			Assert.assertEquals("env.AWS_ACCESS_KEY_ID", des.getEnvironmentVariableKey("aws.accessKeyId"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
