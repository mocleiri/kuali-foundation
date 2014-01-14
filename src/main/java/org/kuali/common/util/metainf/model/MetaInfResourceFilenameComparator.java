package org.kuali.common.util.metainf.model;

import java.util.Comparator;

import org.kuali.common.util.LocationUtils;
import org.springframework.core.io.Resource;

/**
 * Compare 2 MetaInfResource objects based on the filename portion of the location they contain.
 */
public class MetaInfResourceFilenameComparator implements Comparator<MetaInfResource> {

	@Override
	public int compare(MetaInfResource one, MetaInfResource two) {
		Resource res1 = LocationUtils.getResource(one.getLocation());
		Resource res2 = LocationUtils.getResource(two.getLocation());
		String filename1 = res1.getFilename();
		String filename2 = res2.getFilename();
		return filename1.compareTo(filename2);
	}

}
