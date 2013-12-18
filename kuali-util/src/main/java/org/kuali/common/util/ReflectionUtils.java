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

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.util.MethodInvoker;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableSet;

public class ReflectionUtils extends org.springframework.util.ReflectionUtils {

	public static <T extends Annotation> Optional<T> getAnnotation(Class<?> instanceClass, Class<T> annotationClass) {
		return Optional.fromNullable(instanceClass.getAnnotation(annotationClass));
	}

	public static List<Class<?>> getDeclarationHierarchy(Class<?> type) {
		List<Class<?>> hierarchy = new ArrayList<Class<?>>();
		Class<?> declaringClass = type.getDeclaringClass();
		if (declaringClass != null) {
			hierarchy.addAll(getDeclarationHierarchy(declaringClass));
		}
		hierarchy.add(type);
		return hierarchy;
	}

	public static String getDeclarationPath(Class<?> type) {
		List<Class<?>> hierarchy = getDeclarationHierarchy(type);
		List<String> names = new ArrayList<String>();
		for (Class<?> element : hierarchy) {
			names.add(element.getSimpleName());
		}
		return CollectionUtils.getStringWithSeparator(names, ".");
	}

	/**
	 * Unconditionally attempt to get the value of this field on this bean. If the field is not accessible make it accessible, get the value, then revert the field back to being
	 * inaccessible.
	 */
	public static Optional<?> get(Field field, Object instance) {

		// Be thread safe
		synchronized (field) {

			// Preserve the original accessibility indicator
			boolean accessible = field.isAccessible();

			// If it's not accessible, change it so it is
			if (!accessible) {
				field.setAccessible(true);
			}

			try {
				// Attempt to get the value of this field on the instance
				return Optional.fromNullable(field.get(instance));
			} catch (IllegalAccessException e) {
				throw new IllegalStateException(e);
			} finally {
				// Always flip the accessible flag back to what it was before (if we need to)
				if (!accessible) {
					field.setAccessible(false);
				}
			}
		}
	}

	/**
	 * Unconditionally attempt to set a value on this field of this instance. If the field is not accessible, make it accessible, set the value, then revert the field to being
	 * inaccessible.
	 */
	public static void set(Object instance, Field field, Object value) {

		// Be thread safe
		synchronized (field) {

			// Preserve the original accessibility indicator
			boolean accessible = field.isAccessible();

			// If it's not accessible, change it so it is
			if (!accessible) {
				field.setAccessible(true);
			}

			try {
				// Attempt to set the value on this field of the instance
				field.set(instance, value);
			} catch (IllegalAccessException e) {
				throw new IllegalStateException(e);
			} finally {
				// Always flip the accessible flag back to what it was before (if we need to)
				if (!accessible) {
					field.setAccessible(false);
				}
			}
		}
	}

	/**
	 * Get fields declared directly on this type as an immutable set.
	 */
	public static Set<Field> getFields(Class<?> type) {
		return ImmutableSet.copyOf(type.getDeclaredFields());
	}

	/**
	 * Get fields for a given type with the option to include all inherited fields
	 * 
	 * <p>
	 * NOTE: field.getName() is not necessarily unique for the elements in the set if includeInheritedFields is true
	 * </p>
	 */
	public static Set<Field> getFields(Class<?> type, boolean includeInheritedFields) {
		if (includeInheritedFields) {
			return getAllFields(type);
		} else {
			return getFields(type);
		}
	}

	/**
	 * <p>
	 * Recursively examine the type hierarchy and extract every field encountered anywhere in the hierarchy into an immutable set
	 * </p>
	 * 
	 * <p>
	 * NOTE: field.getName() is not necessarily unique for the elements in the set
	 * </p>
	 */
	public static Set<Field> getAllFields(Class<?> type) {
		Set<Field> fields = new HashSet<Field>();
		for (Class<?> c = type; c != null; c = c.getSuperclass()) {
			fields.addAll(getFields(type));
		}
		return ImmutableSet.copyOf(fields);
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
