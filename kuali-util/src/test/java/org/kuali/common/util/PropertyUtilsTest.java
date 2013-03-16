/**
 * Copyright 2010-2012 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.common.util;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.jasypt.util.text.TextEncryptor;
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

	@Test
	public void encryptTest() {
		TextEncryptor crypto = EncUtils.getTextEncryptor(EncryptionStrength.BASIC, password)
		Properties props = new Properties();
		props.setProperty("foo", "bar");
		String foo = props.getProperty("foo");
		Assert.assertEquals("bar", foo);
	}
}
