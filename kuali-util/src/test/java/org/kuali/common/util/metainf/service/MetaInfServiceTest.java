package org.kuali.common.util.metainf.service;

import java.io.File;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.common.util.file.CanonicalFile;
import org.kuali.common.util.metainf.model.MetaInfContext;
import org.kuali.common.util.metainf.model.ScanResult;
import org.kuali.common.util.metainf.spring.MetaInfServiceConfig;
import org.kuali.common.util.project.ProjectUtils;
import org.kuali.common.util.project.model.Project;
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
			File buildOutputDirectory = ProjectUtils.getBuildOutputDirectory(project);
			File outputFile = new CanonicalFile(buildOutputDirectory, MetaInfUtils.getResourcePrefix(project) + "/classes.resources");
			String encoding = ProjectUtils.getEncoding(project);
			MetaInfContext context = new MetaInfContext(outputFile, encoding, buildOutputDirectory, "**/*.class");
			ScanResult result = service.scan(context);
			logger.info("size={}", result.getResources().size());
			service.write(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
