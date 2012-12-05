package org.kuali.common.util;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class PropertyUtilsTest {
	private static final Logger logger = LoggerFactory.getLogger(PropertyUtilsTest.class);

	@Autowired
	private String buildDir = null;

	String location = "classpath:org/kuali/common/util/simple.properties";
	String xmlLocation = "classpath:org/kuali/common/util/simple.xml";
	String encoding = "UTF-8";

	protected File getTestDir() {
		return new File(buildDir + File.separator + "properties");
	}

	@Test
	public void testBuildDir() throws IOException {
		Assert.assertNotNull(buildDir);
		logger.info("Creating " + getTestDir());
		FileUtils.forceMkdir(getTestDir());
	}

	@Test
	public void storeUTF8XMLPropertiesTest() throws IOException {
		File temp = File.createTempFile("temporary.", ".xml", getTestDir());
		Properties props = PropertyUtils.load(xmlLocation);
		String foo = props.getProperty("foo");
		Assert.assertEquals("bar", foo);
		PropertyUtils.store(props, temp, encoding);
	}

	@Test
	public void storeXMLPropertiesTest() throws IOException {
		File temp = File.createTempFile("temporary.", ".xml", getTestDir());
		temp.deleteOnExit();
		Properties props = PropertyUtils.load(xmlLocation);
		String foo = props.getProperty("foo");
		Assert.assertEquals("bar", foo);
		PropertyUtils.store(props, temp);
	}

	@Test
	public void storeUTF8PropertiesTest() throws IOException {
		File temp = File.createTempFile("temporary.", ".properties", getTestDir());
		temp.deleteOnExit();
		Properties props = PropertyUtils.load(xmlLocation);
		String foo = props.getProperty("foo");
		Assert.assertEquals("bar", foo);
		PropertyUtils.store(props, temp, encoding);
	}

	@Test
	public void storePropertiesTest() throws IOException {
		File temp = File.createTempFile("temporary.", ".properties", getTestDir());
		temp.deleteOnExit();
		Properties props = PropertyUtils.load(xmlLocation);
		String foo = props.getProperty("foo");
		Assert.assertEquals("bar", foo);
		PropertyUtils.store(props, temp);
	}

	@Test
	public void getXMLPropertiesTest() {
		Properties props = PropertyUtils.load(xmlLocation);
		String foo = props.getProperty("foo");
		Assert.assertEquals("bar", foo);
	}

	@Test
	public void getPropertiesTest() {
		Properties props = PropertyUtils.load(location);
		String foo = props.getProperty("foo");
		Assert.assertEquals("bar", foo);
	}

	@Test
	public void getPropertiesUTF8Test() {
		Properties props = PropertyUtils.load(location, "UTF-8");
		String foo = props.getProperty("foo");
		Assert.assertEquals("bar", foo);
	}
}
