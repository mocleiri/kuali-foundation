package org.kuali.maven.mojo.context;

import java.io.File;

import org.apache.tools.ant.Project;

public class AntContext {
	Project antProject;
	String resultProperty;
	File outputFile;
	String[] args;
	String classpath;

	public Project getAntProject() {
		return antProject;
	}

	public void setAntProject(Project antProject) {
		this.antProject = antProject;
	}

	public String getResultProperty() {
		return resultProperty;
	}

	public void setResultProperty(String resultProperty) {
		this.resultProperty = resultProperty;
	}

	public File getOutputFile() {
		return outputFile;
	}

	public void setOutputFile(File outputFile) {
		this.outputFile = outputFile;
	}

	public String[] getArgs() {
		return args;
	}

	public void setArgs(String[] args) {
		this.args = args;
	}

	public String getClasspath() {
		return classpath;
	}

	public void setClasspath(String classpath) {
		this.classpath = classpath;
	}

}
