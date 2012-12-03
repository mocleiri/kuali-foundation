package org.kuali.common.util.property.processor;

import junit.framework.Assert;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApplicationHomeProcessorTest {
	private static final Logger logger = LoggerFactory.getLogger(ApplicationHomeProcessorTest.class);
	HomeProcessor processor = new HomeProcessor();

	@Test
	public void testGetOrgCode() {
		Assert.assertEquals("kuali", processor.getOrganizationCode("org.kuali"));
		Assert.assertEquals("commons-lang", processor.getOrganizationCode("commons-lang"));
	}

	@Test
	public void testGetGroupCode() {
		String organizationGroupId = "org.kuali";
		Assert.assertEquals("ole", processor.getGroupCode(organizationGroupId, "org.kuali.ole"));
		Assert.assertEquals("student", processor.getGroupCode(organizationGroupId, "org.kuali.student.web"));
	}

	@Test
	public void test() {
		String userHomePath = System.getProperty("user.home");
		String organizationGroupId = "org.kuali";
		String groupId = "org.kuali.ole";
		String artifactId = "docstore";
		String propertiesFileSuffix = ".properties";

		String orgHome = processor.getOrganizationHome(userHomePath, organizationGroupId);
		String grpHome = processor.getGroupHome(orgHome, organizationGroupId, groupId);
		String props = processor.getPropertiesFilename(grpHome, artifactId, propertiesFileSuffix);
		logger.info("Organization Home: " + orgHome);
		logger.info("Group Home: " + grpHome);
		logger.info("Properties Filename: " + props);
	}
}
