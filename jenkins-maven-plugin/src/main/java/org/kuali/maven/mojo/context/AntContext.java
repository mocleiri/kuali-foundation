package org.kuali.maven.mojo.context;

import java.io.File;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.Path;

public class AntContext {
	Project antProject;
	String resultProperty;
	File outputFile;
	String[] args;
	Path classpath;
	String classname;

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

	public Path getClasspath() {
		return classpath;
	}

	public void setClasspath(Path classpath) {
		this.classpath = classpath;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}
}
