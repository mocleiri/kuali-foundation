package org.kuali.common.util.spring.format;

import java.io.File;

public class PathListFormatter extends AbstractListStringFormatter {

	public PathListFormatter() {
		super(File.pathSeparatorChar);
	}

}
