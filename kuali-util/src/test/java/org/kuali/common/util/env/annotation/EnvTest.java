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
package org.kuali.common.util.env.annotation;

import org.junit.Test;
import org.kuali.common.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EnvTest {

	private static final Logger logger = LoggerFactory.getLogger(EnvTest.class);

	@Test
	public void test() {
		try {
			System.setProperty("env.foo", "bar");
			System.setProperty("env.bar", "blibbity,blabbity,pac-man");
			System.setProperty("env.missingPropertyMode", "INFORMM");
			FakeEnvServiceContext fesc = new FakeEnvServiceContext.Builder().missingPropertyMode(null).build();
			show(fesc);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void show(FakeEnvServiceContext ctx) {
		logger.info("Mode: {}", ctx.getMissingPropertyMode());
		if (ctx.getFoo().isPresent()) {
			logger.info(" Foo: {}", ctx.getFoo().get());
		} else {
			logger.info(" Foo: {}", "ABSENT");
		}
		logger.info(" Bar: {}", CollectionUtils.getSpaceSeparatedString(ctx.getBar()));
	}

}
