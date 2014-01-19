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

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.kuali.common.util.ReflectionUtils;
import org.kuali.common.util.build.Builder;
import org.kuali.common.util.log.LoggerUtils;
import org.slf4j.Logger;

public class ReflectionUtilsTest {

	private static final Logger logger = LoggerUtils.make();

	/**
	 * <p>
	 * Given a class that implements a parameterized interface, return the actual type of the first parameterized argument for the interface
	 * </p>
	 * 
	 * Given:
	 * 
	 * <pre>
	 * interface Foo&lt;T&gt; 
	 * 
	 * class Bar implements Foo&lt;String&gt;
	 * </pre>
	 * 
	 * <p>
	 * This method returns {@code String.class}
	 * </p>
	 */
	public static Class<?> getFirstParameterizedTypeArgumentAsClass(Class<?> type, Class<?> parameterizedInterface) {
		checkArgument(parameterizedInterface.isInterface(), "[%s] is not an interface", parameterizedInterface.getCanonicalName());
		TypeVariable<?>[] params = parameterizedInterface.getTypeParameters();
		checkArgument(params.length > 0, "[%s] has no type parameters", parameterizedInterface.getCanonicalName());
		Map<Class<?>, ParameterizedType> interfaces = ReflectionUtils.getAllParameterizedInterfaces(type);
		ParameterizedType parameterizedType = interfaces.get(parameterizedInterface);
		checkState(parameterizedType != null, "[%s] does not implement [%s]", type.getCanonicalName(), parameterizedInterface.getCanonicalName());
		Type[] args = parameterizedType.getActualTypeArguments();
		checkState(args.length > 0, "[%s] has no actual type arguments", parameterizedInterface.getCanonicalName());
		Type firstTypeArgument = args[0];
		checkState(firstTypeArgument instanceof Class<?>, "First actual type argument of [%s] is not a class [%s]", parameterizedInterface.getCanonicalName(), firstTypeArgument);
		return (Class<?>) firstTypeArgument;
	}

	@Test
	public void extractBuilderType() {
		try {
			Class<?> type = Foo.Builder2.class;
			Class<?> builderType = getFirstParameterizedTypeArgumentAsClass(type, Builder.class);
			System.out.println(builderType.getCanonicalName());
		} catch (Exception e) {
			e.printStackTrace();
		}
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
