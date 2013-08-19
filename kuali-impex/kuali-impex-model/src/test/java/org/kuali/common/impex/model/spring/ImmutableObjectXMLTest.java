package org.kuali.common.impex.model.spring;

import java.io.File;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.common.impex.model.ImmutableSchema;
import org.kuali.common.impex.model.ImmutableTable;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.file.CanonicalFile;
import org.kuali.common.util.xml.XmlService;
import org.kuali.common.util.xml.spring.XmlServiceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = XmlServiceConfig.class)
public class ImmutableObjectXMLTest {

	private static final Logger logger = LoggerFactory.getLogger(ImmutableObjectXMLTest.class);

	@Autowired
	XmlService service;

	@Test
	public void test() {
		try {
			ImmutableTable table = new ImmutableTable("foo");
			ImmutableSchema out = new ImmutableSchema("bar", CollectionUtils.singletonList(table));
			File file1 = new CanonicalFile(System.getProperty("user.home") + "/ws/kuali-impex/kuali-impex-model/target/schema1.xml");
			logger.info("Creating [{}]", file1);
			service.write(file1, out);
			ImmutableSchema in = service.getObject(file1, ImmutableSchema.class);
			File file2 = new CanonicalFile(System.getProperty("user.home") + "/ws/kuali-impex/kuali-impex-model/target/schema2.xml");
			service.write(file2, in);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
