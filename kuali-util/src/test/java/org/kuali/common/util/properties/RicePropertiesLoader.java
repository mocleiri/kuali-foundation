/**
 * Copyright 2005-2013 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you cannot use this file except in compliance with the License.
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
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

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
import org.kuali.common.util.obscure.DefaultObscurer;
import org.kuali.common.util.obscure.Obscurer;
import org.kuali.common.util.properties.model.rice.Config;
import org.kuali.common.util.properties.model.rice.Param;
import org.slf4j.Logger;
import org.springframework.util.PropertyPlaceholderHelper;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;

/**
 * <p>
 * Load Rice XML config files. This class supports the chaining of config files together via {@code config.location} param entries. It also honors the {@code override} attribute on
 * param entries. It does not support the {@code system} attribute. If that attribute is present anywhere in any config file, an exception is thrown.
 * </p>
 * 
 * <p>
 * The purpose of this class is to decouple the loading of property files from everything else and produce a plain vanilla {@code java.util.Properties} object that accurately
 * reflects the contents of the files as a simple object in memory.
 * </p>
 * 
 * <p>
 * No placeholder resolution is attempted on parameter entries with the exception of {@code config.location} entries. During the loading process, {@code config.location} entries
 * that contain placeholders, are resolved using system and environment variables as well as any properties that have already been loaded.
 * </p>
 */
public class RicePropertiesLoader {

	private static final Logger logger = LoggerUtils.make();
	private static final Random RANDOM = new Random();

	private final PropertyPlaceholderHelper propertyPlaceholderHelper;
	private final String magicNestedConfigKey;
	private final List<String> obscureTokens;
	private final Obscurer obscurer;
	private final boolean ignoreUnresolvablePlaceholdersInConfigLocationValues;

	public Properties load(String location) {
		Preconditions.checkArgument(!StringUtils.isBlank(location), "'location' cannot be blank");
		Preconditions.checkArgument(LocationUtils.exists(location), "[%s] does not exist", location);
		Unmarshaller unmarshaller = getUnmarshaller();
		Map<String, Param> params = Maps.newHashMap();
		load(location, unmarshaller, 0, params);
		randomize(params);
		Properties properties = convert(params);
		return properties;
	}

