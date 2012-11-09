package org.kuali.common.util.spring;

import org.kuali.common.util.ResourceUtils;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.util.Assert;

public class ToStringFactoryBean implements FactoryBean<String> {

	// The location to read in
	String location;
	// The encoding for the content in that location
	String encoding;
	// If true, the content is trimmed before returning
	boolean trim;
	// If true, the location (if it is a file) is deleted after being read in
	boolean delete;
	// If true, no exception is thrown if the location cannot be deleted
	boolean quiet;

	@Override
	public String getObject() throws Exception {
		Assert.notNull("location is null", location);
		String s = ResourceUtils.toString(location, encoding);
		if (delete) {
			ResourceUtils.delete(location, quiet);
		}
		return (trim) ? s.trim() : s;
	}

	@Override
	public Class<String> getObjectType() {
		return String.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

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

	public boolean isQuiet() {
		return quiet;
	}

	public void setQuiet(boolean quiet) {
		this.quiet = quiet;
	}
}
