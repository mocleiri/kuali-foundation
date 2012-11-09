package org.kuali.common.util.property;

import java.io.File;

public class DefaultPropertyStorageContext extends DefaultPropertyHandlingContext implements PropertyStorageContext {

	// The file to store properties to
	File file;

	// If true, sort the properties by key when storing
	boolean sort = true;

	// If not null, include this as a comment in the properties file
	String comment;

	@Override
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	@Override
	public boolean isSort() {
		return sort;
	}

	public void setSort(boolean sort) {
		this.sort = sort;
	}

	@Override
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
