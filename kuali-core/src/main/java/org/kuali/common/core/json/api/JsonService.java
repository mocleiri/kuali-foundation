package org.kuali.common.core.json.api;

import java.io.InputStream;
import java.io.OutputStream;

public interface JsonService {

	<T> T readString(String json, Class<T> type);

	<T> T read(InputStream in, Class<T> type);

	<T> String writeString(T reference);

	<T> void write(OutputStream out, T reference);

}
