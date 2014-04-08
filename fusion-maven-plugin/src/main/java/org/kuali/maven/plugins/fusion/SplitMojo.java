/**
 * 
 */
package org.kuali.maven.plugins.fusion;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

/**
 * @author ocleirig
 *
 */
public class SplitMojo extends AbstractFusionMojo {

	/**
	 * 
	 */
	public SplitMojo() {
		super();
	}

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		getLog().info("execute");
		
	}

	

}
