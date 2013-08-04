package org.kuali.common.util.metainf.model;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ScanContext {

	public ScanContext(File directory, List<String> includes, List<String> excludes) {
		super();
		this.directory = directory;
		this.includes = Collections.unmodifiableList(new ArrayList<String>(includes));
		this.excludes = Collections.unmodifiableList(new ArrayList<String>(excludes));
	}

	private final File directory;
	private final List<String> includes;
	private final List<String> excludes;

	public File getDirectory() {
		return directory;
	}

	public List<String> getIncludes() {
		return includes;
	}

	public List<String> getExcludes() {
		return excludes;
	}

}
