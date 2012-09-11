package org.kuali.maven.plugins.jenkins;

import java.io.File;

import org.apache.commons.io.filefilter.IOFileFilter;

public class Rice211Filter implements IOFileFilter {
	String token = "-2.1.1";

	@Override
	public boolean accept(File file) {
		return accept(file.getAbsolutePath());
	}

	@Override
	public boolean accept(File dir, String name) {
		return accept(name);
	}

	protected boolean accept(String name) {
		return name.indexOf(token) != -1;
	}

}
