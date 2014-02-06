package org.kuali.common.util.metainf.model;

import org.junit.Assert;
import org.junit.Test;
import org.kuali.common.util.log.LoggerUtils;
import org.slf4j.Logger;

public class PathComparatorTest {

	private static final Logger logger = LoggerUtils.make();

	@Test
	public void testCompare() {
		PathComparator instance = new PathComparator();
		try {
			instance.compare(-1, new String[] { "" }, new String[] { "" });
			Assert.fail();
		} catch (IllegalArgumentException e) {
			logger.info(e.getMessage());
		}
		try {
			instance.compare(1, new String[] { "" }, new String[] { "" });
			Assert.fail();
		} catch (IllegalArgumentException e) {
			logger.info(e.getMessage());
		}
		try {
			instance.compare(1, new String[] { "", "" }, new String[] { "" });
			Assert.fail();
		} catch (IllegalArgumentException e) {
			logger.info(e.getMessage());
		}
	}

	@Test
	public void testCompareTokens() {
		PathComparator instance = new PathComparator();
		String[] two = { "a", "b" };
		String[] three = { "a", "b", "c" };
		Assert.assertEquals(-1, instance.compare(two, three));
		Assert.assertEquals(1, instance.compare(three, two));
	}
}
