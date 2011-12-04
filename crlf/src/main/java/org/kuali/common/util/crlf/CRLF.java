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
package org.kuali.common.util.crlf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

public class CRLF {
	public static final String FS = System.getProperty("file.separator");
	public static final String LF = "\n";
	public static final String CR = "\r";
	public static final String CRLF = CR + LF;
	public static final String DEBUG_KEY = "crlf.debug";
	public static final boolean DEBUG = Boolean.getBoolean(DEBUG_KEY);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new CRLF().exec(args);
	}

	public void exec(String[] args) {
		if (isEmpty(args)) {
			showUsage();
			return;
		}
		try {
			File dir = new File(".");
			List<File> files = getFiles(dir, args);
			replace(files);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected boolean isEmpty(String[] args) {
		return args == null || args.length == 0;
	}

	protected void showUsage() {
		System.out.println("usage: crlf [files]");
	}

	protected void debug(String msg) {
		if (!DEBUG) {
			return;
		} else {
			System.out.println(msg);
		}
	}

	protected void handleFile(File file, List<File> files) {
		if (file.isDirectory()) {
			debug("skipped " + file.getName() + " - dir");
		} else if (!file.canRead()) {
			System.out.println("skipped " + file.getName() + " - can't read");
		} else if (!file.canWrite()) {
			System.out.println("skipped " + file.getName() + " - can't write");
		} else {
			files.add(file);
		}
	}

	protected List<File> getFiles(File dir, String[] args) throws IOException {
		String path = dir.getCanonicalPath();
		List<File> files = new ArrayList<File>();
		for (String arg : args) {
			String filename = path + FS + arg;
			File file = new File(filename);
			handleFile(file, files);
		}
		return files;
	}

	protected void replace(List<File> files) throws IOException {
		for (File file : files) {
			String s = read(file);
			if (s.indexOf(CR) == -1) {
				debug("skipped " + file.getName() + " - no cr's");
			} else {
				s = replace(s);
				write(file, s);
				System.out.println("updated " + file.getName());
			}
		}
	}

	protected String read(File file) throws IOException {
		InputStream in = null;
		try {
			in = new FileInputStream(file);
			return IOUtils.toString(in);
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

	protected void write(File file, String s) throws IOException {
		OutputStream out = null;
		try {
			out = new FileOutputStream(file);
			IOUtils.write(s.getBytes(), out);
		} finally {
			IOUtils.closeQuietly(out);
		}
	}

	protected String replace(String s) {
		s = StringUtils.replace(s, CRLF, LF);
		return StringUtils.replace(s, CR, LF);
	}

}
