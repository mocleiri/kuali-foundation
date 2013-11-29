package org.kuali.common.util.spring.env;

import junit.framework.Assert;

import org.junit.Test;

public class EnvUtilsTest {

	@Test
	public void testGetEnvironmentVariableKey() {
		Assert.assertEquals("env.FOO_BAR", EnvUtils.getEnvironmentVariableKey("foo.bar"));
		Assert.assertEquals("env.FOO_BAR_BAZ", EnvUtils.getEnvironmentVariableKey("foo.barBaz"));
		Assert.assertEquals("env.FOO_BAR_BAZ", EnvUtils.getEnvironmentVariableKey("foo.BarBaz"));
		Assert.assertEquals("env.FOO_BAR_BBAZ", EnvUtils.getEnvironmentVariableKey("foo.BarBBaz"));
		Assert.assertEquals("env.AWS_ACCESS_KEY_ID", EnvUtils.getEnvironmentVariableKey("aws.accessKeyId"));
	}

}
