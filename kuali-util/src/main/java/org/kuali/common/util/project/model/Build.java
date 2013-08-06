package org.kuali.common.util.project.model;

import java.io.File;

import org.kuali.common.util.Assert;

public class Build {

	public Build(Project project, File projectDir, File baseDir, File outputDir) {
		this(project, projectDir, baseDir, outputDir, null, null, null, null);
	}

	public Build(Project project, File projectDir, File baseDir, File outputDir, File sourceDir, File scriptSourceDir, File testOutPutDir, File testSourceDir) {
		Assert.noNulls(project, projectDir, baseDir, outputDir);
		this.project = project;
		this.projectDir = projectDir;
		this.baseDir = baseDir;
		this.outputDir = outputDir;
		this.sourceDir = sourceDir;
		this.scriptSourceDir = scriptSourceDir;
		this.testOutputDir = testOutPutDir;
		this.testSourceDir = testSourceDir;
	}

	private final Project project;
	private final File projectDir;
	private final File baseDir;
	private final File outputDir;
	private final File sourceDir;
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

	public File getSourceDir() {
		return sourceDir;
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
