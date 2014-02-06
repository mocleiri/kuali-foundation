package org.kuali.common.util.metainf.model;

import java.util.Comparator;

/**
 * Compare 2 MetaInfResource objects based on the location they contain.
 */
public class MetaInfResourceLocationComparator implements Comparator<MetaInfResource> {

	@Override
	public int compare(MetaInfResource one, MetaInfResource two) {
		return one.getLocation().compareTo(two.getLocation());
	}

}
