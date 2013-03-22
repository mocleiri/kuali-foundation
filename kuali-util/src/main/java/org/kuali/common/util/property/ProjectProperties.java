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
package org.kuali.common.util.property;

import java.util.List;

import org.kuali.common.util.Project;

public class ProjectProperties implements Comparable<ProjectProperties> {

	Project project;
	int sequence;
	List<PropertiesLoaderContext> loaderContexts;

	@Override
	public int compareTo(ProjectProperties other) {
		return Double.compare(sequence, other.getSequence());
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public List<PropertiesLoaderContext> getLoaderContexts() {
		return loaderContexts;
	}

	public void setLoaderContexts(List<PropertiesLoaderContext> loaderContexts) {
		this.loaderContexts = loaderContexts;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

}