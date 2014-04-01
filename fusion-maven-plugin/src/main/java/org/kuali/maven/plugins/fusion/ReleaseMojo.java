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

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Execute;
import org.apache.maven.plugins.annotations.Mojo;
import org.kuali.common.util.maven.MavenConstants;

/**
 * Perform a release of a multi-module Maven project that contains svn:externals. The release process creates a separate tag for each module
 * defined as an svn:external after trimming SNAPSHOT off the version number. It then increments the version number in the root poms and
 * re-appends the SNAPSHOT qualifier before committing the altered poms.
 *
 */
@Mojo (name=FusionMavenPluginConstants.RELEASE_MOJO, threadSafe=true)
@Execute (goal=FusionMavenPluginConstants.RELEASE_MOJO)
public class ReleaseMojo extends AbstractTagMojo {

	@Override
	public TagStyle getTagStyle() {
		return TagStyle.RELEASE;
	}

	@Override
	public void execute() throws MojoExecutionException {
		helper.createAndUpdateTags(this);
		getLog().info("Release has been successfully tagged.");
		getLog().info("Incrementing version numbers for the next development iteration");
		helper.incrementVersions(this);
	}

}
