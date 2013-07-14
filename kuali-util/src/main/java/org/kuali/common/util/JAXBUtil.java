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

public class JAXBUtil {

	public static void write(Object instance, File file) {
		Writer writer = null;
		try {
			writer = LocationUtils.openWriter(file);
			write(instance, writer);
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error", e);
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
