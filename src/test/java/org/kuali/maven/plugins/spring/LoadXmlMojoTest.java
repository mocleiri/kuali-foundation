package org.kuali.maven.plugins.spring;

import org.junit.Test;
import org.kuali.common.util.spring.service.DefaultSpringService;
import org.kuali.common.util.spring.service.SpringService;
import org.kuali.maven.plugins.spring.config.XmlLoadMojoTestConfig;

public class LoadXmlMojoTest {

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
