package org.kuali.common.util.spring;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class HelloWorldTest {

	@Test
	public void test() {
		try {
			AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(HelloWorldConfig.class);
			HelloWorld helloWorld = ctx.getBean(HelloWorld.class);
			System.out.println(helloWorld.getMessage());
			ctx.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
