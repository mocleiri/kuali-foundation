/**
 * Copyright 2010-2013 The Kuali Foundation
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
package org.kuali.common.util;

import junit.framework.Assert;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Deprecated
public class RepositoryUtilsTest {

	private static final Logger logger = LoggerFactory.getLogger(RepositoryUtilsTest.class);

	@Test
	public void testArtifact() {
		Artifact a = new Artifact();
		a.setGroupId("org.kuali.common");
		a.setArtifactId("kuali-util");
		a.setVersion("1.0.0");
		a.setClassifier("webapp");
		a.setType("jar");
		String gav1 = RepositoryUtils.toString(a);
		Artifact parsed = RepositoryUtils.parseArtifact(gav1);
		String gav2 = RepositoryUtils.toString(parsed);
		logger.info("gav1={}", gav1);
		logger.info("gav2={}", gav2);
		Assert.assertEquals(gav1, gav2);
	}

	@Test
	public void testEmptyArtifact() {
		Artifact a = new Artifact();
		String gav1 = RepositoryUtils.toString(a);
		Artifact parsed = RepositoryUtils.parseArtifact(gav1);
		String gav2 = RepositoryUtils.toString(parsed);
		logger.info("gav1={}", gav1);
		logger.info("gav2={}", gav2);
		Assert.assertEquals(gav1, gav2);
	}

	@Test
	public void testGroupIdArtifact() {
		Artifact a = new Artifact();
		a.setGroupId("org.kuali.common");
		String gav1 = RepositoryUtils.toString(a);
		Artifact parsed = RepositoryUtils.parseArtifact(gav1);
		String gav2 = RepositoryUtils.toString(parsed);
		logger.info("gav1={}", gav1);
		logger.info("gav2={}", gav2);
		Assert.assertEquals(gav1, gav2);
	}

	@Test
	public void testTypeArtifact() {
		Artifact a = new Artifact();
		a.setType("jar");
		String gav1 = RepositoryUtils.toString(a);
		Artifact parsed = RepositoryUtils.parseArtifact(gav1);
		String gav2 = RepositoryUtils.toString(parsed);
		logger.info("gav1={}", gav1);
		logger.info("gav2={}", gav2);
		Assert.assertEquals(gav1, gav2);
	}
}
