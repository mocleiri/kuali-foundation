package org.kuali.common.util.spring.car;

import java.util.List;

import org.junit.Test;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.service.DefaultSpringService;
import org.kuali.common.util.service.SpringContext;
import org.kuali.common.util.service.SpringService;
import org.springframework.core.env.PropertySource;

public class CarConfigTest {

	@Test
	public void test() {

		try {
			System.setProperty("project.encoding", "UTF-8");
			SpringService ss = new DefaultSpringService();

			List<PropertySource<?>> propertySources = ss.getPropertySources(CarPropertySourcesConfig.class);

			List<Class<?>> annotatedClasses = CollectionUtils.getClassList(CarConfig.class);

			SpringContext sc = new SpringContext();
			sc.setAnnotatedClasses(annotatedClasses);
			sc.setPropertySources(propertySources);
			ss.load(sc);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
