/**
 * Copyright 2004-2011 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.maven.plugins.jenkins.context;

import java.io.File;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.Path;

public class AntContext {
	Project antProject;
	String resultProperty;
	File outputFile;
	File inputFile;
	String[] args;
	Path classpath;
	String classname;
	int result;

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

	public File getInputFile() {
		return inputFile;
	}

	public void setInputFile(File inputFile) {
		this.inputFile = inputFile;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}
}
