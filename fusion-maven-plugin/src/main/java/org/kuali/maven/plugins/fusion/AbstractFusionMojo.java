/**
 * 
 */
package org.kuali.maven.plugins.fusion;

import java.io.File;
import java.util.List;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

/**
 * @author ocleirig
 *
 */
public abstract class AbstractFusionMojo extends AbstractMojo {

	/**
	 * The prefix Maven needs in front of the real SCM url
	 * 
	 */
	@Parameter (property=FusionMavenPluginConstants.SCM_URL_PREFIX, defaultValue=FusionMavenPluginConstants.SCM_URL_PREFIX_DEFAULT)
	protected String scmUrlPrefix;

	/**
	 * The location of a text file that contains the fusion-maven-plugin.dat definitions
	 * 
	 */
	@Parameter (property=FusionMavenPluginConstants.FUSION_DATA_FILE, defaultValue=FusionMavenPluginConstants.FUSION_DATA_FILE_DEFAULT)
	protected File fusionDataFile;


	/**
	 * Filename pattern used to discover Maven pom's
	 * 
	 */
	@Parameter(property=FusionMavenPluginConstants.FUSION_POM, defaultValue=FusionMavenPluginConstants.FUSION_POM_DEFAULT)
	protected String pom;

	/**
	 * Directories to ignore when examining the file system for Maven pom's
	 * 
	 */
	@Parameter(property=FusionMavenPluginConstants.FUSION_IGNORE_DIRS, defaultValue=FusionMavenPluginConstants.FUSION_IGNORE_DIRS_DEFAULT)
	protected String ignoreDirectories;

	/**
	 * The Maven project object
	 * 
	 */
	@Parameter(property="project", defaultValue="${project}")
	protected MavenProject project;

	/**
	 * These mappings connect the svn:externals definitions with a property inside the root pom that controls what version each external is
	 * set to
	 * 
	 * @parameter
	 */
	@Parameter
	protected List<Mapping> mappings;

	/**
	 * The property where the current build number is stored. Jenkins automatically sets an environment variable called
	 * <code>BUILD_NUMBER</code> each time a job is run
	 * 
	 */
	@Parameter(property=FusionMavenPluginConstants.FUSION_BUILD_NUMBER, defaultValue=FusionMavenPluginConstants.FUSION_BUILD_NUMBER_DEFAULT)
	protected String buildNumberProperty;
	
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
		return fusionDataFile;
	}

	public void setFile(File file) {
		this.fusionDataFile = file;
	}
	
	/**
	 * 
	 */
	public AbstractFusionMojo() {
		super();
		
	}

	
}
