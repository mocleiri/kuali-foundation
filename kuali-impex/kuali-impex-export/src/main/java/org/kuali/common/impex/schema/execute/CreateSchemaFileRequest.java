package org.kuali.common.impex.schema.execute;

import java.io.File;
import java.util.List;

import org.kuali.common.impex.model.Schema;

public class CreateSchemaFileRequest {

	Schema schema;
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

	public Schema getSchema() {
		return schema;
	}

	public void setSchema(Schema schema) {
		this.schema = schema;
	}

}
