package org.kuali.common.jdbc.vendor.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.spring.SpringExecUtils;
import org.kuali.common.util.spring.service.SpringContext;
import org.kuali.common.util.spring.service.SpringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DatabaseVendorPropertySourceConfig.class)
public class DatabaseVendorConfigTest {

	@Autowired
	PropertySource<?> source;

	@Autowired
	SpringService service;

	@Test
	public void test() {
		try {
			SpringContext context = SpringExecUtils.getSinglePropertySourceContext(source);
			context.setAnnotatedClasses(CollectionUtils.asList(DatabaseVendorConfig.class));
			service.load(context);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
