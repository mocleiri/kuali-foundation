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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.swing.tree.DefaultMutableTreeNode;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.ListBranchCommand.ListMode;
import org.eclipse.jgit.api.errors.CheckoutConflictException;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRefNameException;
import org.eclipse.jgit.api.errors.RefAlreadyExistsException;
import org.eclipse.jgit.api.errors.RefNotFoundException;
import org.eclipse.jgit.lib.Ref;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.kuali.common.util.Version;
import org.kuali.common.util.VersionUtils;
import org.kuali.student.svn.model.AbstractGitRespositoryTestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Ignore
public class MojoHelperTest extends AbstractGitRespositoryTestCase {
	
	private static final Logger logger = LoggerFactory.getLogger(MojoHelperTest.class);
	SVNUtils svnUtils = SVNUtils.getInstance();

	MojoHelper helper = MojoHelper.getInstance(new LogOnlyTestMojo());
	private static final String POM = "pom.xml";
	private static final String IGNORE = "src,target,.svn,.git";

	
	/**
	 * @param name
	 * @param bare
	 */
	public MojoHelperTest() {
		super("mojo-helper-test");
	}

	@Test
	public void testIsKnownQualifier() {
		/*
		 * This used to fail but the new multi-part qualifier support doesn't care about ordering.
		 * 
		 * It detects the M6 part and will bump that up by 1.
		 */
		String version = "2.0.0-M6-CM20-SNAPSHOT";
		String nextVersion = "";
		
		boolean exception = false;
		try {
			nextVersion = helper.getNextVersion(version);
		} catch (IllegalArgumentException e) {
			exception = true;
		}
		
		Assert.assertEquals (true, exception);
		
		logger.info(nextVersion);
		Version v = VersionUtils.getVersion(version);
		String qualifier = v.getQualifier();
		logger.info(qualifier);
		Assert.assertFalse(helper.isKnownSubQualifier(qualifier));
	}
	
	private void assertVersionChange (String baseVersion, String expectedNextVersion, boolean assertEquals) {
		assertVersionChange(baseVersion, expectedNextVersion, assertEquals, false);
	}
	
	private void assertVersionChange (String baseVersion, String expectedNextVersion, boolean assertEquals, boolean throwException) {
			
		try {
			String nextVersion = helper.getNextVersion(baseVersion);
			
			if (assertEquals)
				Assert.assertEquals(expectedNextVersion, nextVersion);
			else
				Assert.assertNotEquals(expectedNextVersion, nextVersion);
		} catch (IllegalArgumentException e) {
			
			if (throwException)
				throw e;
			
			if (assertEquals) {
				Assert.fail (e.getMessage());
			}
		}
	}
	
	@Test
	public void testENRFoundersReleaseVersionScheme () {
		/*
		 * Verifies that the ENR founders release module naming scheme will work properly. 
		 */
		
		assertVersionChange("2.0.0-M9-SNAPSHOT", "2.0.0-M10-SNAPSHOT", false);

		assertVersionChange("2.0.0-M6-CM20-SNAPSHOT", "2.0.0-M7-CM20-SNAPSHOT", false);
		
		boolean failed = false;
		try {
			assertVersionChange("2.0.0-FR1-RC-SNAPSHOT", "2.0.0-FR1-RC1-SNAPSHOT", true, true);
		} catch (IllegalArgumentException e) {
			failed = true;
		}
		
		Assert.assertTrue("Should fail if the qualifier is missing a number", failed);
		
		assertVersionChange("2.1.0-FR2-M10-SNAPSHOT", "2.1.0-FR2-M11-SNAPSHOT", true);
		
		assertVersionChange("2.1.0-fr2-M10-SNAPSHOT", "2.1.0-fr2-M11-SNAPSHOT", true);
		
		assertVersionChange("2.1.0-fr2-m10-SNAPSHOT", "2.1.0-fr2-m11-SNAPSHOT", true);
		
		assertVersionChange("2.0.0-FR1-SNAPSHOT", "2.0.0-FR2-SNAPSHOT", true);
		
		assertVersionChange("2.0.0-fr1-SNAPSHOT", "2.0.0-fr2-SNAPSHOT", true);
		
		assertVersionChange("2.0.0-M9-FR1-SNAPSHOT", "2.0.0-M10-FR1-SNAPSHOT", false);
	}

	@Test
	public void testKSAPVersionScheme () {
		
		assertVersionChange("2.0.0-M8-KSAP-SNAPSHOT", "2.0.0-M9-KSAP-SNAPSHOT", false);
		
		assertVersionChange("2.0.0-KSAP-M8-SNAPSHOT", "2.0.0-KSAP-M9-SNAPSHOT", false);
		
	}
	
	@Test
	public void testOtherVersions () {
		
		assertVersionChange("2.0.0-SNAPSHOT", "2.0.1-SNAPSHOT", true);
		
	}
	
	
	@Test
	public void testOnTemporaryRepository () throws GitAPIException, IOException {
		
		GitTestRepositoryUtils.createFusionBaseStructure(this.repo);
		
		List<Ref> branches = GitTestRepositoryUtils.listBranches(repo, ListMode.ALL);
		
		Assert.assertNotNull (branches);
		
		Assert.assertEquals(4, branches.size());
		
		/*
		 * Would use the fusion plugin here
		 */
		
//		File workingCopy = GitTestRepositoryUtils.checkOut("test-repository", "aggregate/trunk", null, null);
//		
//		logger.info ("workingCopy = " + workingCopy.getAbsolutePath());
//		
//		// check that the modules materialized properly
//		
//		File module1 = new File (workingCopy, "module1");
//		
//		Assert.assertEquals (true, module1.exists());
//		
//		File module2 = new File (workingCopy, "module2");
//		
//		Assert.assertEquals (true, module2.exists());
		
		
	}
	
