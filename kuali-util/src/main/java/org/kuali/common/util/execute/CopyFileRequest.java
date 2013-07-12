package org.kuali.common.util.execute;

import java.io.File;

public class CopyFileRequest {

	File source;
	File destination;

	public File getSource() {
		return source;
	}

	public void setSource(File source) {
		this.source = source;
	}

	public File getDestination() {
		return destination;
	}

	public void setDestination(File destination) {
		this.destination = destination;
	}

}
