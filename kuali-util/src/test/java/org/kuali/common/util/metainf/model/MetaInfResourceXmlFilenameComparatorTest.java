package org.kuali.common.util.metainf.model;

import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.kuali.common.util.log.LoggerUtils;
import org.slf4j.Logger;

import com.google.common.collect.Lists;

public class MetaInfResourceXmlFilenameComparatorTest {

	private static final Logger logger = LoggerUtils.make();

	@Test
	public void test() {
		try {
			List<MetaInfResource> resources = Lists.newArrayList();
			resources.add(new MetaInfResource("demo/TravelAttachmentSampleDocType.xml"));
			resources.add(new MetaInfResource("demo/TravelAuthorization.xml"));
			resources.add(new MetaInfResource("demo/TravelDocument/TravelCompanyMaintenanceDocument.xml"));
			resources.add(new MetaInfResource("demo/TravelDocument/TravelDestinationMaintenanceDocument.xml"));
			resources.add(new MetaInfResource("demo/TravelDocument.xml"));
			resources.add(new MetaInfResource("demo/TravelDocument/TravelMileageRateMaintenanceDocument.xml"));

			Collections.sort(resources, new MetaInfResourceXmlFilenameComparator());

			for (MetaInfResource resource : resources) {
				logger.info(resource.getLocation());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
