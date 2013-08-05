package org.kuali.common.util.project.model;

import java.io.File;

import org.kuali.common.util.Assert;

public class ProjectDirs {

	public ProjectDirs(File base, File build, File buildOutput) {
		this(base, build, buildOutput, null, null, null, null);
	}

	public ProjectDirs(File base, File build, File buildOutput, File buildSource, File buildScriptSource, File buildTestOutput, File buildTestSource) {
		Assert.noNulls("base, build, and buildOutput are required", base, build, buildOutput);
		this.base = base;
		this.build = build;
		this.buildOutput = buildOutput;
		this.buildSource = buildSource;
		this.buildScriptSource = buildScriptSource;
		this.buildTestOutput = buildTestOutput;
		this.buildTestSource = buildTestSource;
	}

	private final File base;
	private final File build;
	private final File buildOutput;
	private final File buildSource;
	private final File buildScriptSource;
	private final File buildTestOutput;
	private final File buildTestSource;

	public File getBase() {
		return base;
	}

	public File getBuild() {
		return build;
	}

	public File getBuildOutput() {
		return buildOutput;
	}

	public File getBuildSource() {
		return buildSource;
	}

	public File getBuildScriptSource() {
		return buildScriptSource;
	}

	public File getBuildTestOutput() {
		return buildTestOutput;
	}

	public File getBuildTestSource() {
		return buildTestSource;
	}

}
