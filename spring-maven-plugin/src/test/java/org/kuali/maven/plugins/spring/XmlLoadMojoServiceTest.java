package org.kuali.maven.plugins.spring;

import org.junit.Test;
import org.kuali.common.util.service.DefaultSpringService;
import org.kuali.common.util.service.SpringService;
import org.kuali.maven.plugins.spring.config.LoadMojoServiceTestConfig;

public class XmlLoadMojoServiceTest {

	@Test
	public void test() {
		try {
			SpringService ss = new DefaultSpringService();
			ss.load(LoadMojoServiceTestConfig.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
