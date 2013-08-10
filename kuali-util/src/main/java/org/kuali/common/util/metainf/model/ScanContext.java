package org.kuali.common.util.metainf.model;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.kuali.common.util.Assert;
import org.kuali.common.util.CollectionUtils;

public class ScanContext {

	private final File directory;
	private final List<String> includes;
	private final List<String> excludes;

	public ScanContext(File directory, String includes) {
		this(directory, Arrays.asList(includes));
	}

	public ScanContext(File directory, String... includes) {
		this(directory, Arrays.asList(includes));
	}

	public ScanContext(File directory, List<String> includes) {
		this(directory, includes, Collections.<String> emptyList());
	}

	public ScanContext(File directory, List<String> includes, List<String> excludes) {
		Assert.noNulls(directory, includes, excludes);
		this.directory = directory;
		this.includes = CollectionUtils.unmodifiableCopy(includes);
		this.excludes = CollectionUtils.unmodifiableCopy(excludes);
	}

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
