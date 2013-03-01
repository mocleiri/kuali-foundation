package org.kuali.common.util.service;

import java.io.File;
import java.util.List;

public class MavenContext {

	String executable = "mvn";
	File workingDir;
	File pom;
	boolean debug;
	boolean errors;
	boolean batchMode;
	boolean quiet;
	boolean offline;
	boolean mavenOpts;
	List<String> options;
	List<String> goals;
	List<String> phases;

	public String getExecutable() {
		return executable;
	}

	public void setExecutable(String executable) {
		this.executable = executable;
	}

	public File getWorkingDir() {
		return workingDir;
	}

	public void setWorkingDir(File workingDir) {
		this.workingDir = workingDir;
	}

	public File getPom() {
		return pom;
	}

	public void setPom(File pom) {
		this.pom = pom;
	}

	public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	public boolean isErrors() {
		return errors;
	}

	public void setErrors(boolean errors) {
		this.errors = errors;
	}

	public boolean isBatchMode() {
		return batchMode;
	}

	public void setBatchMode(boolean batchMode) {
		this.batchMode = batchMode;
	}

	public boolean isQuiet() {
		return quiet;
	}

	public void setQuiet(boolean quiet) {
		this.quiet = quiet;
	}

	public boolean isOffline() {
		return offline;
	}

	public void setOffline(boolean offline) {
		this.offline = offline;
	}

	public boolean isMavenOpts() {
		return mavenOpts;
	}

	public void setMavenOpts(boolean mavenOpts) {
		this.mavenOpts = mavenOpts;
	}

	public List<String> getOptions() {
		return options;
	}

	public void setOptions(List<String> options) {
		this.options = options;
	}

	public List<String> getGoals() {
		return goals;
	}

	public void setGoals(List<String> goals) {
		this.goals = goals;
	}

	public List<String> getPhases() {
		return phases;
	}

	public void setPhases(List<String> phases) {
		this.phases = phases;
	}

}
