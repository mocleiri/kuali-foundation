package org.kuali.common.util.spring.context;

import org.junit.Test;
import org.kuali.common.util.log.LoggerUtils;
import org.slf4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ContextTest {

	private static final Logger logger = LoggerUtils.make();

	@Test
	public void test() {
		try {
			AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
			ctx.setId("foo");
			ctx.setDisplayName("bar");
			ctx.register(CerealConfig.class);
			ctx.refresh();
			logger.info("Id: [{}]", ctx.getId());
			logger.info("Name: [{}]", ctx.getDisplayName());
			ctx.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
