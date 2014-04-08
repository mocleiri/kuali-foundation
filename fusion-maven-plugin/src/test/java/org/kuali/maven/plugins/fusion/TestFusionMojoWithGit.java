/**
 * 
 */
package org.kuali.maven.plugins.fusion;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.maven.execution.DefaultMavenExecutionRequest;
import org.apache.maven.execution.MavenExecutionRequest;
import org.apache.maven.plugin.testing.AbstractMojoTestCase;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.DefaultProjectBuilder;
import org.apache.maven.project.DefaultProjectBuildingRequest;
import org.apache.maven.project.MavenProject;
import org.apache.maven.project.ProjectBuilder;
import org.apache.maven.project.ProjectBuildingRequest;
import org.apache.maven.project.ProjectBuildingResult;
import org.apache.maven.repository.RepositorySystem;
import org.apache.maven.repository.internal.MavenRepositorySystemSession;
import org.eclipse.jgit.api.errors.CheckoutConflictException;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRefNameException;
import org.eclipse.jgit.api.errors.RefAlreadyExistsException;
import org.eclipse.jgit.api.errors.RefNotFoundException;
import org.eclipse.jgit.lib.Repository;
import org.junit.Test;
import org.kuali.student.git.model.GitRepositoryUtils;
import org.kuali.student.git.model.SvnExternalsUtils;
import org.kuali.student.git.model.branch.large.LargeBranchNameProviderMapImpl;
import org.kuali.student.svn.model.ExternalModuleInfo;

/**
 * @author ocleirig
 * 
 * A test case that will test the operation of the fusion mojo on a testing git repository
 *
 */
public class TestFusionMojoWithGit extends AbstractFusionPluginTestCase {

	private Repository repo;

	/**
	 * 
	 */
	public TestFusionMojoWithGit() {
		
	}

	
	@Override
	protected void onBefore() throws Exception {
		
		File repoDirectory;
		FileUtils.deleteDirectory(repoDirectory = new File ("target/fusion-mojo-with-git"));
		repo = GitRepositoryUtils.buildFileRepository(repoDirectory, true, false);
		
		
	}
	
	
	public void testFusion() throws Exception {
		
		GitTestRepositoryUtils.createFusionBaseStructure(repo);
		
		File pomFile = getTestFile(repo.getWorkTree().getAbsolutePath(), "pom.xml");
		
		assertNotNull(pomFile);
		
		assertTrue(pomFile.exists());
		
		FuseMojo mojo = (FuseMojo) lookupMojo(FusionMavenPluginConstants.FUSE_MOJO, pomFile);
		
		MavenProject project = readProjectFromPom(pomFile);

		mojo.project = project;
		
		mojo.checkoutFusedBranch = "true";
		
		assertNotNull(mojo);
		
		assertNotNull(mojo.mappings);
		
		assertEquals(2, mojo.mappings.size());
		
		mojo.execute();
		
		
		
	}
	
	public void testFusionDataFile() throws Exception {
		/*
		 * Test that we can fuse using a data file
		 */
		
		List<ExternalModuleInfo> externals = GitTestRepositoryUtils.createFusionBaseStructure(repo);
		
		File pomFile = getTestFile(repo.getWorkTree().getAbsolutePath(), "pom.xml");
		
		assertNotNull(pomFile);
		
		assertTrue(pomFile.exists());
		
		FuseMojo mojo = (FuseMojo) lookupMojo(FusionMavenPluginConstants.FUSE_MOJO, pomFile);
		
		MavenProject project = readProjectFromPom(pomFile);

		mojo.project = project;
		
		mojo.checkoutFusedBranch = "true";
		
		String dataFileContent = SvnExternalsUtils.createFusionMavenPluginDataFileString(-1L, repo, externals, new LargeBranchNameProviderMapImpl());
		
		File dataFile;
		FileUtils.writeStringToFile(dataFile = new File ("target", "test-fusion-mojo-fusion-maven-plugin.dat"), dataFileContent);
		
		mojo.fusionDataFile = dataFile;
				
		assertNotNull(mojo);
		
		assertNotNull(mojo.mappings);
		
		assertEquals(2, mojo.mappings.size());
		
		mojo.execute();
	}
	
	

}
