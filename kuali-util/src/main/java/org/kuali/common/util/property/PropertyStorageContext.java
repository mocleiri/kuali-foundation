package org.kuali.common.util.property;

import java.io.File;

public class PropertyStorageContext {

	// The file to store properties to
	File file;
	// The encoding to use when storing them (null means use JVM's default encoding)
	String encoding;
	// If true, sort the properties by key when storing
	boolean sort;
	// If not null, add this as a prefix to every key
	String prefix;
	// If not null, include this as a comment in the properties file
	String comment;
	// If set to ENVIRONMENT_VARIABLE properties are stored in a format environment variables are usually declared as
	PropertyStorageStyle style;

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public boolean isSort() {
		return sort;
	}

	public void setSort(boolean sort) {
		this.sort = sort;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public PropertyStorageStyle getStyle() {
		return style;
	}

	public void setStyle(PropertyStorageStyle style) {
		this.style = style;
	}

}
