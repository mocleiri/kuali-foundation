/**
 * Copyright 2011-2012 The Kuali Foundation
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
package org.kuali.maven.plugins.externals;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.swing.tree.DefaultMutableTreeNode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MojoHelperTest {
	private static final Logger logger = LoggerFactory.getLogger(SVNUtilsTest.class);
	SVNUtils svnUtils = SVNUtils.getInstance();

	MojoHelper helper = MojoHelper.getInstance();
	private static final String POM = "pom.xml";
	private static final String IGNORE = "src,target,.svn,.git";
	private static final File BASEDIR = new File("/Users/jeffcaddel/ws/aggregate");

	// @Test
	public void testUpdateVersions() {
		int buildNumber = 100;
		GAV rootGav = new GAV("org.kuali.student", "student", "2.0.0-SNAPSHOT");
		List<Mapping> mappings = new ArrayList<Mapping>();
		mappings.add(new Mapping("ks-api", "ks.api.version"));
		mappings.add(new Mapping("ks-core", "ks.core.version"));
		mappings.add(new Mapping("ks-enroll", "ks.enroll.version"));
		mappings.add(new Mapping("ks-lum", "ks.lum.version"));
		mappings.add(new Mapping("ks-deployments", "ks.deployments.version"));

		Properties properties = new Properties();
		properties.setProperty("ks.api.version", "2.0.0-M5-SNAPSHOT");
		properties.setProperty("ks.core.version", "2.0.0-SNAPSHOT");
		properties.setProperty("ks.enroll.version", "1.0.0-SNAPSHOT");
		properties.setProperty("ks.lum.version", "2.0.0-SNAPSHOT");
		properties.setProperty("ks.deployments.version", "2.0.0-SNAPSHOT");

		List<File> files = helper.getPoms(BASEDIR, POM, IGNORE);
		List<DefaultMutableTreeNode> nodes = helper.getNodes(files);
		DefaultMutableTreeNode node = helper.getTree(BASEDIR, nodes, POM);
		List<SVNExternal> externals = svnUtils.getExternals(BASEDIR);
		BuildTag rootTag = helper.getBuildTag(rootGav, BASEDIR, rootGav, TagStyle.BUILDNUMBER, buildNumber);
		helper.updateBuildInfo(rootGav, node, rootTag, TagStyle.BUILDNUMBER, buildNumber);
		List<BuildTag> moduleTags = helper.getBuildTags(rootGav, properties, externals, mappings, TagStyle.REVISION, buildNumber);
		helper.updateBuildInfo(rootGav, nodes, moduleTags, mappings, TagStyle.REVISION, buildNumber);
		helper.updateGavs(node);
		helper.updateProperties(node, properties, mappings);
		logger.info("\n" + helper.getDisplayString(node));
	}

	// @Test
	public void testGetTree() {
		try {
			List<File> poms = helper.getPoms(BASEDIR, POM, IGNORE);
			List<DefaultMutableTreeNode> nodes = helper.getNodes(poms);
			DefaultMutableTreeNode tree = helper.getTree(BASEDIR, nodes, POM);
			String s = helper.getDisplayString(tree);
			logger.info("Maven Structure:\n" + s);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// @Test
	public void testGetFiles() {
		try {
			List<File> poms = helper.getPoms(BASEDIR, POM, IGNORE);
			List<DefaultMutableTreeNode> nodes = helper.getNodes(poms);
			DefaultMutableTreeNode tree = helper.getTree(BASEDIR, nodes, POM);
			String s = helper.getDisplayString(tree, BASEDIR, POM);
			logger.info("Maven Structure:\n" + s);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
