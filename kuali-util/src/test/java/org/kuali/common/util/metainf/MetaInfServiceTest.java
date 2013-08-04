package org.kuali.common.util.metainf;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.common.util.metainf.service.MetaInfService;
import org.kuali.common.util.metainf.spring.MetaInfServiceConfig;
import org.kuali.common.util.project.Project;
import org.kuali.common.util.project.ProjectUtils;
import org.kuali.common.util.project.spring.KualiUtilProjectConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { KualiUtilProjectConfig.class, MetaInfServiceConfig.class })
public class MetaInfServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(MetaInfServiceTest.class);

	@Autowired
	MetaInfService service;

	@Autowired
	Project project;

	@Test
	public void test() {
		try {
			logger.info(project.getGroupId());
			logger.info(ProjectUtils.getBasedir(project) + "");
			System.out.println(service);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
