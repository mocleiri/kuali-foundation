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
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.util.MethodInvoker;

import com.google.common.base.Optional;

public class ReflectionUtils extends org.springframework.util.ReflectionUtils {

	/**
	 * Unconditionally attempt to get the value for this field on this bean. If the field is not accessible make it accessible, get the value, then set it back to being in
	 * accessible.
	 */
	public static Optional<?> get(Field field, Object instance) {
		try {
			synchronized (field) {
				boolean accessible = field.isAccessible();
				if (!accessible) {
					field.setAccessible(true);
				}
				Object value = field.get(instance);
				if (!accessible) {
					field.setAccessible(false);
				}
				return Optional.fromNullable(value);
			}
		} catch (IllegalAccessException e) {
			throw new IllegalStateException(e);
		}
	}

	/**
	 * Unconditionally attempt to set this value on this field of this bean. If the field is not accessible make it accessible, set the value, then set it back to being in
	 * accessible.
	 */
	public static void set(Object instance, Field field, Object value) {
		try {
			synchronized (field) {
				boolean accessible = field.isAccessible();
				if (!accessible) {
					field.setAccessible(true);
				}
				field.set(instance, value);
				if (!accessible) {
					field.setAccessible(false);
				}
			}
		} catch (IllegalAccessException e) {
			throw new IllegalStateException(e);
		}
	}

	/**
	 * Get declared fields with the option to include inherited fields
	 */
	public static List<Field> getDeclaredFields(Class<?> type, boolean includeInheritedFields) {
		if (includeInheritedFields) {
			List<Field> fields = new ArrayList<Field>();
			for (Class<?> c = type; c != null; c = c.getSuperclass()) {
				fields.addAll(Arrays.asList(c.getDeclaredFields()));
			}
			return fields;
		} else {
			return Arrays.asList(type.getDeclaredFields());
		}
	}

	@SuppressWarnings("unchecked")
	public static Map<String, Object> describe(Object bean) {
		try {
			return BeanUtils.describe(bean);
		} catch (IllegalAccessException e) {
			throw new IllegalStateException(e);
		} catch (InvocationTargetException e) {
			throw new IllegalStateException(e);
		} catch (NoSuchMethodException e) {
			throw new IllegalStateException(e);
		}
	}

	public static void copyProperty(Object bean, String name, Object value) {
		try {
			BeanUtils.copyProperty(bean, name, value);
		} catch (IllegalAccessException e) {
			throw new IllegalStateException(e);
		} catch (InvocationTargetException e) {
			throw new IllegalStateException(e);
		}
	}

	public static Object invokeMethod(Class<?> targetClass, String targetMethod, Object... arguments) {
		MethodInvoker invoker = new MethodInvoker();
		invoker.setTargetClass(targetClass);
		invoker.setTargetMethod(targetMethod);
		invoker.setArguments(arguments);
		return invoke(invoker);
	}

	public static Object invokeMethod(Object targetObject, String targetMethod, Object... arguments) {
		MethodInvoker invoker = new MethodInvoker();
		invoker.setTargetObject(targetObject);
		invoker.setTargetMethod(targetMethod);
		invoker.setArguments(arguments);
		return invoke(invoker);
	}

	public static Object invoke(MethodInvoker invoker) {
		try {
			invoker.prepare();
			return invoker.invoke();
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException(e);
		} catch (NoSuchMethodException e) {
			throw new IllegalStateException(e);
		} catch (InvocationTargetException e) {
			throw new IllegalStateException(e);
		} catch (IllegalAccessException e) {
			throw new IllegalStateException(e);
		}
	}

	public static Class<?> getClass(String className) {
		try {
			return Class.forName(className);
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> Class<? extends T> getTypedClass(String className) {
		try {
			return (Class<? extends T>) Class.forName(className);
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public static <T> T newInstance(String className) {
		@SuppressWarnings("unchecked")
		Class<T> clazz = (Class<T>) getClass(className);
		return (T) newInstance(clazz);
	}

	public static <T> T newInstance(Class<T> instanceClass) {
		try {
			return (T) instanceClass.newInstance();
		} catch (IllegalAccessException e) {
			throw new IllegalArgumentException("Unexpected error", e);
		} catch (InstantiationException e) {
			throw new IllegalArgumentException("Unexpected error", e);
		}
	}

}
