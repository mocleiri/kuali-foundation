package org.kuali.common.util.spring.test;

import org.junit.Test;
import org.kuali.common.util.spring.beans.Message;
import org.kuali.common.util.spring.config.HelloWorldConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class HelloAndGoodbyeTest {

	@Test
	public void test() {
		try {
			AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(HelloWorldConfig.class);
			Message helloWorld = ctx.getBean("helloWorldMessage", Message.class);
			Message goodbye = ctx.getBean("goodbyeMessage", Message.class);
			System.out.println(helloWorld.getMessage());
			System.out.println(goodbye.getMessage());
			ctx.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
