package org.kuali.common.util.metainf.model;

import static org.kuali.common.util.log.Loggers.newLogger;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.kuali.common.util.metainf.spring.MetaInfDataLocation;
import org.kuali.common.util.metainf.spring.MetaInfDataType;
import org.slf4j.Logger;

import com.google.common.collect.Lists;

public class MetaInfResourceConfigurablePathComparatorTest {

	private static final Logger logger = newLogger();

	private static final List<String> qualifierOrder = Lists.newArrayList("1.0.0", "1.0.1");
	private static final List<MetaInfDataLocation> locationOrder = Lists.newArrayList(MetaInfDataLocation.values());
	private static final List<MetaInfDataType> typeOrder = Lists.newArrayList(MetaInfDataType.values());

	@Test
	public void test() {
		logger.info("Testing MetaInfResourceConfigurablePathComparatorTest");
		String loc0 = "1.0.0/client/bootstrap/foo.txt";
		String loc1 = "1.0.1/client/bootstrap/foo.txt";

		List<MetaInfResource> resources = Lists.newArrayList();
		resources.add(new MetaInfResource(loc1));
		resources.add(new MetaInfResource(loc0));

		Comparator<MetaInfResource> comparator = MetaInfResourceConfigurablePathComparator.builder().qualifierOrder(qualifierOrder).build();
		Collections.sort(resources, comparator);
		Assert.assertEquals(loc0, resources.get(0).getLocation());
		Assert.assertEquals(loc1, resources.get(1).getLocation());
	}

	@Test
	public void test2() {
		String loc0 = "1.0.0/client/bootstrap/foo.txt";
		String loc1 = "1.0.0/server/bootstrap/foo.txt";

		List<MetaInfResource> resources = Lists.newArrayList();
		resources.add(new MetaInfResource(loc1));
		resources.add(new MetaInfResource(loc0));

		Comparator<MetaInfResource> comparator = MetaInfResourceConfigurablePathComparator.builder().locationOrder(locationOrder).build();
		Collections.sort(resources, comparator);
		Assert.assertEquals(loc0, resources.get(0).getLocation());
		Assert.assertEquals(loc1, resources.get(1).getLocation());
	}

	@Test
	public void test3() {
		String loc0 = "1.0.0/client/bootstrap/foo.txt";
		String loc1 = "1.0.0/client/demo/foo.txt";
		String loc2 = "1.0.0/client/staging/foo.txt";
		String loc3 = "1.0.0/client/test/foo.txt";

		List<MetaInfResource> resources = Lists.newArrayList();
		resources.add(new MetaInfResource(loc2));
		resources.add(new MetaInfResource(loc1));
		resources.add(new MetaInfResource(loc3));
		resources.add(new MetaInfResource(loc0));

		Comparator<MetaInfResource> comparator = MetaInfResourceConfigurablePathComparator.builder().typeOrder(typeOrder).build();
		Collections.sort(resources, comparator);
		Assert.assertEquals(loc0, resources.get(0).getLocation());
		Assert.assertEquals(loc1, resources.get(1).getLocation());
		Assert.assertEquals(loc2, resources.get(2).getLocation());
		Assert.assertEquals(loc3, resources.get(3).getLocation());
	}

	@Test
	public void test4() {
		String loc00 = "1.0.0/client/bootstrap/foo.txt";
		String loc01 = "1.0.0/client/demo/foo.txt";
		String loc02 = "1.0.0/client/staging/foo.txt";
		String loc03 = "1.0.0/client/test/foo.txt";
		String loc04 = "1.0.0/server/bootstrap/foo.txt";
		String loc05 = "1.0.0/server/demo/foo.txt";
		String loc06 = "1.0.0/server/staging/foo.txt";
		String loc07 = "1.0.0/server/test/foo.txt";
		String loc08 = "1.0.1/client/bootstrap/foo.txt";
		String loc09 = "1.0.1/client/demo/foo.txt";
		String loc10 = "1.0.1/client/staging/foo.txt";
		String loc11 = "1.0.1/client/test/foo.txt";
		String loc12 = "1.0.1/server/bootstrap/foo.txt";
		String loc13 = "1.0.1/server/demo/foo.txt";
		String loc14 = "1.0.1/server/staging/foo.txt";
		String loc15 = "1.0.1/server/test/foo.txt";

		List<MetaInfResource> resources = Lists.newArrayList();
		resources.add(new MetaInfResource(loc10));
		resources.add(new MetaInfResource(loc09));
		resources.add(new MetaInfResource(loc11));
		resources.add(new MetaInfResource(loc08));
		resources.add(new MetaInfResource(loc06));
		resources.add(new MetaInfResource(loc05));
		resources.add(new MetaInfResource(loc07));
		resources.add(new MetaInfResource(loc04));
		resources.add(new MetaInfResource(loc14));
		resources.add(new MetaInfResource(loc13));
		resources.add(new MetaInfResource(loc15));
		resources.add(new MetaInfResource(loc12));
		resources.add(new MetaInfResource(loc02));
		resources.add(new MetaInfResource(loc01));
		resources.add(new MetaInfResource(loc03));
		resources.add(new MetaInfResource(loc00));

		MetaInfResourceConfigurablePathComparator.Builder builder = MetaInfResourceConfigurablePathComparator.builder();
		Comparator<MetaInfResource> comparator = builder.qualifierOrder(qualifierOrder).locationOrder(locationOrder).typeOrder(typeOrder).build();
		Collections.sort(resources, comparator);
		Assert.assertEquals(loc00, resources.get(0).getLocation());
		Assert.assertEquals(loc01, resources.get(1).getLocation());
		Assert.assertEquals(loc02, resources.get(2).getLocation());
		Assert.assertEquals(loc03, resources.get(3).getLocation());
		Assert.assertEquals(loc04, resources.get(4).getLocation());
		Assert.assertEquals(loc05, resources.get(5).getLocation());
		Assert.assertEquals(loc06, resources.get(6).getLocation());
		Assert.assertEquals(loc07, resources.get(7).getLocation());
		Assert.assertEquals(loc08, resources.get(8).getLocation());
		Assert.assertEquals(loc09, resources.get(9).getLocation());
		Assert.assertEquals(loc10, resources.get(10).getLocation());
		Assert.assertEquals(loc11, resources.get(11).getLocation());
		Assert.assertEquals(loc12, resources.get(12).getLocation());
		Assert.assertEquals(loc13, resources.get(13).getLocation());
		Assert.assertEquals(loc14, resources.get(14).getLocation());
		Assert.assertEquals(loc15, resources.get(15).getLocation());
	}

}
