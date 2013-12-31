package org.kuali.common.util.spring.context;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.junit.Test;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.log.LoggerUtils;
import org.kuali.common.util.spring.PropertySourceUtils;
import org.slf4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import com.google.common.collect.Lists;

public class ContextTest {

	private static final Logger logger = LoggerUtils.make();

	@Test
	public void test() {
		try {
			Properties source = PropertyUtils.load("classpath:org/kuali/common/util/spring/context/breakfast.properties");
			AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
			ConfigurableEnvironment env = ctx.getEnvironment();
			PropertySourceUtils.reconfigurePropertySources(env, "properties", source);
			ctx.register(CerealConfig.class, MilkConfig.class);
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
