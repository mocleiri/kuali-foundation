package org.kuali.common.util.spring.format;

import java.io.File;

public class FileListFormatter extends ListStringFormatter {

	public FileListFormatter() {
		super(File.separatorChar);
	}

}
