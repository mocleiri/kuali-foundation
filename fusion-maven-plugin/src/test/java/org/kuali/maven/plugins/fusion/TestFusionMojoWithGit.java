/**
 * 
 */
package org.kuali.maven.plugins.fusion;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.maven.project.MavenProject;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.junit.Assert;
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
	
	
	public void testSplitMojo() throws Exception {
		
		testFusion();
		
		File pomFile = getTestFile(repo.getWorkTree().getAbsolutePath(), "pom.xml");
		
		assertNotNull(pomFile);
		
		assertTrue(pomFile.exists());
		
		SplitMojo mojo = (SplitMojo) lookupMojo(FusionMavenPluginConstants.SPLIT_MOJO, pomFile);
		
		MavenProject project = readProjectFromPom(pomFile);

		mojo.project = project;
		
		mojo.amend = "true";
		
		mojo.commitBeforeSplit = "true";
		
		assertNotNull(mojo);
		
		assertNotNull(mojo.mappings);
		
		assertEquals(2, mojo.mappings.size());
		
		// use the GAV to set the pom versions.
		
		mojo.execute();
		
		Ref splitModule1 = repo.getRef(Constants.R_HEADS + "split_module1");
		
		Ref splitModule2 = repo.getRef(Constants.R_HEADS + "split_module2");
		
		Assert.assertNotNull(splitModule1);
		
		Assert.assertNotNull(splitModule2);
		
		
		
	}
	

}
