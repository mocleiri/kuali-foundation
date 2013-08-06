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
package org.kuali.common.util.project;

import java.util.Properties;

import org.kuali.common.util.project.model.Project;
import org.kuali.common.util.project.model.ProjectIdentifier;

public interface ProjectService {

	/**
	 * Create a <code>Project</code> from <code>identifier</code>
	 */
	Project getProject(ProjectIdentifier identifier);

	/**
	 * Create a <code>Project</code> from <code>groupId</code> and <code>artifactId</code>
	 */
	Project getProject(String groupId, String artifactId);

	/**
	 * Create a <code>Project</code> from <code>properties</code>
	 */
	Project getProject(Properties properties);

}
