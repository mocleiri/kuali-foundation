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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.kuali.common.util.LocationUtils;

public class DefaultXmlService implements XmlService {

	@Override
	public <T> void write(File file, T instance) {
		OutputStream out = null;
		try {
			out = FileUtils.openOutputStream(file);
			write(out, instance);
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error", e);
		} finally {
			IOUtils.closeQuietly(out);
		}
	}

	@Override
	public <T> void write(OutputStream out, T instance) {
		try {
			JAXBContext context = JAXBContext.newInstance(instance.getClass());
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.marshal(instance, out);
		} catch (JAXBException e) {
			throw new IllegalStateException("Unexpected JAXB error", e);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T getObject(InputStream in, Class<T> type) {
		try {
			JAXBContext context = JAXBContext.newInstance(type);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			return (T) unmarshaller.unmarshal(in);
		} catch (JAXBException e) {
			throw new IllegalStateException("Unexpected JAXB error", e);
		}
	}

	@Override
	public <T> T getObject(File file, Class<T> type) {
		return getObject(LocationUtils.getCanonicalPath(file), type);
	}

	@Override
	public <T> T getObject(String location, Class<T> type) {
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
	public <T> String toString(T instance, String encoding) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		write(out, instance);
		try {
			return out.toString(encoding);
		} catch (UnsupportedEncodingException e) {
			throw new IllegalArgumentException(e);
		}
	}
}
