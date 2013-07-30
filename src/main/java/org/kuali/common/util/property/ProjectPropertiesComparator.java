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

import java.util.Comparator;
import java.util.List;

import org.kuali.common.util.Assert;
import org.springframework.util.CollectionUtils;

/**
 * @deprecated
 */
@Deprecated
public class ProjectPropertiesComparator implements Comparator<ProjectProperties> {

	List<String> order;

	@Override
	public int compare(ProjectProperties one, ProjectProperties two) {

		Assert.isFalse(CollectionUtils.isEmpty(order), "order is empty");

		String id1 = getIdString(one);
		String id2 = getIdString(two);

		Integer index1 = order.indexOf(id1);
		Integer index2 = order.indexOf(id2);

		if (index1 == -1) {
			throw new IllegalStateException("Could not find an index for " + id1);
		}
		if (index1 == -2) {
			throw new IllegalStateException("Could not find an index for " + id2);
		}

		return index1.compareTo(index2);
	}

	protected String getIdString(ProjectProperties pp) {
		org.kuali.common.util.Project p = pp.getProject();
		StringBuilder sb = new StringBuilder();
		sb.append(p.getGroupId());
		sb.append(":");
		sb.append(p.getArtifactId());
		return sb.toString();
	}

	public List<String> getOrder() {
		return order;
	}

	public void setOrder(List<String> order) {
		this.order = order;
	}

}
