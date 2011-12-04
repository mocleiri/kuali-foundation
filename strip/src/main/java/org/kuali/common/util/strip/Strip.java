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
import java.util.ArrayList;
import java.util.List;

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

	protected List<String> getFilenames(String[] args) {
		if (isEmpty(args)) {
			return null;
		}
		int dirIndex = getDirIndex(args);
		int filenamesIndex = dirIndex == -1 ? 0 : dirIndex + 2;
		if (filenamesIndex >= args.length) {
			throw new IllegalArgumentException("no filenames were provided");
		}
		List<String> filenames = new ArrayList<String>();
		for (int i = filenamesIndex; i < args.length; i++) {
			String filename = args[i];
			filenames.add(filename);
		}
		return filenames;
	}

	protected int getDirIndex(String[] args) {
		for (int i = 0; i < args.length; i++) {
			String arg = args[i];
			if (arg.equals("-d")) {
				return i;
			}
		}
		return -1;
	}

	protected boolean isEmpty(String[] args) {
		return args == null || args.length == 0;
	}

	protected File getWorkingDir(String[] args) throws IOException {
		if (isEmpty(args)) {
			return this.dir;
		}
		int index = getDirIndex(args);
		if (index == -1) {
			return this.dir;
		} else {
			return getArgsDir(args, index);
		}
	}

	protected File getArgsDir(String[] args, int index) {
		if ((index + 1) >= args.length) {
			String msg = "-d was provided without a directory";
			throw new IllegalArgumentException(msg);
		}
		String argsDir = args[index + 1];
		if (isDir(argsDir)) {
			return new File(args[index + 1]);
		} else {
			throw new IllegalArgumentException(argsDir + " is not a directory");
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
		sb.append("\n");
		sb.append("strip [files]\n");
		sb.append("strip -d [dir] [files]\n");
		sb.append("strip -R -d [dir] [files]\n");
		sb.append("\n");
		System.out.print(sb.toString());
	}

	public void exec(String[] args) {
		if (isEmpty(args)) {
			showUsage();
			return;
		}
		try {
			File dir = getWorkingDir(args);
			List<String> filenames = getFilenames(args);
			strip(dir, filenames);
		} catch (IllegalArgumentException e) {
			System.out.println("Error: " + e.getMessage());
			showUsage();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void strip(File workingDir, List<String> filenames) throws IOException {
		String path = workingDir.getCanonicalPath();
		for (String filename : filenames) {
			File file = new File(path + FS + filename);
			String s = read(file);
			if (isSkip(s)) {
				System.out.println("Skipping " + file.getCanonicalPath());
				continue;
			}
			s = replace(s);
			write(file, s);
		}
	}

	protected boolean isSkip(String s) {
		return s.indexOf(CR) == -1;
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
