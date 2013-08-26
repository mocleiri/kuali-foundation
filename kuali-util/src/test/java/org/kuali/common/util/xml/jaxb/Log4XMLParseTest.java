package org.kuali.common.util.xml.jaxb;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.common.util.log.log4j.model.Log4JConfiguration;
import org.kuali.common.util.log.log4j.model.Logger;
import org.kuali.common.util.project.spring.KualiUtilProjectConfig;
import org.kuali.common.util.xml.service.XmlService;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { KualiUtilProjectConfig.class })
public class Log4XMLParseTest {

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Log4XMLParseTest.class);

	@Test
	public void test() {
		try {
			Log4JConfiguration config = new Log4JConfiguration.Builder(Logger.DEFAULT).build();

			XmlService service1 = new JAXBXmlService.Builder().useNamespaceAwareParser(false).useEclipseLinkMoxyProvider(false).build();

			String xml1 = service1.toXml(config, "UTF-8");
			logger.info(xml1);

			XmlService service2 = new JAXBXmlService.Builder().useNamespaceAwareParser(false).useEclipseLinkMoxyProvider(true).build();
			String xml2 = service2.toXml(config, "UTF-8");
			logger.info(xml2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
