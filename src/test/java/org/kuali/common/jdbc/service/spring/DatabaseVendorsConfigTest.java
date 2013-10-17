package org.kuali.common.jdbc.service.spring;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.common.jdbc.vendor.model.VendorDefault;
import org.kuali.common.jdbc.vendor.spring.DatabaseVendorConfig;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.spring.SpringExecUtils;
import org.kuali.common.util.spring.service.SpringContext;
import org.kuali.common.util.spring.service.SpringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DatabaseVendorsPropertySourceConfig.class)
public class DatabaseVendorsConfigTest {

	@Autowired
	PropertySource<?> source;

	@Autowired
	SpringService service;

	@Test
	public void test() {
		try {
			String vendorString = (String) source.getProperty("db.vendor");
			VendorDefault vendor = VendorDefault.valueOf(vendorString.toUpperCase());
			String profile = vendor.getCode();
			SpringContext context = SpringExecUtils.getSinglePropertySourceContext(source);
			context.setAnnotatedClasses(CollectionUtils.asList(DatabaseVendorConfig.class));
			context.setActiveProfiles(Arrays.asList(profile));
			service.load(context);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
