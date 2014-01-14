/**
 * Copyright 2010-2013 The Kuali Foundation
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
package org.kuali.common.util.log.log4j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.UnmarshallerHandler;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.common.util.log.log4j.model.Log4JConfiguration;
import org.kuali.common.util.log.log4j.spring.Log4JConfig;
import org.kuali.common.util.project.model.Project;
import org.kuali.common.util.project.spring.KualiUtilProjectConfig;
import org.kuali.common.util.xml.service.XmlService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { KualiUtilProjectConfig.class, Log4JConfig.class })
public class Log4JTestConfig {

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Log4JTestConfig.class);

	@Autowired
	Log4JService service;

	@Autowired
	XmlService xmlService;

	@Autowired
	Log4JConfig config;

	@Autowired
	Project project;

	@Test
	public void test() {
		try {
			Log4JConfiguration original = config.log4JContextMaven();
			String xml1 = service.toXml(original);
			System.out.println(xml1);
			Log4JConfiguration derived = xmlService.getObjectFromXml(xml1, "UTF-8", Log4JConfiguration.class);
			String xml2 = service.toXml(derived);
			System.out.println(xml2);
			Assert.assertEquals(xml1, xml2);
			logger.info("old logging configuration");
			service.configure(derived);
			logger.info("new logging configuration");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String toXml(Object object) throws Exception {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		write(out, object);
		return out.toString("UTF-8");
	}

	public void write(OutputStream out, Object object) throws Exception {
		JAXBContext context = JAXBContext.newInstance(object.getClass());
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		marshaller.marshal(object, out);
	}

	public <T> T getObject(String s, Class<T> type) throws Exception {
		ByteArrayInputStream in = new ByteArrayInputStream(s.getBytes("UTF-8"));
		return getObject(in, type);
	}

	@SuppressWarnings("unchecked")
	public <T> T getObject(InputStream in, Class<T> type) throws Exception {
		JAXBContext jc = JAXBContext.newInstance(type);
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		UnmarshallerHandler unmarshallerHandler = unmarshaller.getUnmarshallerHandler();

		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser sp = spf.newSAXParser();
		XMLReader xr = sp.getXMLReader();
		xr.setContentHandler(unmarshallerHandler);
		InputSource xmlSource = new InputSource(in);
		xr.parse(xmlSource);
		return (T) unmarshallerHandler.getResult();
	}
}
