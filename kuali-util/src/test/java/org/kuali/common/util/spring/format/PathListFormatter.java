package org.kuali.common.util.spring.format;

import java.io.File;

public class PathListFormatter extends ListStringFormatter {

	public PathListFormatter() {
		super(File.pathSeparatorChar);
	}

}
