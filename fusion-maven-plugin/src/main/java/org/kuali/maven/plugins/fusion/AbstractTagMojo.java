/**
 * Copyright 2011-2014 The Kuali Foundation
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
package org.kuali.maven.plugins.fusion;

import java.io.File;
import java.util.List;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

/**
 * 
 * 
 */
public abstract class AbstractTagMojo extends AbstractMojo {

	MojoHelper helper = MojoHelper.getInstance(this);

	public abstract TagStyle getTagStyle();

	/**
	 * The prefix Maven needs in front of the real SCM url
	 * 
	 */
	@Parameter (property=FusionMavenPluginConstants.SCM_URL_PREFIX, defaultValue=FusionMavenPluginConstants.SCM_URL_PREFIX_DEFAULT)
	private String scmUrlPrefix;

	/**
	 * The location of a text file that contains the fusion-maven-plugin.dat definitions
	 * 
	 */
	@Parameter (property=FusionMavenPluginConstants.FUSION_DATA_FILE, defaultValue=FusionMavenPluginConstants.FUSION_DATA_FILE_DEFAULT)
	private File file;


	/**
	 * Filename pattern used to discover Maven pom's
	 * 
	 */
	@Parameter(property=FusionMavenPluginConstants.FUSION_POM, defaultValue=FusionMavenPluginConstants.FUSION_POM_DEFAULT)
	private String pom;

	/**
	 * Directories to ignore when examining the file system for Maven pom's
	 * 
	 */
	@Parameter(property=FusionMavenPluginConstants.FUSION_IGNORE_DIRS, defaultValue=FusionMavenPluginConstants.FUSION_IGNORE_DIRS_DEFAULT)
	private String ignoreDirectories;

	/**
	 * The Maven project object
	 * 
	 */
	@Component
	private MavenProject project;

	/**
	 * These mappings connect the svn:externals definitions with a property inside the root pom that controls what version each external is
	 * set to
	 * 
	 * @parameter
	 */
	@Parameter
	private List<Mapping> mappings;

	/**
	 * The property where the current build number is stored. Jenkins automatically sets an environment variable called
	 * <code>BUILD_NUMBER</code> each time a job is run
	 * 
	 */
	@Parameter(property=FusionMavenPluginConstants.FUSION_BUILD_NUMBER, defaultValue=FusionMavenPluginConstants.FUSION_BUILD_NUMBER_DEFAULT)
	private String buildNumberProperty;
	
	public List<Mapping> getMappings() {
		return mappings;
	}

	public void setMappings(List<Mapping> mappings) {
		this.mappings = mappings;
	}

	public MavenProject getProject() {
		return project;
	}

	
	public String getScmUrlPrefix() {
		return scmUrlPrefix;
	}

	public void setScmUrlPrefix(String scmUrlPrefix) {
		this.scmUrlPrefix = scmUrlPrefix;
	}

	public String getPom() {
		return pom;
	}

	public void setPom(String pom) {
		this.pom = pom;
	}

	public String getIgnoreDirectories() {
		return ignoreDirectories;
	}

	public void setIgnoreDirectories(String ignoreDirectories) {
		this.ignoreDirectories = ignoreDirectories;
	}

	public String getBuildNumberProperty() {
		return buildNumberProperty;
	}

	public void setBuildNumberProperty(String buildNumberProperty) {
		this.buildNumberProperty = buildNumberProperty;
	}

	
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

}
