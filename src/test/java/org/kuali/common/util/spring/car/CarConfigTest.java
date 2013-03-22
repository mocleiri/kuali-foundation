package org.kuali.common.util.spring.car;

import java.util.List;

import org.junit.Test;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.service.DefaultSpringService;
import org.kuali.common.util.service.PropertySourceContext;
import org.kuali.common.util.service.SpringContext;
import org.kuali.common.util.service.SpringService;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.core.env.PropertySource;

public class CarConfigTest {

	@Test
	public void test() {

		try {
			System.setProperty("car.make", "ford");

			SpringService ss = new DefaultSpringService();

			List<PropertySource<?>> propertySources = SpringUtils.getPropertySources(CarPropertySourcesConfig.class);

			SpringContext sc = new SpringContext();
			sc.setAnnotatedClasses(CollectionUtils.asList(CarConfig.class));
			sc.setPropertySourceContext(new PropertySourceContext(propertySources, true));
			ss.load(sc);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
