package org.kuali.common.util;

import java.io.File;
import java.util.Properties;

import org.junit.Assert;
import org.junit.Test;

public class PropertyUtilsTest {
	String location = "classpath:org/kuali/common/util/simple.properties";
	String xmlLocation = "classpath:org/kuali/common/util/simple.xml";
	String encoding = "UTF-8";

	@Test
	public void storeUTF8XMLPropertiesTest() {
		try {
			File temp = File.createTempFile("temporary.", ".xml");
			temp.deleteOnExit();
			Properties props = PropertyUtils.getProperties(xmlLocation);
			String foo = props.getProperty("foo");
			Assert.assertEquals("bar", foo);
			PropertyUtils.store(props, temp, encoding);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void storeXMLPropertiesTest() {
		try {
			File temp = File.createTempFile("temporary.", ".xml");
			temp.deleteOnExit();
			Properties props = PropertyUtils.getProperties(xmlLocation);
			String foo = props.getProperty("foo");
			Assert.assertEquals("bar", foo);
			PropertyUtils.store(props, temp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void storeUTF8PropertiesTest() {
		try {
			File temp = File.createTempFile("temporary.", ".properties");
			temp.deleteOnExit();
			Properties props = PropertyUtils.getProperties(xmlLocation);
			String foo = props.getProperty("foo");
			Assert.assertEquals("bar", foo);
			PropertyUtils.store(props, temp, encoding);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void storePropertiesTest() {
		try {
			File temp = File.createTempFile("temporary.", ".properties");
			temp.deleteOnExit();
			Properties props = PropertyUtils.getProperties(xmlLocation);
			String foo = props.getProperty("foo");
			Assert.assertEquals("bar", foo);
			PropertyUtils.store(props, temp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void getXMLPropertiesTest() {
		Properties props = PropertyUtils.getProperties(xmlLocation);
		String foo = props.getProperty("foo");
		Assert.assertEquals("bar", foo);
	}

	@Test
	public void getPropertiesTest() {
		Properties props = PropertyUtils.getProperties(location);
		String foo = props.getProperty("foo");
		Assert.assertEquals("bar", foo);
	}

	@Test
	public void getPropertiesUTF8Test() {
		Properties props = PropertyUtils.getProperties(location, "UTF-8");
		String foo = props.getProperty("foo");
		Assert.assertEquals("bar", foo);
	}
}
