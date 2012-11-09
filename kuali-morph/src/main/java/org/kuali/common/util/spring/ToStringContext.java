package org.kuali.common.util.spring;

public class ToStringContext {

	// The location to convert into a string
	String location;
	// The encoding for the content in that location
	String encoding;
	// If true, the content is trimmed before returning
	boolean trim;
	// If true, the location (if it is a file) is deleted after being read in
	boolean delete;
	// If true, no exception is thrown if the location cannot be deleted
	boolean deleteQuietly;

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public boolean isTrim() {
		return trim;
	}

	public void setTrim(boolean trim) {
		this.trim = trim;
	}

	public boolean isDelete() {
		return delete;
	}

	public void setDelete(boolean delete) {
		this.delete = delete;
	}

	public boolean isDeleteQuietly() {
		return deleteQuietly;
	}

	public void setDeleteQuietly(boolean quiet) {
		this.deleteQuietly = quiet;
	}
}