	@Test
	public void testIsReleaseCandidate() {
		String s1 = "rc1";
		String s2 = "Rc1";
		String s3 = "RC1";
		String s4 = "rC2";

		Assert.assertTrue(helper.isKnownSubQualifier(s1));
		Assert.assertTrue(helper.isKnownSubQualifier(s2));
		Assert.assertTrue(helper.isKnownSubQualifier(s3));
		Assert.assertTrue(helper.isKnownSubQualifier(s4));

	}

	@Test
	public void testValidate() throws RefAlreadyExistsException, RefNotFoundException, InvalidRefNameException, CheckoutConflictException, IOException, GitAPIException {
		
		GitTestRepositoryUtils.createFusionBaseStructure(this.repo);
		
			List<File> poms = helper.getPoms(repo.getWorkTree(), POM, IGNORE);
			List<DefaultMutableTreeNode> nodes = helper.getNodes(poms);
			DefaultMutableTreeNode tree = helper.getTree(repo.getWorkTree(), nodes, POM);
			helper.fillInGavs(tree);
	}

	 @Test
	public void testUpdateVersions() throws RefAlreadyExistsException, RefNotFoundException, InvalidRefNameException, CheckoutConflictException, IOException, GitAPIException {
		 
		 GitTestRepositoryUtils.createFusionBaseStructure(this.repo);
		 
		int buildNumber = 100;
		GAV gav = new GAV("org.kuali.student", "student", "2.0.0-SNAPSHOT");
		List<Mapping> mappings = new ArrayList<Mapping>();
		mappings.add(new Mapping("ks-api", "ks-api", "ks.api.version"));
		mappings.add(new Mapping("ks-core", "ks-core", "ks.core.version"));
		mappings.add(new Mapping("ks-enroll", "ks-enroll", "ks.enroll.version"));
		mappings.add(new Mapping("ks-lum", "ks-lum", "ks.lum.version"));
		mappings.add(new Mapping("ks-deployments", "ks-deployments", "ks.deployments.version"));

		Properties properties = new Properties();
		properties.setProperty("ks.api.version", "2.0.0-M5-SNAPSHOT");
		properties.setProperty("ks.core.version", "2.0.0-SNAPSHOT");
		properties.setProperty("ks.enroll.version", "1.0.0-SNAPSHOT");
		properties.setProperty("ks.lum.version", "2.0.0-SNAPSHOT");
		properties.setProperty("ks.deployments.version", "2.0.0-SNAPSHOT");
		
		/*
		 * TODO: make sure this works once fusion is turned on.
		 */

//		List<File> files = helper.getPoms(BASEDIR, POM, IGNORE);
//		List<DefaultMutableTreeNode> nodes = helper.getNodes(files);
//		DefaultMutableTreeNode node = helper.getTree(BASEDIR, nodes, POM);
////		List<SVNExternal> externals = svnUtils.getExternals(BASEDIR);
//		BuildTag rootTag = helper.getBuildTag(BASEDIR, gav, TagStyle.BUILDNUMBER, buildNumber);
//		helper.updateBuildInfo(node, rootTag, TagStyle.BUILDNUMBER, buildNumber);
//		List<BuildTag> moduleTags = helper.getBuildTags(properties, null, mappings, TagStyle.REVISION, buildNumber);
//		helper.updateBuildInfo(nodes, moduleTags, mappings, TagStyle.REVISION, buildNumber);
//		helper.updateGavs(node);
//		helper.updateProperties(node, properties, mappings);
//		logger.info("\n" + helper.getDisplayString(node));
	}

	 @Test
	public void testGetTree() throws RefAlreadyExistsException, RefNotFoundException, InvalidRefNameException, CheckoutConflictException, IOException, GitAPIException {
		 
		 GitTestRepositoryUtils.createFusionBaseStructure(this.repo);
		 
			List<File> poms = helper.getPoms(repo.getWorkTree(), POM, IGNORE);
			List<DefaultMutableTreeNode> nodes = helper.getNodes(poms);
			DefaultMutableTreeNode tree = helper.getTree(repo.getWorkTree(), nodes, POM);
			String s = helper.getDisplayString(tree);
			logger.info("Maven Structure:\n" + s);
	}

	 @Test
	public void testGetFiles() throws RefAlreadyExistsException, RefNotFoundException, InvalidRefNameException, CheckoutConflictException, IOException, GitAPIException {
		 
		 GitTestRepositoryUtils.createFusionBaseStructure(this.repo);
		 
			List<File> poms = helper.getPoms(repo.getWorkTree(), POM, IGNORE);
			List<DefaultMutableTreeNode> nodes = helper.getNodes(poms);
			DefaultMutableTreeNode tree = helper.getTree(repo.getWorkTree(), nodes, POM);
			String s = helper.getDisplayString(tree, repo.getWorkTree(), POM);
			logger.info("Maven Structure:\n" + s);
	}
}
