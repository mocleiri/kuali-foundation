package org.kuali.common.util.project.model;

import java.io.File;

import org.kuali.common.util.Assert;

public class Build {

	public Build(Project project, File projectDir, File baseDir, File buildOutput) {
		this(project, projectDir, baseDir, buildOutput, null, null, null, null);
	}

	public Build(Project project, File projectDir, File baseDir, File buildOutput, File buildSource, File buildScriptSource, File buildTestOutput, File buildTestSource) {
		Assert.noNulls(project, projectDir, baseDir, buildOutput);
		this.project = project;
		this.projectDir = projectDir;
		this.baseDir = baseDir;
		this.outputDir = buildOutput;
		this.buildSource = buildSource;
		this.scriptSourceDir = buildScriptSource;
		this.testOutputDir = buildTestOutput;
		this.testSourceDir = buildTestSource;
	}

	private final Project project;
	private final File projectDir;
	private final File baseDir;
	private final File outputDir;
	private final File buildSource;
	private final File scriptSourceDir;
	private final File testOutputDir;
	private final File testSourceDir;

	public File getProjectDir() {
		return projectDir;
	}

	public File getBaseDir() {
		return baseDir;
	}

	public File getOutputDir() {
		return outputDir;
	}

	public File getBuildSource() {
		return buildSource;
	}

	public File getScriptSourceDir() {
		return scriptSourceDir;
	}

	public File getTestOutputDir() {
		return testOutputDir;
	}

	public File getTestSourceDir() {
		return testSourceDir;
	}

	public Project getProject() {
		return project;
	}

}
