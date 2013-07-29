package org.kuali.common.util.log4j;

import java.io.File;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.common.util.project.spring.AutowiredProjectConfigTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ProjectTestingConfig.class)
public class Log4jConfigTest {

	private static final Logger logger = LoggerFactory.getLogger(AutowiredProjectConfigTest.class);

	@Autowired
	ProjectTestingConfig projectTestingConfig;

	@Test
	public void test() {
		File baseDir = projectTestingConfig.baseDir();
		logger.info(baseDir + "");
	}
}
