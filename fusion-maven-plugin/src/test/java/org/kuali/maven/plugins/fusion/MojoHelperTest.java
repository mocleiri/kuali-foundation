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
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import javax.swing.tree.DefaultMutableTreeNode;

import org.eclipse.jgit.api.errors.CheckoutConflictException;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRefNameException;
import org.eclipse.jgit.api.errors.RefAlreadyExistsException;
import org.eclipse.jgit.api.errors.RefNotFoundException;
import org.junit.Assert;
import org.kuali.common.util.Version;
import org.kuali.common.util.VersionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MojoHelperTest extends AbstractFusionMojoWithGit {
	
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


	public void testKSAPVersionScheme () {
		
		assertVersionChange("2.0.0-M8-KSAP-SNAPSHOT", "2.0.0-M9-KSAP-SNAPSHOT", false);
		
		assertVersionChange("2.0.0-KSAP-M8-SNAPSHOT", "2.0.0-KSAP-M9-SNAPSHOT", false);
		
	}
	

	public void testOtherVersions () {
		
		assertVersionChange("2.0.0-SNAPSHOT", "2.0.1-SNAPSHOT", true);
		
	}
	

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


	private GAV getGAV (DefaultMutableTreeNode node) {
		Project project = (Project) node.getUserObject();
		GAV gav = project.getGav();
		
		return gav;
	}
	
	public void testValidate() throws Exception {
		
			GitTestRepositoryUtils.createFusedBaseStructure(this.repo);
		
			List<File> poms = helper.getPoms(repo.getWorkTree().getAbsoluteFile(), POM, IGNORE);
			List<DefaultMutableTreeNode> nodes = helper.getNodes(poms);
			DefaultMutableTreeNode tree = helper.getTree(repo.getWorkTree().getAbsoluteFile(), nodes, POM);
			
			// check that the group ids are null on the modules
			
			Enumeration<DefaultMutableTreeNode> enumeration = tree.children();
			
			Assert.assertEquals(true, enumeration.hasMoreElements());
			
			DefaultMutableTreeNode module1Node = (DefaultMutableTreeNode) enumeration.nextElement();
			
			Assert.assertEquals(true, enumeration.hasMoreElements());
			Assert.assertNull(getGAV(module1Node).getGroupId());
			
			DefaultMutableTreeNode module2Node = (DefaultMutableTreeNode) enumeration.nextElement();
			
			Assert.assertNull(getGAV(module2Node).getGroupId());
			
			helper.fillInGavs(tree);
			
			// check that the group ids are set on the modules
			
			Assert.assertEquals("org.kuali.maven.plugins", getGAV(module1Node).getGroupId());
			Assert.assertEquals("org.kuali.maven.plugins", getGAV(module2Node).getGroupId());
			
	}

	 
	public void testUpdateVersions() throws RefAlreadyExistsException, RefNotFoundException, InvalidRefNameException, CheckoutConflictException, IOException, GitAPIException {
		 
		GitTestRepositoryUtils.createFusedBaseStructure(this.repo);
		
		 
		int buildNumber = 100;
		GAV gav = new GAV("org.kuali.student", "student", "2.0.0-SNAPSHOT");
		List<Mapping> mappings = new ArrayList<Mapping>();
		mappings.add(new Mapping("module1", "module1", "module1.version"));
		mappings.add(new Mapping("module2", "module2", "module2.version"));

		Properties properties = new Properties();
		properties.setProperty("module1.version", "2.0.0-M5-SNAPSHOT");
		properties.setProperty("module2.version", "2.0.0-SNAPSHOT");
		
		File baseDir = repo.getWorkTree().getAbsoluteFile();
		
		List<File> files = helper.getPoms(baseDir, POM, IGNORE);
		List<DefaultMutableTreeNode> nodes = helper.getNodes(files);
		DefaultMutableTreeNode node = helper.getTree(baseDir, nodes, POM);
		BuildTag rootTag = helper.getBuildTag(baseDir, gav, TagStyle.BUILDNUMBER, buildNumber);
		helper.updateBuildInfo(node, rootTag, TagStyle.BUILDNUMBER, buildNumber);
		List<BuildTag> moduleTags = helper.getBuildTags(properties, mappings, TagStyle.BUILDNUMBER, buildNumber);
		
		assertEquals(2, moduleTags.size());
		
		BuildTag module1Tag = moduleTags.get(0);
		
		assertEquals("module1-2.0/2.0.0-M5/build-100", module1Tag.getName());
		
		BuildTag module2Tag = moduleTags.get(1);
		
		assertEquals("module2-2.0/2.0.0/build-100", module2Tag.getName());
		
		helper.updateBuildInfo(nodes, moduleTags, mappings, TagStyle.BUILDNUMBER, buildNumber);
		helper.updateGavs(node);
		helper.updateProperties(node, properties, mappings);
		
		// note this test does not effect the change into the fused aggregate.
		logger.info("\n" + helper.getDisplayString(node));
	}

	 
	public void testGetTree() throws RefAlreadyExistsException, RefNotFoundException, InvalidRefNameException, CheckoutConflictException, IOException, GitAPIException {
		 
		 GitTestRepositoryUtils.createFusionBaseStructure(this.repo);
		 
			List<File> poms = helper.getPoms(repo.getWorkTree(), POM, IGNORE);
			List<DefaultMutableTreeNode> nodes = helper.getNodes(poms);
			DefaultMutableTreeNode tree = helper.getTree(repo.getWorkTree().getAbsoluteFile(), nodes, POM);
			String s = helper.getDisplayString(tree);
			logger.info("Maven Structure:\n" + s);
	}

	 
	public void testGetFiles() throws RefAlreadyExistsException, RefNotFoundException, InvalidRefNameException, CheckoutConflictException, IOException, GitAPIException {
		 
		 GitTestRepositoryUtils.createFusedBaseStructure(this.repo);
		 
			List<File> poms = helper.getPoms(repo.getWorkTree().getAbsoluteFile(), POM, IGNORE);
			List<DefaultMutableTreeNode> nodes = helper.getNodes(poms);
			DefaultMutableTreeNode tree = helper.getTree(repo.getWorkTree().getAbsoluteFile(), nodes, POM);
			String s = helper.getDisplayString(tree, repo.getWorkTree(), POM);
			logger.info("Maven Structure:\n" + s);
	}
}
