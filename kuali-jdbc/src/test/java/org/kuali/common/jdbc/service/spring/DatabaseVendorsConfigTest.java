package org.kuali.common.jdbc.service.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.common.jdbc.model.DatabaseVendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DatabaseVendorsConfig.class)
@ActiveProfiles(profiles = "mysql")
public class DatabaseVendorsConfigTest {

	@Autowired
	DatabaseVendor vendor;

	@Test
	public void test() {
		System.out.println(vendor.getDriver().getName());
	}

}
