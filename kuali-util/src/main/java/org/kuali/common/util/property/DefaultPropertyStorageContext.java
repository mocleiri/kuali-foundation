package org.kuali.common.util.property;

import java.io.File;

public class DefaultPropertyStorageContext extends DefaultPropertyHandlingContext implements PropertyStoreContext {

	// The file to store properties to
	File file;

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
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
