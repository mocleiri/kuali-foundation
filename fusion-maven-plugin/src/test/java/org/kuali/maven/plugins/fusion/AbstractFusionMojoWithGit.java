/**
 * 
 */
package org.kuali.maven.plugins.fusion;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.eclipse.jgit.lib.Repository;
import org.kuali.student.git.model.GitRepositoryUtils;

/**
 * @author ocleirig
 *
 */
public abstract class AbstractFusionMojoWithGit extends AbstractFusionPluginTestCase {

	private String testName;

	/**
	 * 
	 */
	public AbstractFusionMojoWithGit(String testName) {
		this.testName = testName;
	}

	protected Repository repo;
	
	@Override
	protected void onBefore() throws Exception {
		
		File repoDirectory;
		FileUtils.deleteDirectory(repoDirectory = new File ("target/" + testName));
		repo = GitRepositoryUtils.buildFileRepository(repoDirectory, true, false);
		
		
	}

	@Override
	protected void onAfter() throws Exception {
		repo.close();
	}
	
	
	
}
