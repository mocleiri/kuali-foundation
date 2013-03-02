package org.kuali.common.deploy;

import java.util.Properties;

import org.junit.Test;
import org.kuali.common.util.service.DefaultSpringService;
import org.kuali.common.util.service.SpringService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultDeployServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(DefaultDeployServiceTest.class);
	SpringService ss = new DefaultSpringService();

	@Test
	public void test() {
		try {
			logger.trace("");
			Properties mavenProperties = new Properties();
			mavenProperties.setProperty("kuali.encoding", "UTF-8");
			mavenProperties.setProperty("project.groupId.path", "org/kuali/student/web");
			ss.load("classpath:org/kuali/common/deploy/spring/deploy-context.xml", "kdo.mavenProperties", mavenProperties);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