	protected void load(String location, Unmarshaller unmarshaller, int depth, Map<String, Param> params) {

		// Setup an indentation prefix based on the recursive depth
		final String prefix = StringUtils.repeat(" ", depth);

		// If the location does not exist, we are done
		if (!LocationUtils.exists(location)) {
			logger.warn("{}# skip non-existent location [{}]", prefix, location);
			return;
		}

		InputStream in = null;
		try {
			in = LocationUtils.getInputStream(location);
			load(prefix, location, in, params, depth, unmarshaller);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

	protected void load(String prefix, String location, InputStream in, Map<String, Param> params, int depth, Unmarshaller unmarshaller) throws IOException {
		if (isPropertiesFile(location)) {
			loadJavaProperties(prefix, location, in, params, depth);
		} else {
			loadRiceProperties(prefix, location, in, params, depth, unmarshaller);
		}
	}

	protected void loadRiceProperties(String prefix, String location, InputStream in, Map<String, Param> params, int depth, Unmarshaller unmarshaller) throws IOException {
		logger.info("{}+ loading - [{}]", prefix, location);
		Config config = unmarshal(unmarshaller, in);
		for (Param p : config.getParams()) {
			handleParam(p, depth, unmarshaller, params, prefix);
		}
		logger.info("{}- loaded  - [{}]", prefix, location);
	}

	protected void handleParam(Param p, int depth, Unmarshaller unmarshaller, Map<String, Param> params, String prefix) {

		// This is a reference to a nested config file
		if (p.getName().equalsIgnoreCase(magicNestedConfigKey)) {
			String originalLocation = p.getValue();
			String resolvedLocation = getResolvedValue(originalLocation, params);
			load(resolvedLocation, unmarshaller, depth + 1, params);
			return;
		}

		// Random and system attributes are not supported
		checkParam(p);

		// Update the properties object with this parameter
		update(params, p, prefix);

	}

	protected void loadJavaProperties(String prefix, String location, InputStream in, Map<String, Param> params, int depth) throws IOException {
		logger.info("{}+ loading - [{}]", prefix, location);
		Properties loaded = new Properties();
		loaded.load(in);
		Map<String, Param> newMap = getParamMap(loaded);
		for (Param p : newMap.values()) {
			update(params, p, prefix);
		}
		logger.info("{}- loaded  - [{}]", prefix, location);
	}

	protected void update(Map<String, Param> params, Param p, String prefix) {
		Preconditions.checkNotNull(p.getValue(), "parameter value cannot be null");

		// Extract the old value (if it's present)
		Optional<Param> oldParam = Optional.fromNullable(params.get(p.getName()));

		// Get a log friendly value
		String newLogValue = getLogValue(p);

		// If there is no previous value, just add it
		if (!oldParam.isPresent()) {
			Object[] args = { prefix, p.getName(), newLogValue };
			logger.debug("{}~ add - [{}]=[{}]", args);
			params.put(p.getName(), p);
			return;
		}

		// The new value is the same as the old value. Nothing more to do
		if (oldParam.get().getValue().equals(p.getValue())) {
			Object[] args = { prefix, p.getName(), newLogValue };
			logger.debug("{}~ duplicate - [{}]=[{}]", args);
			return;
		}

		// Get a log friendly value
		String oldLogValue = getLogValue(oldParam.get());

		// There is a new value for this property and it's different than the old value
		Object[] args = { prefix, p.getName(), oldLogValue, newLogValue };
		if (p.isOverride()) {
			// Change it, and log the fact that we are changing it
			logger.info("{}* override - [{}]=[{}] -> [{}]", args);
			params.put(p.getName(), p);
		} else {
			// Ignore it, and log the fact that we are ignoring the new value from the config file
			logger.info("{}~ ignore - [{}]=[{}] -> ignoring [{}]", args);
		}
	}

	protected String getLogValue(Param param) {
		String lcase = param.getName().toLowerCase();
		for (String obscurePattern : obscureTokens) {
			if (lcase.contains(obscurePattern)) {
				return Str.flatten(obscurer.obscure(param.getValue()));
			}
		}
		return Str.flatten(param.getValue());
	}

	protected void checkParam(Param param) {
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

	protected Map<String, Param> getParamMap(Properties properties) {
		Map<String, Param> params = Maps.newHashMap();
		List<String> keys = PropertyUtils.getSortedKeys(properties);
		for (String key : keys) {
			String value = properties.getProperty(key);
			// "override" defaults to true here because that is by far the most "normal" and widely accepted behavior
			// Both Spring and Maven adhere to the "last one in wins" strategy, so we follow that here
			// Normal .properties files don't have a way to toggle an "override" attribute at the individual property level (nor should they)
			// Thus, the default value of "true"
			Param param = Param.builder(key, value).override(true).build();
			params.put(param.getName(), param);
		}
		return params;
	}

	protected boolean isPropertiesFile(String location) {
		String lower = StringUtils.lowerCase(location);
		return StringUtils.endsWith(lower, ".properties");
	}

	protected Properties convert(Map<String, Param> params) {
		Properties properties = new Properties();
		for (Param p : params.values()) {
			properties.setProperty(p.getName(), p.getValue());
		}
		return properties;
	}

	protected String getResolvedValue(String value, Map<String, Param> params) {
		Properties properties = convert(params);
		Properties global = PropertyUtils.getGlobalProperties(properties);
		return propertyPlaceholderHelper.replacePlaceholders(value, global);
	}

	private RicePropertiesLoader(Builder builder) {
		this.propertyPlaceholderHelper = builder.propertyPlaceholderHelper;
		this.magicNestedConfigKey = builder.magicNestedConfigKey;
		this.obscureTokens = builder.obscureTokens;
		this.obscurer = builder.obscurer;
		this.ignoreUnresolvablePlaceholdersInConfigLocationValues = builder.ignoreUnresolvablePlaceholdersInConfigLocationValues;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {

		private String magicNestedConfigKey = "config.location";
		private List<String> obscureTokens = ImmutableList.of("secret", "password", "private");
		private Obscurer obscurer = new DefaultObscurer();
		private boolean ignoreUnresolvablePlaceholdersInConfigLocationValues = false;
		private PropertyPlaceholderHelper propertyPlaceholderHelper;

		public Builder ignoreUnresolvablePlaceholdersInConfigLocationValues(boolean ignoreUnresolvablePlaceholdersInConfigLocationValues) {
			this.ignoreUnresolvablePlaceholdersInConfigLocationValues = ignoreUnresolvablePlaceholdersInConfigLocationValues;
			return this;
		}

		public Builder magicNestedConfigFileKey(String magicNestedConfigFileKey) {
			this.magicNestedConfigKey = magicNestedConfigFileKey;
			return this;
		}

		public Builder obscurePatterns(List<String> obscurePatterns) {
			this.obscureTokens = obscurePatterns;
			return this;
		}

		public RicePropertiesLoader build() {
			this.propertyPlaceholderHelper = new PropertyPlaceholderHelper("${", "}", ":", ignoreUnresolvablePlaceholdersInConfigLocationValues);
			this.obscureTokens = ImmutableList.copyOf(obscureTokens);
			RicePropertiesLoader instance = new RicePropertiesLoader(this);
			validate(instance);
			return instance;
		}

		private static void validate(RicePropertiesLoader instance) {
			Preconditions.checkNotNull(instance.propertyPlaceholderHelper, "propertyPlaceholderHelper cannot be null");
			Preconditions.checkNotNull(instance.obscureTokens, "obscureTokens cannot be null");
			Preconditions.checkNotNull(instance.obscurer, "obscurer cannot be null");
			Preconditions.checkArgument(!StringUtils.isBlank(instance.magicNestedConfigKey), "magicNestedConfigKey cannot be blank");
		}
	}

	protected void randomize(Map<String, Param> params) {
		for (String key : params.keySet()) {
			Param param = params.get(key);
			if (param.isRandom()) {
				String rangeSpec = param.getValue();
				String random = String.valueOf(rangeSpec);
				Param newParam = Param.builder(param.getName(), random).build();
				params.put(key, newParam);
			}
		}
	}

	protected int getRandomInteger(String rangeSpec) {
		String[] range = rangeSpec.split("-");
		Preconditions.checkState(range.length == 2, "Invalid range specifier: %s", rangeSpec);
		int from = Integer.parseInt(range[0].trim());
		int to = Integer.parseInt(range[1].trim());
		if (from > to) {
			int tmp = from;
			from = to;
			to = tmp;
		}
		// not very random
		if (from == to) {
			return from;
		} else {
			return from + RANDOM.nextInt((to - from) + 1);
		}
	}

	public PropertyPlaceholderHelper getPropertyPlaceholderHelper() {
		return propertyPlaceholderHelper;
	}

	public String getMagicNestedConfigKey() {
		return magicNestedConfigKey;
	}

	public List<String> getObscureTokens() {
		return obscureTokens;
	}

	public Obscurer getObscurer() {
		return obscurer;
	}

	public boolean isIgnoreUnresolvablePlaceholdersInConfigLocationValues() {
		return ignoreUnresolvablePlaceholdersInConfigLocationValues;
	}

}
