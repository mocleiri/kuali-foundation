package org.kuali.common.deploy;

import java.util.Properties;

import org.junit.Test;
import org.kuali.common.util.service.DefaultSpringService;
import org.kuali.common.util.service.SpringService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @deprecated
 */
@Deprecated
public class DefaultDeployServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(DefaultDeployServiceTest.class);
	SpringService ss = new DefaultSpringService();

	// <value>kuali.enc.*</value>
	// <value>jdbc.*</value>
	// <value>kdo.*</value>

	@Test
	public void test() {
		try {
			logger.trace("");
			Properties mavenProperties = new Properties();
			mavenProperties.setProperty("kuali.encoding", "UTF-8");
			mavenProperties.setProperty("kuali.groupId", "org.kuali");
			mavenProperties.setProperty("project.groupId", "org.kuali.student.web");
			mavenProperties.setProperty("project.artifactId", "ks-with-rice-bundled");
			mavenProperties.setProperty("project.version", "2.0.0-SNAPSHOT");
			mavenProperties.setProperty("project.classifier", "");
			mavenProperties.setProperty("deploy.env", "16");
			mavenProperties.setProperty("db.vendor", "oracle");
			mavenProperties.setProperty("kuali.enc.password", System.getProperty("kuali.enc.password"));
			mavenProperties.setProperty("kuali.enc.mode", "DECRYPT");
			ss.load("classpath:org/kuali/common/deploy/spring/deploy-context.xml", "kdo.mavenProperties", mavenProperties);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
