package org.kuali.common.deploy;

import org.junit.Test;
import org.kuali.common.deploy.spring.MavenDeployConfig;
import org.kuali.common.util.service.DefaultSpringService;
import org.kuali.common.util.service.SpringService;

public class DeployAnnotatedClassesTest {

	SpringService ss = new DefaultSpringService();

	@Test
	public void test() {
		try {
			System.setProperty("properties.resolve", "false");
			ss.load(MavenDeployConfig.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
