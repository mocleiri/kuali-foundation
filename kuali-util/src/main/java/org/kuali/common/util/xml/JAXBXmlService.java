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
package org.kuali.common.util.xml;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.UnmarshallerHandler;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.kuali.common.util.Assert;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.xml.jaxb.XmlBind;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class JAXBXmlService implements XmlService {

	private final boolean formatOutput;
	private final boolean useNamespaceAwareParser;
	private final Map<String, ?> properties;

	@Override
	public void write(File file, Object object) {
		Assert.noNulls(file, object);
		OutputStream out = null;
		try {
			out = FileUtils.openOutputStream(file);
			write(out, object);
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error", e);
		} finally {
			IOUtils.closeQuietly(out);
		}
	}

	@Override
	public void write(OutputStream out, Object object) {
		Assert.noNulls(out, object);
		try {
			JAXBContext context = getJAXBContext(object.getClass());
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, formatOutput);
			marshaller.marshal(object, out);
		} catch (JAXBException e) {
			throw new IllegalStateException("Unexpected JAXB error", e);
		}
	}

	@Override
	public <T> T getObjectFromXml(String xml, String encoding, Class<T> type) {
		Assert.noBlanks(xml, encoding);
		Assert.noNulls(type);
		InputStream in = null;
		try {
			in = new ByteArrayInputStream(xml.getBytes(encoding));
			return getObject(in, type);
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error", e);
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T getObject(InputStream in, Class<T> type) {
		Assert.noNulls(in, type);
		try {
			Unmarshaller unmarshaller = getUnmarshaller(type);
			if (useNamespaceAwareParser) {
				return (T) unmarshaller.unmarshal(in);
			} else {
				UnmarshallerHandler unmarshallerHandler = unmarshaller.getUnmarshallerHandler();
				SAXParserFactory spf = SAXParserFactory.newInstance();
				SAXParser sp = spf.newSAXParser();
				XMLReader xr = sp.getXMLReader();
				xr.setContentHandler(unmarshallerHandler);
				InputSource xmlSource = new InputSource(in);
				xr.parse(xmlSource);
				return (T) unmarshallerHandler.getResult();
			}
		} catch (JAXBException e) {
			throw new IllegalStateException("Unexpected JAXB error", e);
		} catch (SAXException e) {
			throw new IllegalStateException("Unexpected SAX error", e);
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error", e);
		} catch (ParserConfigurationException e) {
			throw new IllegalStateException("Unexpected parser configuration error", e);
		}
	}

	@Override
	public <T> T getObject(File file, Class<T> type) {
		Assert.exists(file);
		Assert.noNulls(type);
		return getObject(LocationUtils.getCanonicalPath(file), type);
	}

	@Override
	public <T> T getObject(String location, Class<T> type) {
		Assert.noBlanks(location);
		Assert.noNulls(type);
		InputStream in = null;
		try {
			in = LocationUtils.getInputStream(location);
			return getObject(in, type);
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error", e);
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

	@Override
	public String toXml(Object object, String encoding) {
		Assert.noNulls(object);
		Assert.noBlanks(encoding);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		write(out, object);
		try {
			return out.toString(encoding);
		} catch (UnsupportedEncodingException e) {
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * @deprecated Use toXml(object,encoding) instead
	 */
	@Override
	@Deprecated
	public String toString(Object object, String encoding) {
		return toXml(object, encoding);
	}

	protected Unmarshaller getUnmarshaller(Class<?> clazz) throws JAXBException {
		Class<?>[] classes = getClassesToBeBound(clazz);
		JAXBContext jc = JAXBContext.newInstance(classes);
		return jc.createUnmarshaller();
	}

	protected JAXBContext getJAXBContext(Class<?> clazz) throws JAXBException {
		Class<?>[] classes = getClassesToBeBound(clazz);
		if (properties.size() > 0) {
			return JAXBContext.newInstance(classes);
		} else {
			return JAXBContext.newInstance(classes, properties);
		}
	}

	protected Class<?>[] getClassesToBeBound(Class<?> clazz) {
		List<Class<?>> classes = getClassesList(clazz);
		return classes.toArray(new Class<?>[classes.size()]);
	}

	protected List<Class<?>> getClassesList(Class<?> clazz) {
		XmlBind bindings = clazz.getAnnotation(XmlBind.class);
		List<Class<?>> classes = new ArrayList<Class<?>>(CollectionUtils.singletonList(clazz));
		if (bindings == null) {
			// base case
			return classes;
		} else {
			// recurse
			for (Class<?> binding : bindings.classes()) {
				classes.addAll(getClassesList(binding));
			}
			return classes;
		}
	}

	public boolean isFormatOutput() {
		return formatOutput;
	}

	public boolean isUseNamespaceAwareParser() {
		return useNamespaceAwareParser;
	}

	public static class Builder {

		private static final Map<String, ?> EMPTY_MAP = Collections.unmodifiableMap(new HashMap<String, Object>());
		public static final boolean FORMAT_OUTPUT = true;
		public static final boolean USE_NAMESPACE_AWARE_PARSER = true;

		// Optional fields with default values
		private boolean formatOutput = FORMAT_OUTPUT;
		private boolean useNamespaceAwareParser = USE_NAMESPACE_AWARE_PARSER;
		private Map<String, ?> properties = EMPTY_MAP;

		public Builder formatOutput(boolean formatOutput) {
			this.formatOutput = formatOutput;
			return this;
		}

		public Builder useNamespaceAwareParser(boolean useNamespaceAwareParser) {
			this.useNamespaceAwareParser = useNamespaceAwareParser;
			return this;
		}

		public Builder properties(Map<String, ?> properties) {
			this.properties = properties;
			return this;
		}

		public JAXBXmlService build() {
			Assert.noNulls(properties);
			this.properties = (properties == EMPTY_MAP) ? properties : Collections.unmodifiableMap(new HashMap<String, Object>(properties));
			return new JAXBXmlService(this);
		}

	}

	private JAXBXmlService(Builder builder) {
		this.formatOutput = builder.formatOutput;
		this.useNamespaceAwareParser = builder.useNamespaceAwareParser;
		this.properties = builder.properties;
	}

	public Map<String, ?> getProperties() {
		return properties;
	}

}
