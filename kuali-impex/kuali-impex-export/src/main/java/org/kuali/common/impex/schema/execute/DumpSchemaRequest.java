package org.kuali.common.impex.schema.execute;

import java.io.File;
import java.util.List;

import org.kuali.common.impex.model.Schema;
import org.kuali.common.impex.util.DumpConstants;

public class DumpSchemaRequest {

	public static final boolean DEFAULT_LOG_EXCLUDED_SCHEMA_OBJECTS = false;
	public static final List<String> DEFAULT_INCLUDES = DumpConstants.DEFAULT_REGEX_INCLUDES;
	public static final List<String> DEFAULT_EXCLUDES = DumpConstants.DEFAULT_REGEX_EXCLUDES;

	boolean logExcludedSchemaObjects = DEFAULT_LOG_EXCLUDED_SCHEMA_OBJECTS;
	List<String> includes = DEFAULT_INCLUDES;
	List<String> excludes = DEFAULT_EXCLUDES;

	Schema schema;
	File outputFile;

	// Optional
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
