/**
 * Copyright 2013-2013 The Kuali Foundation
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
package org.kuali.common.http.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.common.http.model.HttpContext;
import org.kuali.common.http.spring.DefaultHttpServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DefaultHttpServiceConfig.class })
public class DefaultHttpServiceTest {

	@Autowired
	HttpService service;

	@Test
	public void test() {
		HttpContext context = new HttpContext.Builder("http://env166888888.ks.kuali.org").overallTimeoutMillis("10s").requestTimeoutMillis("1s").sleepIntervalMillis("1s").build();
		service.wait(context);
	}

}
