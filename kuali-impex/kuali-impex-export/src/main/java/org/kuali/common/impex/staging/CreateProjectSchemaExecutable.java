package org.kuali.common.impex.staging;

import java.io.File;
import java.util.List;

import org.kuali.common.util.Project;
import org.kuali.common.util.execute.Executable;

public class CreateProjectSchemaExecutable implements Executable {

	Project project;
	File inputSchemaFile;
	File outputSchemaFile;
	List<String> includes;
	List<String> excludes;

	@Override
	public void execute() {
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public File getInputSchemaFile() {
		return inputSchemaFile;
	}

	public void setInputSchemaFile(File inputSchemaFile) {
		this.inputSchemaFile = inputSchemaFile;
	}

	public File getOutputSchemaFile() {
		return outputSchemaFile;
	}

	public void setOutputSchemaFile(File outputSchemaFile) {
		this.outputSchemaFile = outputSchemaFile;
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
}
