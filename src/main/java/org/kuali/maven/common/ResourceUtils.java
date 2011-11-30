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

	/**
	 * Given a location that can represent either a file on the file system or a Spring style resource, return an
	 * InputStream. The method checks the file system first. If no file exists, it uses Spring resource loading to
	 * obtain an InputStream
	 */
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

	/**
	 * Copy a URL location to the local file system
	 */
	public void copy(String location, String filename) throws IOException {
		InputStream in = null;
		OutputStream out = null;
		try {
			in = getInputStream(location);
			out = FileUtils.openOutputStream(new File(filename));
			IOUtils.copy(in, out);
		} finally {
			IOUtils.closeQuietly(in);
			IOUtils.closeQuietly(out);

		}
	}

	/**
	 * Write the string to the file system
	 */
	public void write(String filename, String contents) throws IOException {
		OutputStream out = null;
		try {
			out = FileUtils.openOutputStream(new File(filename));
			IOUtils.write(contents, out);
		} finally {
			IOUtils.closeQuietly(out);
		}

	}

	/**
	 * Read the contents of the URL location into a string
	 */
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
