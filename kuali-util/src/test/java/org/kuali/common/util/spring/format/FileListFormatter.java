package org.kuali.common.util.spring.format;

import java.io.File;

public class FileListFormatter extends AbstractListStringFormatter {

	public FileListFormatter() {
		super(File.separatorChar);
	}

}
