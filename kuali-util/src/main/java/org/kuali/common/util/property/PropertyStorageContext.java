package org.kuali.common.util.property;

import java.io.File;
import java.util.Properties;

public class PropertyStorageContext {

	Properties properties;
	File file;
	String encoding;
	boolean sort;
	String prefix;
	String comment;
	PropertyStorageStyle style;

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

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
