package org.kuali.common.util.spring.test;

import org.junit.Test;
import org.kuali.common.util.spring.HelloWorldConfig;
import org.kuali.common.util.spring.beans.HelloWorldMessage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class HelloWorldTest {

	@Test
	public void test() {
		try {
			AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(HelloWorldConfig.class);
			HelloWorldMessage helloWorld = ctx.getBean(HelloWorldMessage.class);
			System.out.println(helloWorld.getMessage());
			ctx.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
