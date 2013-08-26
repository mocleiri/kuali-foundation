package org.kuali.common.util.xml.jaxb;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.common.util.log.log4j.model.Log4JConfiguration;
import org.kuali.common.util.log.log4j.model.Logger;
import org.kuali.common.util.project.ProjectUtils;
import org.kuali.common.util.project.model.Build;
import org.kuali.common.util.project.model.Project;
import org.kuali.common.util.project.spring.KualiUtilProjectConfig;
import org.kuali.common.util.xml.service.XmlService;
import org.kuali.common.util.xml.spring.Log4JXmlServiceConfig;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { KualiUtilProjectConfig.class, Log4JXmlServiceConfig.class })
public class Log4XMLParseTest {

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Log4XMLParseTest.class);

	@Autowired
	Project project;

	@Autowired
	XmlService service;

	@Test
	public void test() {
		try {
			Build build = build();
			Log4JConfiguration config = new Log4JConfiguration.Builder(Logger.DEFAULT).build();
			String xml = service.toXml(config, build.getEncoding());
			logger.info(xml);
			service.getObjectFromXml(xml, build.getEncoding(), Log4JConfiguration.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected Build build() {
		return ProjectUtils.getBuild(project);
	}

}
