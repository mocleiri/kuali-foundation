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
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.kuali.common.util.ReflectionUtils;
import org.kuali.common.util.log.LoggerUtils;
import org.slf4j.Logger;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

public class ReflectionUtilsTest {

	private static final Logger logger = LoggerUtils.make();

	@Test
	public void extractBuilderType() {
		try {
			Class<?> type = Foo.Builder2.class;
			List<ParameterizedType> list = getAllParameterizedInterfaces(type);
			for (ParameterizedType element : list) {
				Class<?> interfaceClass = (Class<?>) element.getRawType();
				System.out.println(interfaceClass.getCanonicalName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Class<?>> getPath(Class<?> type) {
		List<Class<?>> list = Lists.newArrayList();
		if (type.getSuperclass() != null) {
			list.addAll(getPath(type.getSuperclass()));
		}
		list.add(type);
		return list;
	}

	public List<ParameterizedType> getAllParameterizedInterfaces(Class<?> type) {
		List<Type> list = getAllGenericInterfaces(type);
		List<ParameterizedType> types = Lists.newArrayList();
		for (Type element : list) {
			if (element instanceof ParameterizedType) {
				types.add((ParameterizedType) element);
			}
		}
		return types;
	}

	public List<Type> getAllGenericInterfaces(Class<?> type) {
		List<Class<?>> path = getPath(type);
		List<Type> list = Lists.newArrayList();
		for (Class<?> element : path) {
			Type[] interfaces = element.getGenericInterfaces();
			list.addAll(ImmutableList.copyOf(interfaces));
		}
		return list;
	}

	@Test
	public void testHasMatchingParameterizedArgTypes1() throws Exception {
		Field field = C.class.getDeclaredField("foo");
		boolean condition = ReflectionUtils.hasMatchingParameterizedArgTypes(field, String.class);
		Assert.assertTrue("'foo' must be an optional string", condition);

	}

	@Test
	public void testHasMatchingParameterizedArgTypes2() throws Exception {
		Field field = C.class.getDeclaredField("bar");
		boolean condition = ReflectionUtils.hasMatchingParameterizedArgTypes(field, Integer.class);
		Assert.assertTrue("'foo' must be an optional string", condition);

	}

	@Test
	public void testHasMatchingParameterizedArgTypes3() throws Exception {
		Field field = C.class.getDeclaredField("bar");
		boolean condition = ReflectionUtils.hasMatchingParameterizedArgTypes(field, String.class);
		Assert.assertFalse("'foo' must be an optional string", condition);

	}

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
