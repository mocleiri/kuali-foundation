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
package org.kuali.common.devops.project;

import static org.kuali.common.util.project.KualiProjectConstants.COMMON_GROUP_ID;

import org.kuali.common.util.project.model.ProjectIdentifier;

public final class KualiDevOpsProjectConstants {

	private KualiDevOpsProjectConstants() {
	}

	private static final String ARTIFACT_ID = "kuali-devops";

	// This information must exactly match what is in the Maven pom
	public static final ProjectIdentifier KUALI_DEVOPS_PROJECT_IDENTIFIER = new ProjectIdentifier(COMMON_GROUP_ID, ARTIFACT_ID);

}
