/**
 * Copyright 2004-2013 The Kuali Foundation
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
package org.kuali.maven.plugin;

import org.apache.maven.model.Scm;
import org.junit.Assert;
import org.junit.Test;
import org.kuali.maven.common.Extractor;

/**
 *
 */
public class ExtractorMojoTest {

	@Test
	public void testGetScmUrl() {
		Extractor extractor = new Extractor();
		String dev = "scm:git:git@github.com:jcaddel/maven-s3-wagon.git";
		Scm scm = new Scm();
		scm.setDeveloperConnection(dev);
		String scmUrl = extractor.getScmUrl(scm);
		Assert.assertEquals("git@github.com:jcaddel/maven-s3-wagon.git", scmUrl);
	}
}
