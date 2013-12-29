package org.kuali.common.util.spring.context;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.kuali.common.util.log.LoggerUtils;
import org.slf4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.google.common.collect.Lists;

public class ContextTest {

	private static final Logger logger = LoggerUtils.make();

	@Test
	public void test() {
		try {
			AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
			ctx.register(CerealConfig.class, MilkConfig.class);
			// ctx.register(BreakfastConfig.class);
			ctx.refresh();
			String[] beanNames = ctx.getBeanDefinitionNames();
			logger.info("{} beans in this application context", beanNames.length);
			List<String> names = Lists.newArrayList(Arrays.asList(beanNames));
			Collections.sort(names);
			for (int i = 0; i < names.size(); i++) {
				String name = names.get(i);
				Object bean = ctx.getBean(name);
				logger.info(" " + (i + 1) + "  [{}]=[{}]", name, bean.getClass().getCanonicalName());
			}
			ctx.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
