/**
 * Copyright 2010-2013 The Kuali Foundation
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
package org.kuali.common.util.service;

import java.io.File;
import java.util.List;
import java.util.Properties;

public class MavenContext {

	public static final String DEFAULT_EXECUTABLE = "mvn";
	public static final String MAVEN_OPTS = "MAVEN_OPTS";

	String executable = DEFAULT_EXECUTABLE;
	boolean inheritMavenOpts = true;
	File workingDir;
	File pom;
	boolean debug;
	boolean errors;
	boolean batchMode;
	boolean quiet;
	boolean offline;
	List<String> profiles;
	List<String> options;
	List<String> goals;
	List<String> phases;
	List<String> passThroughPropertyKeys;
	Properties properties;

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

	public boolean isInheritMavenOpts() {
		return inheritMavenOpts;
	}

	public void setInheritMavenOpts(boolean inheritMavenOpts) {
		this.inheritMavenOpts = inheritMavenOpts;
	}

	public List<String> getProfiles() {
		return profiles;
	}

	public void setProfiles(List<String> profiles) {
		this.profiles = profiles;
	}

	public List<String> getPassThroughPropertyKeys() {
		return passThroughPropertyKeys;
	}

	public void setPassThroughPropertyKeys(List<String> passThroughPropertyKeys) {
		this.passThroughPropertyKeys = passThroughPropertyKeys;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

}
