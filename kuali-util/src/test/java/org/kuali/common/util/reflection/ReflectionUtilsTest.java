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
package org.kuali.common.util.reflection;

import java.lang.reflect.Field;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.kuali.common.util.ReflectionUtils;
import org.kuali.common.util.log.LoggerUtils;
import org.slf4j.Logger;

public class ReflectionUtilsTest {

	private static final Logger logger = LoggerUtils.make();

	@Test
	public void testDetectOptionalString() throws Exception {
		Field field = C.class.getDeclaredField("foo");
		boolean condition = ReflectionUtils.isOptionalString(field);
		Assert.assertTrue("'foo' must be an optional string", condition);
	}

	@Test
	public void testOptionalThatIsntAString() throws Exception {
		Field field = C.class.getDeclaredField("bar");
		boolean condition = ReflectionUtils.isOptionalString(field);
		Assert.assertFalse("'bar' is not an optional string", condition);
	}

	@Test
	public void testNormalString() throws Exception {
		Field field = C.class.getDeclaredField("baz");
		boolean condition = ReflectionUtils.isOptionalString(field);
		Assert.assertFalse("'baz' is not an optional", condition);
	}

	@Test
	public void test() {
		Set<Field> fields = ReflectionUtils.getAllFields(B.class);
		logger.info("fields.size()={}", fields.size());
		Assert.assertEquals(2, fields.size());
	}
}
