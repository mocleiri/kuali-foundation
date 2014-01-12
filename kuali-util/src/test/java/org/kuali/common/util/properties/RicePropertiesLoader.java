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

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.SortedSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

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

	private static final String PLACEHOLDER_REGEX = "\\$\\{([^{}]+)\\}";

	private final PropertyPlaceholderHelper propertyPlaceholderHelper;
	private final String chainedConfigLocationKey;
	private final List<String> obscureTokens;
	private final Obscurer obscurer;
	private final Randomizer randomizer;
	private final boolean systemPropertiesWin;
	private final boolean allowUnresolvablePlaceholders;
	private final boolean convertUnresolvablePlaceholdersToEmpty;

	public Properties load(String location) {
		checkArgument(!StringUtils.isBlank(location), "'location' cannot be blank");
		checkArgument(LocationUtils.exists(location), "[%s] does not exist", location);
		Unmarshaller unmarshaller = getUnmarshaller();
		Map<String, Param> params = Maps.newHashMap();
		Pattern pattern = Pattern.compile(PLACEHOLDER_REGEX);
		load(location, unmarshaller, 0, params, pattern);
		if (systemPropertiesWin) {
			Map<String, Param> system = convert(PropertyUtils.getGlobalProperties(), true, false);
			for (Param param : system.values()) {
				update(params, param, "");
			}
		}
		handleRandomParams(params);
		handleSystemParams(params, pattern);
		return convert(params);
	}

	protected String convertUnresolvablePlaceholdersToEmpty(String prefix, String value, Pattern pattern) {
		String result = value;
		Matcher matcher = pattern.matcher(value);
		while (matcher.find()) {
			// Get the first, outermost ${} in the string
			// This removes the ${} and produces the enclosed key
			String key = matcher.group(1);

			// Log the key that we are converting to the empty string
			logger.info("{}? unknown - [{}] -> converted to empty", prefix, key);

			// Replace the first ${} with ""
			result = matcher.replaceFirst("");

			// Reset the matcher so we can continue examining the string
			matcher = matcher.reset(result);
		}

		// All placeholders have been replaced with the empty string at this point
		return result;
	}

	protected void load(String location, Unmarshaller unmarshaller, int depth, Map<String, Param> params, Pattern pattern) {

		// Setup an indentation prefix based on the recursive depth
		final String prefix = StringUtils.repeat(" ", depth);

		// If the location does not exist, we are done
		if (StringUtils.isBlank(location) || !LocationUtils.exists(location)) {
			logger.info("{}# skip non-existent location [{}]", prefix, location);
			return;
		}

		InputStream in = null;
		try {
			in = LocationUtils.getInputStream(location);
			load(prefix, location, in, params, depth, unmarshaller, pattern);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

	protected void load(String prefix, String location, InputStream in, Map<String, Param> params, int depth, Unmarshaller unmarshaller, Pattern pattern) throws IOException {
		if (isPropertiesFile(location)) {
			loadJavaProperties(prefix, location, in, params, depth);
		} else {
			loadRiceProperties(prefix, location, in, params, depth, unmarshaller, pattern);
		}
	}

	protected void loadRiceProperties(String prefix, String location, InputStream in, Map<String, Param> params, int depth, Unmarshaller unmarshaller, Pattern pattern)
			throws IOException {
		logger.info("{}+ loading - [{}]", prefix, location);
		Config config = unmarshal(unmarshaller, in);
		for (Param p : config.getParams()) {
			handleParam(p, depth, unmarshaller, params, prefix, pattern);
		}
		logger.info("{}- loaded  - [{}]", prefix, location);
	}

	protected void handleParam(Param p, int depth, Unmarshaller unmarshaller, Map<String, Param> params, String prefix, Pattern pattern) {

		// This is a reference to a nested config file
		if (p.getName().equalsIgnoreCase(chainedConfigLocationKey)) {
			String originalLocation = p.getValue();
			String resolvedLocation = getResolvedValue(prefix, originalLocation, params, pattern);
			load(resolvedLocation, unmarshaller, depth + 1, params, pattern);
			return;
		}

		// Update the map of parameter objects with this parameter
		update(params, p, prefix);

	}

	protected void loadJavaProperties(String prefix, String location, InputStream in, Map<String, Param> params, int depth) throws IOException {
		logger.info("{}+ loading - [{}]", prefix, location);
		Properties loaded = new Properties();
		loaded.load(in);

		// "override" defaults to true here because that is by far the most "normal" and widely accepted behavior
		// Both Spring and Maven adhere to the "last one in wins" strategy, so we follow that
		// Normal .properties files don't have a way to toggle an "override" attribute at the individual property level (nor should they)
		// Thus, the default value of override=true
		Map<String, Param> newMap = convert(loaded, true, false);
		for (Param p : newMap.values()) {
			update(params, p, prefix);
		}
		logger.info("{}- loaded  - [{}]", prefix, location);
	}

	protected void update(Map<String, Param> params, Param p, String prefix) {
		checkNotNull(p.getValue(), "parameter value cannot be null");

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
		return location.toLowerCase().endsWith(".properties");
	}

	protected Map<String, Param> convert(Properties properties, boolean override, boolean system) {
		Map<String, Param> params = Maps.newHashMap();
		SortedSet<String> keys = Sets.newTreeSet(properties.stringPropertyNames());
		for (String key : keys) {
			String value = properties.getProperty(key);
			Param param = Param.builder(key, value).override(override).system(system).build();
			params.put(param.getName(), param);
		}
		return params;
	}

	protected Properties convert(Map<String, Param> params) {
		Properties properties = new Properties();
		for (Param p : params.values()) {
			properties.setProperty(p.getName(), p.getValue());
		}
		return properties;
	}

	protected String getResolvedValue(String prefix, String value, Map<String, Param> params, Pattern pattern) {
		Properties properties = convert(params);
		Properties global = PropertyUtils.getGlobalProperties(properties);
		String resolvedValue = propertyPlaceholderHelper.replacePlaceholders(value, global);
		if (convertUnresolvablePlaceholdersToEmpty) {
			resolvedValue = convertUnresolvablePlaceholdersToEmpty(prefix, resolvedValue, pattern);
		}
		return resolvedValue;
	}

	private RicePropertiesLoader(Builder builder) {
		this.propertyPlaceholderHelper = builder.propertyPlaceholderHelper;
		this.chainedConfigLocationKey = builder.chainedConfigLocationKey;
		this.obscureTokens = builder.obscureTokens;
		this.obscurer = builder.obscurer;
		this.randomizer = builder.randomizer;
		this.allowUnresolvablePlaceholders = builder.allowUnresolvablePlaceholders;
		this.systemPropertiesWin = builder.systemPropertiesWin;
		this.convertUnresolvablePlaceholdersToEmpty = builder.convertUnresolvablePlaceholdersToEmpty;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {

		private String chainedConfigLocationKey = "config.location";
		private List<String> obscureTokens = ImmutableList.of("secret", "password", "private", "encryption.key");
		private Obscurer obscurer = new DefaultObscurer();
		private Randomizer randomizer = DefaultRandomizer.builder().build();
		private boolean allowUnresolvablePlaceholders = true;
		private boolean systemPropertiesWin = false;
		private boolean convertUnresolvablePlaceholdersToEmpty = true;

		// This gets filled in by the build() method
		private PropertyPlaceholderHelper propertyPlaceholderHelper;

		public Builder systemPropertiesWin(boolean systemPropertiesWin) {
			this.systemPropertiesWin = systemPropertiesWin;
			return this;
		}

		public Builder convertUnresolvablePlaceholdersToEmpty(boolean convertUnresolvablePlaceholdersToEmpty) {
			this.convertUnresolvablePlaceholdersToEmpty = convertUnresolvablePlaceholdersToEmpty;
			return this;
		}

		public Builder allowUnresolvablePlaceholders(boolean allowUnresolvablePlaceholders) {
			this.allowUnresolvablePlaceholders = allowUnresolvablePlaceholders;
			return this;
		}

		public Builder chainedConfigLocationKey(String chainedConfigLocationKey) {
			this.chainedConfigLocationKey = chainedConfigLocationKey;
			return this;
		}

		public Builder obscurePatterns(List<String> obscurePatterns) {
			this.obscureTokens = obscurePatterns;
			return this;
		}

		public Builder randomizer(DefaultRandomizer randomizer) {
			this.randomizer = randomizer;
			return this;
		}

		public RicePropertiesLoader build() {
			this.propertyPlaceholderHelper = new PropertyPlaceholderHelper("${", "}", ":", allowUnresolvablePlaceholders);
			this.obscureTokens = ImmutableList.copyOf(obscureTokens);
			RicePropertiesLoader instance = new RicePropertiesLoader(this);
			validate(instance);
			return instance;
		}

		private static void validate(RicePropertiesLoader instance) {
			checkNotNull(instance.propertyPlaceholderHelper, "propertyPlaceholderHelper cannot be null");
			checkNotNull(instance.obscureTokens, "obscureTokens cannot be null");
			checkNotNull(instance.obscurer, "obscurer cannot be null");
			checkNotNull(instance.randomizer, "randomizer cannot be null");
			checkArgument(!StringUtils.isBlank(instance.chainedConfigLocationKey), "magicNestedConfigKey cannot be blank");
		}

		public String getChainedConfigLocationKey() {
			return chainedConfigLocationKey;
		}

		public void setChainedConfigLocationKey(String magicNestedConfigKey) {
			this.chainedConfigLocationKey = magicNestedConfigKey;
		}

		public List<String> getObscureTokens() {
			return obscureTokens;
		}

		public void setObscureTokens(List<String> obscureTokens) {
			this.obscureTokens = obscureTokens;
		}

		public Obscurer getObscurer() {
			return obscurer;
		}

		public void setObscurer(Obscurer obscurer) {
			this.obscurer = obscurer;
		}

		public Randomizer getRandomizer() {
			return randomizer;
		}

		public void setRandomizer(DefaultRandomizer randomizer) {
			this.randomizer = randomizer;
		}

		public boolean isConvertUnresolvablePlaceholdersToEmpty() {
			return convertUnresolvablePlaceholdersToEmpty;
		}

		public void setConvertUnresolvablePlaceholdersToEmpty(boolean convertUnresolvablePlaceholdersToEmpty) {
			this.convertUnresolvablePlaceholdersToEmpty = convertUnresolvablePlaceholdersToEmpty;
		}

		public boolean isSystemPropertiesWin() {
			return systemPropertiesWin;
		}

		public void setSystemPropertiesWin(boolean systemPropertiesWin) {
			this.systemPropertiesWin = systemPropertiesWin;
		}

		public boolean isAllowUnresolvablePlaceholders() {
			return allowUnresolvablePlaceholders;
		}

		public void setAllowUnresolvablePlaceholders(boolean allowUnresolvablePlaceholders) {
			this.allowUnresolvablePlaceholders = allowUnresolvablePlaceholders;
		}
	}

	protected void handleRandomParams(Map<String, Param> params) {
		List<Param> randoms = getRandomParams(params.values());
		for (Param param : randoms) {
			String rangeSpec = param.getValue();
			int random = randomizer.getInteger(rangeSpec);
			String value = Integer.toString(random);
			Param newParam = Param.builder(param.getName(), value).build();
			params.put(newParam.getName(), newParam);
		}
	}

	protected void handleSystemParams(Map<String, Param> params, Pattern pattern) {
		Properties properties = convert(params);
		List<Param> system = getSystemParams(params.values());
		for (Param param : system) {
			if (isOverrideSystemProperty(param)) {
				overrideSystemProperty("", param, params, properties, pattern);
			}
		}
	}

	protected boolean isOverrideSystemProperty(Param param) {

		// If the system flag is not set on this param, always return false
		if (!param.isSystem()) {
			return false;
		}

		// If we get here, we know the system flag on this parameter is set to true
		// We now need to check and see if there is an existing system property
		Optional<String> system = Optional.fromNullable(System.getProperty(param.getName()));

		if (system.isPresent()) {
			// If there is an existing system property, only return true if the override flag is set
			return param.isOverride();
		} else {
			// If there is no existing system property, always return true
			return true;
		}
	}

	protected void overrideSystemProperty(String prefix, Param param, Map<String, Param> params, Properties properties, Pattern pattern) {
		Param resolved = getResolvedParam(prefix, param, properties, pattern);
		if (!resolved.getValue().equals(param.getValue())) {
			params.put(resolved.getName(), resolved);
			properties.setProperty(resolved.getName(), resolved.getValue());
		}
		SystemPropertySetter setter = getSystemPropertySetter(resolved);
		setter.execute(resolved);
	}

	protected Param getResolvedParam(String prefix, Param param, Properties properties, Pattern pattern) {
		String originalValue = param.getValue();
		String resolvedValue = propertyPlaceholderHelper.replacePlaceholders(originalValue, properties);
		if (convertUnresolvablePlaceholdersToEmpty) {
			resolvedValue = convertUnresolvablePlaceholdersToEmpty(prefix, resolvedValue, pattern);
		}
		if (resolvedValue.equals(originalValue)) {
			return param;
		} else {
			return Param.builder(param.getName(), resolvedValue).build();
		}
	}

	protected List<Param> getRandomParams(Collection<Param> params) {
		List<Param> list = Lists.newArrayList();
		for (Param param : params) {
			if (param.isRandom()) {
				list.add(param);
			}
		}
		Collections.sort(list);
		return list;
	}

	protected List<Param> getSystemParams(Collection<Param> params) {
		List<Param> list = Lists.newArrayList();
		for (Param param : params) {
			if (param.isSystem()) {
				list.add(param);
			}
		}
		Collections.sort(list);
		return list;
	}

	private interface SystemPropertySetter {
		void execute(Param param);
	}

	protected SystemPropertySetter getSystemPropertySetter(Param param) {
		Optional<String> system = Optional.fromNullable(System.getProperty(param.getName()));

		// Add - there is no existing system property
		if (!system.isPresent()) {
			return new SystemPropertySetter() {
				@Override
				public void execute(Param param) {
					logger.info("~ add system property [{}]=[{}]", param.getName(), getLogValue(param));
					System.setProperty(param.getName(), param.getValue());
				}
			};
		}

		// Override - existing system property which is different from the parameter value
		if (system.isPresent() && !system.get().equals(param.getValue())) {
			return new SystemPropertySetter() {
				@Override
				public void execute(Param param) {
					logger.info("* override system property [{}]=[{}]", param.getName(), getLogValue(param));
					System.setProperty(param.getName(), param.getValue());
				}
			};
		}

		// Noop - existing system property which is exactly the same as the parameter value
		return new SystemPropertySetter() {
			@Override
			public void execute(Param param) {
				// noop
			}
		};
	}

	public PropertyPlaceholderHelper getPropertyPlaceholderHelper() {
		return propertyPlaceholderHelper;
	}

	public String getChainedConfigLocationKey() {
		return chainedConfigLocationKey;
	}

	public List<String> getObscureTokens() {
		return obscureTokens;
	}

	public Obscurer getObscurer() {
		return obscurer;
	}

	public Randomizer getRandomizer() {
		return randomizer;
	}

	public boolean isConvertUnresolvablePlaceholdersToEmpty() {
		return convertUnresolvablePlaceholdersToEmpty;
	}

	public boolean isSystemPropertiesWin() {
		return systemPropertiesWin;
	}

	public boolean isAllowUnresolvablePlaceholders() {
		return allowUnresolvablePlaceholders;
	}

}
