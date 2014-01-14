package org.kuali.common.util.metainf.model;

import java.util.Comparator;

/**
 * <p>
 * Sort lexicographically by directory structure, then filename of the locations contained in each {@code MetaInfResource}
 * </p>
 * 
 * For example:
 * 
 * <pre>
 *   2 - /a/foo2.txt     1 - /a/foo1.txt
 *   3 - /a/b/foo.txt    2 - /a/foo2.txt
 *   1 - /a/foo1.txt     3 - /a/b/foo.txt
 * </pre>
 * 
 */
public class MetaInfResourcePathComparator implements Comparator<MetaInfResource> {

	private final PathComparator comparator = new PathComparator();

	@Override
	public int compare(MetaInfResource one, MetaInfResource two) {
		return comparator.compare(one.getLocation(), two.getLocation());
	}
}
