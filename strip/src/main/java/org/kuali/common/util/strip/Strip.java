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

	/**
	 * @param args
	 */
	public static synchronized void main(String[] args) {
		new Strip().exec(args);
	}

	public void exec(String[] args) {
		try {
			String filename = "." + FS + args[0];
			File file = new File(filename);
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
