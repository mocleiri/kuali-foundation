package org.kuali.common.impex.schema;

import java.io.File;
import java.util.List;

public class SchemaFileRequest {

	List<String> includes;
	List<String> excludes;
	File outputFile;
	File relativeDir;
	boolean logExcludedSchemaObjects;

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

	public File getOutputFile() {
		return outputFile;
	}

	public void setOutputFile(File outputFile) {
		this.outputFile = outputFile;
	}

	public File getRelativeDir() {
		return relativeDir;
	}

	public void setRelativeDir(File relativeDir) {
		this.relativeDir = relativeDir;
	}

	public boolean isLogExcludedSchemaObjects() {
		return logExcludedSchemaObjects;
	}

	public void setLogExcludedSchemaObjects(boolean logExcludedSchemaObjects) {
		this.logExcludedSchemaObjects = logExcludedSchemaObjects;
	}

}
