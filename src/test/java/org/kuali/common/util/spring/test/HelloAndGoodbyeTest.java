/**
 * Copyright 2010-2013 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.common.util.spring.test;

import org.junit.Test;
import org.kuali.common.util.spring.beans.Message;
import org.kuali.common.util.spring.config.HelloAndGoodbyeConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class HelloAndGoodbyeTest {

	@Test
	public void test() {
		try {
			AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(HelloAndGoodbyeConfig.class);
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
