package org.kuali.common.util.metainf.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.common.util.metainf.spring.MpxConfig;
import org.kuali.common.util.project.spring.KualiUtilProjectConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { KualiUtilProjectConfig.class, MpxConfig.class })
public class MetaInfServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(MetaInfServiceTest.class);

	@Test
	public void test() {
		try {
			logger.info("hello world");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test1() {
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
