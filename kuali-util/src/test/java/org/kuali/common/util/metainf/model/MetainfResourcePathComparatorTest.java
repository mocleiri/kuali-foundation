package org.kuali.common.util.metainf.model;

import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.kuali.common.util.log.LoggerUtils;
import org.slf4j.Logger;

import com.google.common.collect.Lists;

public class MetainfResourcePathComparatorTest {

	private static final Logger logger = LoggerUtils.make();

	@Test
	public void test() {
		try {
			List<MetaInfResource> resources = Lists.newArrayList();
			resources.add(new MetaInfResource("/a/foo/bar/file1.txt"));
			resources.add(new MetaInfResource("/b/foo/bar/baz/file.txt"));
			resources.add(new MetaInfResource("/a/foo/bar/file2.txt"));
			resources.add(new MetaInfResource("/b/foo/file2.txt"));
			resources.add(new MetaInfResource("/a/foo/file2.txt"));
			resources.add(new MetaInfResource("/a/foo/bar/baz/file.txt"));
			resources.add(new MetaInfResource("/b/foo/bar/file1.txt"));
			resources.add(new MetaInfResource("/a/foo/file1.txt"));
			resources.add(new MetaInfResource("/b/foo/bar/file2.txt"));
			resources.add(new MetaInfResource("/b/foo/file1.txt"));

			Collections.sort(resources, new MetaInfResourcePathComparator());

			for (MetaInfResource resource : resources) {
				logger.info(resource.getLocation());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
