package org.kuali.common.util;

import java.util.Properties;

import org.junit.Assert;
import org.junit.Test;

public class PropertyUtilsTest {

	@Test
	public void getXMLPropertiesTest() {
		Properties props = PropertyUtils.getProperties("classpath:org/kuali/common/util/properties.xml");
		String foo = props.getProperty("foo");
		Assert.assertEquals("bar", foo);
	}

	@Test
	public void getPropertiesTest() {
		Properties props = PropertyUtils.getProperties("classpath:org/kuali/common/util/simple.properties");
		String foo = props.getProperty("foo");
		Assert.assertEquals("bar", foo);
	}

	@Test
	public void getPropertiesUTF8Test() {
		Properties props = PropertyUtils.getProperties("classpath:org/kuali/common/util/simple.properties", "UTF-8");
		String foo = props.getProperty("foo");
		Assert.assertEquals("bar", foo);
	}
}
