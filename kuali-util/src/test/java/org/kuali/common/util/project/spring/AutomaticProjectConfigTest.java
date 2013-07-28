package org.kuali.common.util.project.spring;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.project.KualiUtilProjectConstants;
import org.kuali.common.util.project.Project;
import org.kuali.common.util.project.ProjectIdentifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = KualiUtilProjectConfig.class)
public class AutomaticProjectConfigTest {

	private static final Logger logger = LoggerFactory.getLogger(AutomaticProjectConfigTest.class);

	protected static final ProjectIdentifier ID = KualiUtilProjectConstants.PROJECT_IDENTIFIER;

	@Autowired
	private Project project;

	@Test
	public void test() {
		logger.info("Constants: [" + ID.getGroupId() + ":" + ID.getArtifactId() + "]");
		logger.info("   Loaded: [" + project.getGroupId() + ":" + project.getArtifactId() + "]");
		Assert.assertEquals(ID.getGroupId(), project.getGroupId());
		Assert.assertEquals(ID.getArtifactId(), project.getArtifactId());
		PropertyUtils.info(project.getProperties());
	}

}
