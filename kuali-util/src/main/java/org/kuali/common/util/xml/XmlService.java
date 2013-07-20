package org.kuali.common.util.xml;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

public interface XmlService {

	<T> T getObject(InputStream in, Class<T> type);

	<T> T getObject(File in, Class<T> type);

	<T> T getObject(String location, Class<T> type);

	<T> void write(File file, T instance);

	<T> void write(OutputStream out, T instance);

	<T> String toString(T instance, String encoding);

}
