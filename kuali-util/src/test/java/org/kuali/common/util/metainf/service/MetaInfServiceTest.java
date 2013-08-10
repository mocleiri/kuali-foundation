package org.kuali.common.util.metainf.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.metainf.spring.MetaInfConfig;
import org.kuali.common.util.metainf.spring.MpxConfig;
import org.kuali.common.util.project.spring.BuildConfig;
import org.kuali.common.util.project.spring.KualiUtilProjectConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { KualiUtilProjectConfig.class, BuildConfig.class, MpxConfig.class, MetaInfConfig.class })
public class MetaInfServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(MetaInfServiceTest.class);

	@Autowired
	MetaInfConfig metaInfConfig;

	@Test
	public void test() {
		try {
			logger.info("hello world");
			Executable exec = metaInfConfig.metaInfExecutable();
			exec.execute();
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
