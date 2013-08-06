package org.kuali.common.util.project.model;

import java.io.File;

import org.kuali.common.util.Assert;

public class Build {

	public Build(Project project, String encoding, File projectDir, File directory, File outputDir, File sourceDir, File scriptSourceDir, File testOutputDir, File testSourceDir) {
		Assert.noNulls(project, encoding, projectDir, directory, outputDir, sourceDir, scriptSourceDir, testOutputDir, testSourceDir);
		this.project = project;
		this.encoding = encoding;
		this.projectDir = projectDir;
		this.directory = directory;
		this.outputDir = outputDir;
		this.sourceDir = sourceDir;
		this.scriptSourceDir = scriptSourceDir;
		this.testOutputDir = testOutputDir;
		this.testSourceDir = testSourceDir;
	}

	private final Project project;
	private final String encoding;
	private final File projectDir;
	private final File directory;
	private final File outputDir;
	private final File sourceDir;
	private final File scriptSourceDir;
	private final File testOutputDir;
	private final File testSourceDir;

	public File getProjectDir() {
		return projectDir;
	}

	public File getDirectory() {
		return directory;
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

	public String getEncoding() {
		return encoding;
	}

}
