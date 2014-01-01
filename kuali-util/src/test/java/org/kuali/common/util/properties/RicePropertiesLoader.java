/**
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.common.util.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.UnmarshallerHandler;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.log.LoggerUtils;
import org.kuali.common.util.properties.model.rice.Config;
import org.kuali.common.util.properties.model.rice.Param;
import org.slf4j.Logger;
import org.springframework.util.PropertyPlaceholderHelper;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLFilter;
import org.xml.sax.helpers.XMLFilterImpl;

import com.google.common.base.Preconditions;

/**
 * 
 */
public class RicePropertiesLoader {

	private static final Logger logger = LoggerUtils.make();
	private static final PropertyPlaceholderHelper PPH = new PropertyPlaceholderHelper("${", "}", ":", false);

	public Properties load(String location) {
		Preconditions.checkArgument(!StringUtils.isBlank(location), "'location' cannot be blank");
		Preconditions.checkArgument(LocationUtils.exists(location), "[%s] does not exist", location);
		Unmarshaller unmarshaller = getUnmarshaller();
		Properties properties = new Properties();
		load(location, unmarshaller, 0, properties);
		return properties;
	}

	protected void load(String location, Unmarshaller unmarshaller, int depth, Properties properties) {

		// Setup an indentation prefix based on the recursive depth
		final String prefix = StringUtils.repeat(" ", depth);

		// If we couldn't open an input stream we are done
		if (!LocationUtils.exists(location)) {
			logger.warn("{}+ Skipping non-existent location [{}]", prefix, location);
			return;
		}

		InputStream in = null;
		try {
			in = LocationUtils.getInputStream(location);
			if (isPropertiesFile(location)) {
				logger.info("{}+ Loading - [{}]", prefix, location);
				Properties loaded = new Properties();
				loaded.load(in);
				properties.putAll(loaded);
			} else {
				loadRiceXML(in, prefix, location, depth, unmarshaller, properties);
			}
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

	protected void loadRiceXML(InputStream in, String prefix, String location, int depth, Unmarshaller unmarshaller, Properties properties) throws IOException {
		logger.info("{}+ Loading - [{}]", prefix, location);
		Config config = unmarshal(unmarshaller, in);
		for (Param p : config.getParams()) {
			handleParam(p, depth, unmarshaller, properties, prefix);
		}
	}

	protected void handleParam(Param p, int depth, Unmarshaller unmarshaller, Properties properties, String prefix) {
		if (p.getName().equals("config.location")) {
			String originalLocation = p.getValue();
			String resolvedLocation = getResolvedValue(originalLocation, properties);
			load(resolvedLocation, unmarshaller, depth + 1, properties);
			return;
		}
		checkParam(p);
		boolean doesNotExist = properties.getProperty(p.getName()) == null;
		if (doesNotExist) {
			logger.debug("{}   adding        - [{}]", prefix, p.getName());
			properties.setProperty(p.getName(), p.getValue());
			return;
		}
		if (p.isOverride()) {
			logger.info("{}   overriding     - [{}]", prefix, p.getName());
			properties.setProperty(p.getName(), p.getValue());
		} else {
			logger.info("{}   not overriding - [{}]", prefix, p.getName());
		}
	}

	protected void checkParam(Param param) {
		Preconditions.checkArgument(!param.isRandom(), "Random properties are not supported. [%s]=[%s]", param.getName(), param.getValue());
		Preconditions.checkArgument(!param.isSystem(), "Setting system properties via config files is not supported. [%s]=[%s]", param.getName(), param.getValue());
	}

	protected Unmarshaller getUnmarshaller() {
		try {
			JAXBContext context = JAXBContext.newInstance(Config.class);
			return context.createUnmarshaller();
		} catch (JAXBException e) {
			throw new IllegalStateException("Error initializing JAXB for config", e);
		}
	}

	protected Config unmarshal(Unmarshaller unmarshaller, InputStream in) throws IOException {
		try {
			SAXParserFactory spf = SAXParserFactory.newInstance();
			spf.setNamespaceAware(true);
			XMLFilter filter = new ConfigNamespaceURIFilter();
			filter.setParent(spf.newSAXParser().getXMLReader());
			UnmarshallerHandler handler = unmarshaller.getUnmarshallerHandler();
			filter.setContentHandler(handler);
			filter.parse(new InputSource(in));
			return (Config) handler.getResult();
		} catch (SAXException e) {
			throw new IllegalStateException("Unexpected SAX error", e);
		} catch (ParserConfigurationException e) {
			throw new IllegalStateException("Unexpected parser configuration error", e);
		} catch (JAXBException e) {
			throw new IllegalStateException("Unexpected JAXB error", e);
		}
	}

	protected boolean isPropertiesFile(String location) {
		String lower = StringUtils.lowerCase(location);
		return StringUtils.endsWith(lower, ".properties");
	}

	protected String getResolvedValue(String value, Properties properties) {
		Properties global = PropertyUtils.getGlobalProperties(properties);
		return PPH.replacePlaceholders(value, global);
	}

	protected class ConfigNamespaceURIFilter extends XMLFilterImpl {

		public static final String CONFIG_URI = "http://rice.kuali.org/core/impl/config";

		@Override
		public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
			if (StringUtils.isBlank(uri)) {
				uri = CONFIG_URI;
			}
			super.startElement(uri, localName, qName, atts);
		}

		@Override
		public void endElement(String uri, String localName, String qName) throws SAXException {
			if (StringUtils.isBlank(uri)) {
				uri = CONFIG_URI;
			}
			super.endElement(uri, localName, qName);
		}
	}

}
