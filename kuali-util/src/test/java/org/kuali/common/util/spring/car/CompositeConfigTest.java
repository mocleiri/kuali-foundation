package org.kuali.common.util.spring.car;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.kuali.common.util.service.DefaultSpringService;
import org.kuali.common.util.service.SpringContext;
import org.kuali.common.util.service.SpringService;
import org.springframework.core.env.PropertySource;

public class CompositeConfigTest {

	@Test
	public void test() {

		try {
			SpringService ss = new DefaultSpringService();

			List<PropertySource<?>> sources = ss.getPropertySources(CarPropertySourcesConfig.class);

			List<Class<?>> acs = new ArrayList<Class<?>>();
			acs.add(CompositeConfig.class);

			SpringContext sc = new SpringContext();
			sc.setAnnotatedClasses(acs);
			sc.setPropertySources(sources);
			ss.load(sc);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
