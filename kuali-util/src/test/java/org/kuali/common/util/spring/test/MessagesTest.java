package org.kuali.common.util.spring.test;

import java.util.Map;

import org.junit.Test;
import org.kuali.common.util.spring.beans.Message;
import org.kuali.common.util.spring.config.MessagesConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MessagesTest {

	@Test
	public void test() {
		try {
			AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MessagesConfig.class);
			Map<String, Message> messages = ctx.getBeansOfType(Message.class);
			for (Map.Entry<String, Message> pair : messages.entrySet()) {
				String key = pair.getKey();
				Message message = pair.getValue();
				System.out.println(key + "=" + message.getMessage());
			}
			ctx.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
