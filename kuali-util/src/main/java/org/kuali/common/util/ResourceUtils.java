package org.kuali.common.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

public class ResourceUtils {

	public static final BufferedReader getBufferedReader(String location) {
		return getBufferedReader(location, null);
	}

	public static final BufferedReader getBufferedReader(String location, String encoding) {
		InputStream in = getInputStream(location);
		return getBufferedReader(in, encoding);
	}

	public static final BufferedReader getBufferedReaderFromString(String s) {
		return getBufferedReaderFromString(s, null);
	}

	public static final BufferedReader getBufferedReaderFromString(String s, String encoding) {
		InputStream in = new ByteArrayInputStream(s.getBytes());
		return getBufferedReader(in, encoding);
	}

	public static final BufferedReader getBufferedReader(InputStream in, String encoding) {
		try {
			if (StringUtils.isBlank(encoding)) {
				return new BufferedReader(new InputStreamReader(in));
			} else {
				return new BufferedReader(new InputStreamReader(in, encoding));
			}
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error", e);
		}
	}

	public static final boolean isFile(String location) {
		File file = new File(location);
		return file.exists();
	}

	public static final boolean exists(String location) {
		if (isFile(location)) {
			return true;
		} else {
			ResourceLoader loader = new DefaultResourceLoader();
			Resource resource = loader.getResource(location);
			return resource.exists();
		}
	}

	public static final InputStream getInputStream(String location) {
		try {
			if (isFile(location)) {
				return new FileInputStream(location);
			}
			ResourceLoader loader = new DefaultResourceLoader();
			Resource resource = loader.getResource(location);
			return resource.getInputStream();
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error", e);
		}
	}
}
