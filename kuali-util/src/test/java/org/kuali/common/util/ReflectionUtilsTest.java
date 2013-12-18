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
package org.kuali.common.util;

import java.lang.reflect.Field;
import java.util.Set;

import org.junit.Test;
import org.slf4j.Logger;

public class ReflectionUtilsTest {

	private static final Logger logger = org.kuali.common.util.log.LoggerUtils.make();

	public static class A {
		int foo;
	}

	public static class B extends A {
		int foo;
	}

	@Test
	public void test() {
		Set<Field> fields = ReflectionUtils.getFields(ReflectionUtilsTest.B.class, true);
		logger.info("fields.size()={}", fields.size());
	}
}
