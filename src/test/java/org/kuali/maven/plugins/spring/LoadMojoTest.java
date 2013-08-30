package org.kuali.maven.plugins.spring;

import org.junit.Test;
import org.kuali.common.util.spring.service.DefaultSpringService;
import org.kuali.common.util.spring.service.SpringService;
import org.kuali.maven.plugins.spring.config.LoadMojoTestConfig;

public class LoadMojoTest {

	@Test
	public void test() {
		try {
			SpringService ss = new DefaultSpringService();
			ss.load(LoadMojoTestConfig.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
