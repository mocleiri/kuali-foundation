package org.kuali.common.jdbc.convert;

import java.io.BufferedReader;
import java.io.File;
import java.io.OutputStream;

public class ConversionContext {

	String delimiter = "/";
	String encoding = "UTF-8";
	int maxLength = 1024 * 50;
	int maxCount = 50;
	File oldFile;
	File newFile;
	BufferedReader in;
	OutputStream out;

	public File getOldFile() {
		return oldFile;
	}

	public void setOldFile(File oldFile) {
		this.oldFile = oldFile;
	}

	public File getNewFile() {
		return newFile;
	}

	public void setNewFile(File newFile) {
		this.newFile = newFile;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public int getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}

	public int getMaxCount() {
		return maxCount;
	}

	public void setMaxCount(int maxCount) {
		this.maxCount = maxCount;
	}

	public BufferedReader getIn() {
		return in;
	}

	public void setIn(BufferedReader in) {
		this.in = in;
	}

	public OutputStream getOut() {
		return out;
	}

	public void setOut(OutputStream out) {
		this.out = out;
	}

	public String getDelimiter() {
		return delimiter;
	}

	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}

}
