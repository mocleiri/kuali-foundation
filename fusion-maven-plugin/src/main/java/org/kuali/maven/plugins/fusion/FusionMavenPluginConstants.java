/**
 * Copyright 2014 The Kuali Foundation
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

/**
 * @author michael.ocleirigh
 *
 */
public final class FusionMavenPluginConstants {

	private FusionMavenPluginConstants() {
	}

	public static final String FUSE_MOJO = "fuse";
	
	public static final String SPLIT_MOJO = "split";
	
	public static final String TAG_MOJO = "tag";
	
	public static final String RELEASE_MOJO = "release";

	public static final String SHOW_MOJO = "show";
	
	public static final String COMMIT_MOJO = "commit";
	
	public static final String SCM_URL_PREFIX = "fusion.scmUrlPrefix";
	public static final String SCM_URL_PREFIX_DEFAULT = "scm:svn:";

	public static final String FUSION_DATA_FILE = "fusion.dataFile";
	public static final String FUSION_DATA_FILE_DEFAULT = "fusion-maven-plugin.dat";

	public static final String FUSION_POM = "fusion.pom";
	public static final String FUSION_POM_DEFAULT = "pom.xml";

	public static final String FUSION_IGNORE_DIRS = "fusion.ignoreDirectories";

	public static final String FUSION_IGNORE_DIRS_DEFAULT = "src,target,overlays,.git";

	public static final String FUSION_BUILD_NUMBER = "fusion.buildNumberProperty";
	public static final String FUSION_BUILD_NUMBER_DEFAULT = "env.BUILD_NUMBER";

	public static final String CHECKOUT_FUSED_BRANCH_PREFIX = "fusion.checkoutFusedBranch";
	public static final String CHECKOUT_FUSED_BRANCH_PREFIX_DEFAULT = "true";
	
	public static final String COMMIT_BEFORE_SPLITTING_FUSED_BRANCH_PREFIX = "fusion.commitBeforeSplit";
	public static final String COMMIT_BEFORE_SPLITTING_FUSED_BRANCH_PREFIX_DEFAULT = "true";
	
	public static final String AMMEND_FUSED_BRANCH_PREFIX = "fusion.commitBeforeSplit";
	public static final String AMMEND_BRANCH_PREFIX_DEFAULT = "true";
	
}
