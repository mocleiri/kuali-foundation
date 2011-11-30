/**
 * Copyright 2004-2011 The Kuali Foundation
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
	 * Given a location that can represent either a file on the file system or a Spring style resource, return an InputStream. The method checks the file
	 * system first. If no file exists, it uses Spring resource loading to obtain an InputStream
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

	public String getFilename(String location) throws IOException {
		File file = new File(location);
		if (file.exists()) {
			return file.getName();
		}
		Resource resource = loader.getResource(location);
		if (!resource.exists()) {
			throw new IOException("Unable to locate " + location);
		}
		return resource.getFilename();
	}
}
