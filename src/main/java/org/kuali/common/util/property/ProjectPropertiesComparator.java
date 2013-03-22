package org.kuali.common.util.property;

import java.util.Comparator;
import java.util.List;

import org.kuali.common.util.Assert;
import org.kuali.common.util.Project;
import org.springframework.util.CollectionUtils;

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
		Project p = pp.getProject();
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
