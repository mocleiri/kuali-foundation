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
package org.kuali.common.util.spring.config;

import java.util.ArrayList;
import java.util.List;

import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.spring.beans.DefaultMessageImpl;
import org.kuali.common.util.spring.beans.Message;
import org.kuali.common.util.spring.beans.PrintMessagesExecutable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AutowiredMessagesConfig {

	@Bean
	public String helloWorldString() {
		return "Hello World";
	}

	@Bean
	public String goodbyeString() {
		return "Good bye";
	}

	@Bean
	public Message helloWorldMessage() {
		DefaultMessageImpl message = new DefaultMessageImpl();
		message.setMessage(helloWorldString());
		return message;
	}

	@Bean
	public Message goodbyeMessage() {
		DefaultMessageImpl message = new DefaultMessageImpl();
		message.setMessage(goodbyeString());
		return message;
	}

	@Bean
	@Autowired
	public List<Message> messages() {
		return null;
	}

	@Bean
	public Executable printMessagesExecutable() {
		List<Message> messages = new ArrayList<Message>();
		messages.add(helloWorldMessage());
		messages.add(goodbyeMessage());

		PrintMessagesExecutable pme = new PrintMessagesExecutable();
		pme.setMessages(messages);
		return pme;
	}
}
