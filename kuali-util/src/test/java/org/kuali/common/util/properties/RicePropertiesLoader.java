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
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.Str;
import org.kuali.common.util.log.LoggerUtils;
import org.kuali.common.util.properties.model.rice.Config;
import org.kuali.common.util.properties.model.rice.Param;
import org.slf4j.Logger;
import org.springframework.util.PropertyPlaceholderHelper;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import com.google.common.base.Optional;
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

		// If the location does not exist, we are done
		if (!LocationUtils.exists(location)) {
			logger.warn("{}+ Skipping non-existent location [{}]", prefix, location);
			return;
		}

		InputStream in = null;
		try {
			in = LocationUtils.getInputStream(location);
			load(location, unmarshaller, depth, properties, in, prefix);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

	protected void load(String location, Unmarshaller unmarshaller, int depth, Properties properties, InputStream in, String prefix) throws IOException {
		if (isPropertiesFile(location)) {
			logger.info("{}+ Loading - [{}]", prefix, location);
			Properties loaded = new Properties();
			loaded.load(in);
			properties.putAll(loaded);
		} else {
			loadRiceXML(in, prefix, location, depth, unmarshaller, properties);
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

		// This is a reference to a nested config file
		if (p.getName().equalsIgnoreCase("config.location")) {
			String originalLocation = p.getValue();
			String resolvedLocation = getResolvedValue(originalLocation, properties);
			load(resolvedLocation, unmarshaller, depth + 1, properties);
			return;
		}

		// Random and system attributes are not supported
		checkParam(p);

		// Extract the old value (it it's present)
		Optional<String> oldValue = Optional.fromNullable(properties.getProperty(p.getName()));

		// If there is no previous value, just add it
		if (!oldValue.isPresent()) {
			Object[] args = { prefix, p.getName(), Str.flatten(p.getValue()) };
			logger.debug("{}   adding        - [{}]=[{}]", args);
			properties.setProperty(p.getName(), p.getValue());
			return;
		}

		// The new value is the same as the old value. Nothing more to do
		if (oldValue.get().equals(p.getValue())) {
			Object[] args = { prefix, p.getName(), Str.flatten(p.getValue()) };
			logger.debug("{}   duplicate        - [{}]=[{}]", args);
			return;
		}

		// There is a new value for this property that is different from the old value
		Object[] args = { prefix, p.getName(), Str.flatten(oldValue.get()), Str.flatten(p.getValue()) };
		if (p.isOverride()) {
			// Change it, and log the fact that we are changing it
			logger.info("{}   overriding     - [{}]=[{}] -> [{}]", args);
			properties.setProperty(p.getName(), p.getValue());
		} else {
			// Don't change it, and log the fact that we are ignoring the new value from the config file
			logger.info("{}   not overriding - [{}]=[{}] -> Ignoring new value [{}]", args);
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
			UnmarshallerHandler unmarshallerHandler = unmarshaller.getUnmarshallerHandler();
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();
			XMLReader xr = sp.getXMLReader();
			xr.setContentHandler(unmarshallerHandler);
			InputSource xmlSource = new InputSource(in);
			xr.parse(xmlSource);
			return (Config) unmarshallerHandler.getResult();
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

}
