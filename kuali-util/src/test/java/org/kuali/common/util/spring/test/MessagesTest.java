package org.kuali.common.util.spring.test;

import org.junit.Test;
import org.kuali.common.util.spring.config.MessagesConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MessagesTest {

	@Test
	public void test() {
		try {
			AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MessagesConfig.class);
			ctx.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
