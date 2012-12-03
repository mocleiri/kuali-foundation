package org.kuali.common.util.property.processor;

import java.util.List;
import java.util.Properties;

import junit.framework.Assert;

import org.junit.Test;
import org.kuali.common.util.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApplicationHomeProcessorTest {
	private static final Logger logger = LoggerFactory.getLogger(ApplicationHomeProcessorTest.class);

	HomeProcessor processor = getHomeProcessor();

	protected HomeProcessor getHomeProcessor() {
		HomeProcessor processor = new HomeProcessor();
		processor.setArtifactId("ole-webapp");
		processor.setGroupId("org.kuali.ole");
		processor.setOrganizationGroupId("org.kuali");
		return processor;
	}

	@Test
	public void testProcess() {
		Properties properties = new Properties();
		processor.process(properties);
		List<String> keys = PropertyUtils.getSortedKeys(properties);
		for (String key : keys) {
			String value = properties.getProperty(key);
			logger.info(key + "=" + value);
		}
	}

	@Test
	public void testGetOrgCode() {
		Assert.assertEquals("kuali", processor.getOrgCode("org.kuali"));
		Assert.assertEquals("commons-lang", processor.getOrgCode("commons-lang"));
	}

	@Test
	public void testGetGroupCode() {
		String orgId = "org.kuali";
		Assert.assertEquals("ole", processor.getGroupCode(orgId, "org.kuali.ole"));
		Assert.assertEquals("student", processor.getGroupCode(orgId, "org.kuali.student.web"));
	}

	@Test
	public void test() {
		String userHomePath = System.getProperty("user.home");
		String organizationGroupId = "org.kuali";
		String groupId = "org.kuali.ole";
		String artifactId = "docstore";
		String propertiesFileSuffix = ".properties";

		String orgHome = processor.getOrgHome(userHomePath, organizationGroupId);
		String grpHome = processor.getGroupHome(orgHome, groupId);
		String props = processor.getPropertiesFileLocation(grpHome, artifactId, propertiesFileSuffix);
		// logger.info("Organization Home: " + orgHome);
		// logger.info("Group Home: " + grpHome);
		// logger.info("Properties Filename: " + props);
	}
}
