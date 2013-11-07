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
package org.kuali.common.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.io.IOUtils;

/**
 * @deprecated
 */
@Deprecated
public class JAXBUtil {

	public static void write(Object instance, File file) {
		Writer writer = null;
		try {
			writer = LocationUtils.openWriter(file);
			write(instance, writer);
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error", e);
		} finally {
			IOUtils.closeQuietly(writer);
		}
	}

	public static void write(Object instance, Writer writer) {
		try {
			JAXBContext context = JAXBContext.newInstance(instance.getClass());
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.marshal(instance, writer);
		} catch (JAXBException e) {
			throw new IllegalStateException("Unexpected JAXB error", e);
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T getObject(InputStream in, Class<T> type) {
		try {
			JAXBContext context = JAXBContext.newInstance(type);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			return (T) unmarshaller.unmarshal(in);
		} catch (JAXBException e) {
			throw new IllegalStateException("Unexpected JAXB error", e);
		}
	}

	public static <T> T getObject(File file, Class<T> type) {
		return getObject(LocationUtils.getCanonicalPath(file), type);
	}

	public static <T> T getObject(String location, Class<T> type) {
		InputStream in = null;
		try {
			in = LocationUtils.getInputStream(location);
			return getObject(in, type);
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected JAXB error", e);
		} finally {
			IOUtils.closeQuietly(in);
		}
	}
}
