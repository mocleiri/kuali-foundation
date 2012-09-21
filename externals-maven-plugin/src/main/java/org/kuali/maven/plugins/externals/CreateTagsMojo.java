package org.kuali.maven.plugins.externals;

import java.util.List;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;

/**
 * 
 * @goal createtags
 */
public class CreateTagsMojo extends AbstractMojo {

	SVNUtils svnUtils = SVNUtils.getInstance();

	/**
	 * The Maven project object
	 * 
	 * @parameter expression="${project}"
	 * @readonly
	 */
	private MavenProject project;

	/**
	 * 
	 */
	private List<Mapping> mappings;

	@Override
	public void execute() throws MojoExecutionException {
		String url = svnUtils.getUrl(project.getBasedir());
		getLog().info(url);
		List<SVNExternal> externals = svnUtils.getExternals(project.getBasedir());
		svnUtils.showExternals(externals);
		getLog().info("externals: " + externals.size());
		getLog().info("mappings: " + mappings.size());
	}

	public List<Mapping> getMappings() {
		return mappings;
	}

	public void setMappings(List<Mapping> mappings) {
		this.mappings = mappings;
	}

	public MavenProject getProject() {
		return project;
	}

}
