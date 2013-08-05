package org.kuali.common.util.project.model;

import java.io.File;

public class ProjectDirectories {

	File base;
	File build;
	File buildOutput;
	File buildSource;
	File buildScriptSource;
	File buildTestOutput;
	File buildTestSource;

	public File getBase() {
		return base;
	}

	public void setBase(File base) {
		this.base = base;
	}

	public File getBuild() {
		return build;
	}

	public void setBuild(File build) {
		this.build = build;
	}

	public File getBuildOutput() {
		return buildOutput;
	}

	public void setBuildOutput(File buildOutput) {
		this.buildOutput = buildOutput;
	}

	public File getBuildSource() {
		return buildSource;
	}

	public void setBuildSource(File buildSource) {
		this.buildSource = buildSource;
	}

	public File getBuildScriptSource() {
		return buildScriptSource;
	}

	public void setBuildScriptSource(File buildScriptSource) {
		this.buildScriptSource = buildScriptSource;
	}

	public File getBuildTestOutput() {
		return buildTestOutput;
	}

	public void setBuildTestOutput(File buildTestOutput) {
		this.buildTestOutput = buildTestOutput;
	}

	public File getBuildTestSource() {
		return buildTestSource;
	}

	public void setBuildTestSource(File buildTestSource) {
		this.buildTestSource = buildTestSource;
	}

}
