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
import java.util.Map;

import javax.swing.tree.DefaultMutableTreeNode;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.Execute;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

/**
 * Make sure the aggregate checkout is "self-contained". Make sure the parent versions of the svn:externals modules point back to the correct root pom. Make sure the properties
 * used to figure out which version of the child modules to use, actually match up with the versions declared in the child modules.
 * 
 */
@Mojo(name="validatepoms")
@Execute(goal="validatepoms")
public class ValidatePomsMojo extends AbstractMojo {

	SVNUtils svnUtils = SVNUtils.getInstance();
	MojoHelper helper = MojoHelper.getInstance(this);

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
	 * These mappings connect the svn:externals definitions with a property inside the root pom that controls what version each external is set to
	 * 
	 */
	@Parameter
	private List<Mapping> mappings;

	@Override
	public void execute() throws MojoExecutionException {
		List<File> files = helper.getPoms(project.getBasedir(), pom, ignoreDirectories);
		List<DefaultMutableTreeNode> nodes = helper.getNodes(files);
		// Since this tree is based on the file system directory structure, it should always be a perfect tree
		DefaultMutableTreeNode node = helper.getTree(project.getBasedir(), nodes, pom);
		// Make sure each GAV is fully populated
		helper.fillInGavs(node);
		// Populate a map keyed by the GAV id
		Map<String, DefaultMutableTreeNode> map = helper.getGavMap(node);
		// Validate that all of the parents are contained in the map
		helper.validateParents(node, map);
		helper.validateMappings(project.getProperties(), mappings, node);
		int depth = node.getDepth();
		int size = nodes.size();
		getLog().info("Validated " + size + " POM's.  Multi-module Maven project depth: " + depth);
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

	public MavenProject getProject() {
		return project;
	}

	public void setProject(MavenProject project) {
		this.project = project;
	}

	public List<Mapping> getMappings() {
		return mappings;
	}

	public void setMappings(List<Mapping> mappings) {
		this.mappings = mappings;
	}
}
