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
package org.kuali.common.util.strip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

public class Strip {
	public static final String FS = System.getProperty("file.separator");
	public static final String LF = "\n";
	public static final String CR = "\r";
	public static final String CRLF = CR + LF;
	File dir = new File(".");

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Strip().exec(args);
	}

	protected String getFilename(String[] args) {
		if (isEmpty(args)) {
			return null;
		}
		int length = args.length;
		return args[length - 1];
	}

	protected boolean isEmpty(String[] args) {
		return args == null || args.length == 0;
	}

	protected File getWorkingDir(String[] args) throws IOException {
		if (isEmpty(args)) {
			return this.dir;
		}

		for (int i = 0; i < args.length; i++) {
			String arg = args[i];
			if (!arg.equals("-d")) {
				continue;
			} else {
				return getArgsDir(args, i);
			}
		}
		return this.dir;
	}

	protected File getArgsDir(String[] args, int index) throws IOException {
		if ((index + 1) >= args.length) {
			String msg = "-d was provided without a directory";
			throw new IllegalArgumentException(msg);
		}
		String argsDir = args[index + 1];
		if (isDir(argsDir)) {
			return new File(args[index + 1]);
		} else {
			throw new IOException(argsDir + " is not a directory");
		}
	}

	protected boolean isDir(String dir) {
		File file = new File(dir);
		if (!file.exists()) {
			return false;
		}
		return file.isDirectory();
	}

	protected void showUsage() {
		StringBuilder sb = new StringBuilder();
		sb.append("strip [files]\n");
		sb.append("strip -d [dir] [files]\n");
		sb.append("strip -r -d [dir] [files]\n");
		System.out.println(sb.toString());
	}

	public void exec(String[] args) {
		try {
			File dir = getWorkingDir(args);
			String filename = getFilename(args);
			File file = new File(dir.getCanonicalPath() + FS + filename);
			String s = read(file);
			s = replace(s);
			write(file, s);
		} catch (Exception e) {
			e.printStackTrace();
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
