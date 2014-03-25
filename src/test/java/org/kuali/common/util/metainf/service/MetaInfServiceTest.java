package org.kuali.common.util.metainf.service;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = { KualiUtilProjectConfig.class, MpxConfig.class })
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
