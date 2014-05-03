/**
 * Copyright 2014-2014 The Kuali Foundation
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
package org.kuali.common.core.json;

import static org.junit.Assert.assertEquals;

import java.util.Properties;

import org.junit.Test;
import org.kuali.common.core.json.api.JsonService;
import org.kuali.common.core.json.jackson.JacksonJsonService;

public class AwsAccountTest {

	@Test
	public void test() {
		try {
			Properties props = new Properties();
			props.setProperty("aws.name", "jeff");
			AwsAccount account = AwsAccount.builder().withName("name").withAccountNumber("123").build();
			JsonService service = new JacksonJsonService();
			String json1 = service.writeString(account);
			System.out.println(json1);
			AwsAccount deserialized = service.readString(json1, AwsAccount.class);
			String json2 = service.writeString(deserialized);
			assertEquals(json1, json2);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}
