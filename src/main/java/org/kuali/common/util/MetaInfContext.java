package org.kuali.common.util;

import java.io.File;
import java.util.List;

public class MetaInfContext {

	File baseDir;
	File outputFile;
	String prefix = "classpath:";
	boolean sort = true;
	List<String> includes;
	List<String> excludes;
	boolean addPropertiesFile;
	boolean addLineCount;

	public File getBaseDir() {
		return baseDir;
	}

	public void setBaseDir(File baseDir) {
		this.baseDir = baseDir;
	}

	public File getOutputFile() {
		return outputFile;
	}

	public void setOutputFile(File outputFile) {
		this.outputFile = outputFile;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public boolean isSort() {
		return sort;
	}

	public void setSort(boolean sort) {
		this.sort = sort;
	}

	public List<String> getIncludes() {
		return includes;
	}

	public void setIncludes(List<String> includes) {
		this.includes = includes;
	}

	public List<String> getExcludes() {
		return excludes;
	}

	public void setExcludes(List<String> excludes) {
		this.excludes = excludes;
	}

	public boolean isAddPropertiesFile() {
		return addPropertiesFile;
	}

	public void setAddPropertiesFile(boolean addPropertiesFile) {
		this.addPropertiesFile = addPropertiesFile;
	}

	public boolean isAddLineCount() {
		return addLineCount;
	}

	public void setAddLineCount(boolean addLineCount) {
		this.addLineCount = addLineCount;
	}
}
