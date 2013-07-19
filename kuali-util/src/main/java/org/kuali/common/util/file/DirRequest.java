package org.kuali.common.util.file;

import java.io.File;
import java.util.List;

import org.kuali.common.util.FileSystemUtils;

public class DirRequest {

	List<String> includes = FileSystemUtils.DEFAULT_RECURSIVE_INCLUDES;
	List<String> excludes = FileSystemUtils.DEFAULT_SCM_IGNORE_PATTERNS;

	File sourceDir;
	File targetDir;
	File relativeDir;

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

	public File getSourceDir() {
		return sourceDir;
	}

	public void setSourceDir(File sourceDir) {
		this.sourceDir = sourceDir;
	}

	public File getTargetDir() {
		return targetDir;
	}

	public void setTargetDir(File targetDir) {
		this.targetDir = targetDir;
	}

	public File getRelativeDir() {
		return relativeDir;
	}

	public void setRelativeDir(File relativeDir) {
		this.relativeDir = relativeDir;
	}

}
