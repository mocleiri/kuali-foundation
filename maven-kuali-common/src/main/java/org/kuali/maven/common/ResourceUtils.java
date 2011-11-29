package org.kuali.maven.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

public class ResourceUtils {
	ResourceLoader loader = new DefaultResourceLoader();

	public InputStream getInputStream(String location) throws IOException {
		File file = new File(location);
		if (file.exists()) {
			return new FileInputStream(file);
		}
		Resource resource = loader.getResource(location);
		if (!resource.exists()) {
			throw new IOException("Unable to locate " + location);
		}
		return resource.getInputStream();
	}

	public void copy(String location, String filename) throws IOException {
		String contents = read(location);
		write(filename, contents);
	}

	public void write(String filename, String contents) throws IOException {
		OutputStream out = null;
		try {
			out = FileUtils.openOutputStream(new File(filename));
			IOUtils.write(contents, out);
		} finally {
			IOUtils.closeQuietly(out);
		}

	}

	public String read(String location) throws IOException {
		InputStream in = null;
		try {
			in = getInputStream(location);
			return IOUtils.toString(in);
		} finally {
			IOUtils.closeQuietly(in);
		}
	}
}
