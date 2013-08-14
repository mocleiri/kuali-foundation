package org.kuali.common.jdbc.service.spring;

import java.util.List;

import org.junit.Test;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.project.spring.AutowiredProjectConfig;
import org.kuali.common.util.spring.service.DefaultSpringService;
import org.kuali.common.util.spring.service.SpringContext;
import org.kuali.common.util.spring.service.SpringService;

public class DatabaseVendorsConfigTest {

	@Test
	public void test() {
		try {
			List<Class<?>> annotatedClasses = CollectionUtils.asList(AutowiredProjectConfig.class, JdbcPropertySourceConfig.class);
			SpringContext context = new SpringContext();
			context.setAnnotatedClasses(annotatedClasses);
			SpringService ss = new DefaultSpringService();
			ss.load(context);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
