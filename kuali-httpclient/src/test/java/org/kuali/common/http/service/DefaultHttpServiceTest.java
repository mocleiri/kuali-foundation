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
package org.kuali.common.http.service;

import static org.kuali.common.util.log.Loggers.newLogger;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuali.common.http.model.HttpContext;
import org.kuali.common.http.model.HttpStatus;
import org.kuali.common.http.model.HttpWaitResult;
import org.kuali.common.http.spring.DefaultHttpServiceConfig;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DefaultHttpServiceConfig.class })
public class DefaultHttpServiceTest {

	private static final Logger logger = newLogger();

	@Autowired
	HttpService service;

	@Test
	public void testQuiet() {
		HttpContext context = HttpContext.builder("http://blibbity.foomanchu").overallTimeout("3s").requestTimeout("1s").sleepInterval("1s").quiet(true).build();
		HttpWaitResult result = service.wait(context);
		Assert.assertEquals(HttpStatus.TIMEOUT, result.getStatus());
	}

	@Test
	public void testUnknownHost() {
		try {
			HttpContext context = HttpContext.builder("http://blibbity.foomanchu").overallTimeout("3s").requestTimeout("1s").sleepInterval("1s").build();
			service.wait(context);
			Assert.fail("Contacting a non-existent URL returned 200");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void testYahoo() {
		HttpContext context = HttpContext.builder("http://www.yahoo.com/").overallTimeout("15s").requestTimeout("10s").sleepInterval("1s").build();
		HttpWaitResult result = service.wait(context);
		Assert.assertEquals(HttpStatus.SUCCESS, result.getStatus());
	}

	@Test
	public void testEnv20() {
		try {
			HttpContext context = HttpContext.builder("http://ec2-23-22-42-91.compute-1.amazonaws.com").overallTimeout("5s").requestTimeout("5s").sleepInterval("1s").build();
			HttpWaitResult result = service.wait(context);
			logger.info(result.getStatus().name());
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
