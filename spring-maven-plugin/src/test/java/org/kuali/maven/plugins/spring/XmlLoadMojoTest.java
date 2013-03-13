package org.kuali.maven.plugins.spring;

import org.junit.Test;
import org.kuali.common.util.service.DefaultSpringService;
import org.kuali.common.util.service.SpringService;
import org.kuali.maven.plugins.spring.config.XmlLoadMojoTestConfig;

public class XmlLoadMojoTest {

	@Test
	public void test() {
		try {
			SpringService ss = new DefaultSpringService();
			ss.load(XmlLoadMojoTestConfig.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
