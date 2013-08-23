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
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.kuali.common.util.Assert;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.xml.jaxb.XmlBind;

public class DefaultXmlService implements XmlService {

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

	protected Class<?>[] getClassesToBeBound(Class<?> clazz) {
		XmlBind bindings = clazz.getAnnotation(XmlBind.class);
		if (bindings == null) {
			return new Class<?>[] { clazz };
		} else {
			return ArrayUtils.add(bindings.classes(), clazz);
		}
	}

	@Override
	public void write(OutputStream out, Object object) {
		Assert.noNulls(out, object);
		try {
			Class<?>[] classes = getClassesToBeBound(object.getClass());
			JAXBContext context = JAXBContext.newInstance(classes);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
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
			Class<?>[] bindings = getClassesToBeBound(type);
			JAXBContext context = JAXBContext.newInstance(bindings);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			return (T) unmarshaller.unmarshal(in);
		} catch (JAXBException e) {
			throw new IllegalStateException("Unexpected JAXB error", e);
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

	public String toXml(Object object, String encoding, Class<?>... types) {
		Assert.noNulls(object, types);
		Assert.noBlanks(encoding);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			JAXBContext context = JAXBContext.newInstance(combine(object.getClass(), types));
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.marshal(object, out);
			return out.toString(encoding);
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}

	protected Class<?>[] combine(Class<?> clazz, Class<?>[] classes) {
		List<Class<?>> list = new ArrayList<Class<?>>();
		list.add(clazz);
		list.addAll(Arrays.asList(classes));
		return list.toArray(new Class[list.size()]);
	}

	/**
	 * @deprecated
	 */
	@Override
	@Deprecated
	public String toString(Object object, String encoding) {
		return toXml(object, encoding);
	}
}
