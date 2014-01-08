package org.kuali.common.util.metainf.model;

import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.kuali.common.util.log.LoggerUtils;
import org.slf4j.Logger;

import com.google.common.collect.Lists;

public class MetainfResourcePathComparatorTest {

	private static final Logger logger = LoggerUtils.make();

	@Test
	public void test() {
		logger.info("Testing MetaInfResourcePathComparator");
		String loc0 = "foo.txt";
		String loc1 = "/a/foo1.txt";
		String loc2 = "/a/foo2.txt";
		String loc3 = "/a/b/foo.txt";

		List<MetaInfResource> resources = Lists.newArrayList();
		resources.add(new MetaInfResource(loc2));
		resources.add(new MetaInfResource(loc0));
		resources.add(new MetaInfResource(loc3));
		resources.add(new MetaInfResource(loc1));

		Collections.sort(resources, new MetaInfResourcePathComparator());
		Assert.assertEquals(loc0, resources.get(0).getLocation());
		Assert.assertEquals(loc1, resources.get(1).getLocation());
		Assert.assertEquals(loc2, resources.get(2).getLocation());
		Assert.assertEquals(loc3, resources.get(3).getLocation());
	}

	@Test
	public void testEqualPaths() {
		String loc0 = "foo.txt";
		String loc1 = loc0;
		String loc2 = "foo1.txt";
		String loc3 = "foo2.txt";

		List<MetaInfResource> resources = Lists.newArrayList();
		resources.add(new MetaInfResource(loc2));
		resources.add(new MetaInfResource(loc0));
		resources.add(new MetaInfResource(loc3));
		resources.add(new MetaInfResource(loc1));

		Collections.sort(resources, new MetaInfResourcePathComparator());
		Assert.assertEquals(loc0, resources.get(0).getLocation());
		Assert.assertEquals(loc1, resources.get(1).getLocation());
		Assert.assertEquals(loc2, resources.get(2).getLocation());
		Assert.assertEquals(loc3, resources.get(3).getLocation());
	}

	@Test
	public void testCompare() {
		MetaInfResourcePathComparator meta = new MetaInfResourcePathComparator();
		try {
			meta.compare(1, new String[] { "" }, new String[] { "" });
			Assert.fail();
		} catch (IllegalArgumentException e) {
			logger.info(e.getMessage());
		}
		try {
			meta.compare(1, new String[] { "", "" }, new String[] { "" });
			Assert.fail();
		} catch (IllegalArgumentException e) {
			logger.info(e.getMessage());
		}
	}

	@Test
	public void testCompareTokens() {
		MetaInfResourcePathComparator meta = new MetaInfResourcePathComparator();
		Assert.assertEquals(1, meta.compare(new String[] { "a", "b" }, new String[] { "a" }));
		Assert.assertEquals(-1, meta.compare(new String[] { "a" }, new String[] { "a", "b" }));
		Assert.assertEquals(0, meta.compare(new String[] { "a" }, new String[] { "a" }));
	}

}
