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
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.util.MethodInvoker;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

public class ReflectionUtils extends org.springframework.util.ReflectionUtils {

	/**
	 * Return true if this class is an immutable Guava collection
	 */
	public static boolean isImmutableGuavaCollection(Class<?> type) {
		return ImmutableCollection.class.isAssignableFrom(type);
	}

	/**
	 * Return true if this class is an immutable Guava map
	 */
	public static boolean isImmutableGuavaMap(Class<?> type) {
		return ImmutableMap.class.isAssignableFrom(type);
	}

	/**
	 * Return true if this field is a {@code java.util.Collection}
	 */
	public static boolean isCollection(Field field) {
		return Collection.class.isAssignableFrom(field.getType());
	}

	/**
	 * Return true if this field is a {@code java.util.Collection<String>}
	 */
	public static boolean isStringCollection(Field field) {
		return isCollection(field) && hasMatchingParameterizedArgTypes(field, String.class);
	}

	/**
	 * Return true if this field is a {@code java.util.Map}
	 */
	public static boolean isMap(Field field) {
		return Map.class.isAssignableFrom(field.getType());
	}

	/**
	 * Return true if this field is a {@code java.util.Map} that uses {@code String} for its keys
	 * 
	 * <pre>
	 * Map&lt;String,String>  returns true
	 * Map&lt;String,Object>  returns true
	 * Map&lt;String,Integer> returns true
	 * Map&lt;Integer,String> returns false
	 * </pre>
	 */
	public static boolean isStringKeyedMap(Field field) {
		return isMap(field) && hasMatchingParameterizedArgTypes(field, String.class);
	}

	/**
	 * Return true if this field is a CharSequence
	 */
	public static boolean isCharSequence(Field field) {
		return isCharSequence(field.getType());
	}

	/**
	 * Return true if this field is a CharSequence
	 */
	public static boolean isCharSequence(Class<?> type) {
		return CharSequence.class.isAssignableFrom(type);
	}

	/**
	 * Return true iff this field is a Guava {@code com.google.common.base.Optional}
	 */
	public static boolean isOptional(Field field) {
		return Optional.class.isAssignableFrom(field.getType());
	}

	/**
	 * Return true iff this field is a Guava {@code com.google.common.base.Optional<String>}
	 */
	public static boolean isOptionalString(Field field) {
		return isOptional(field) && hasMatchingParameterizedArgTypes(field, String.class);
	}

	/**
	 * <p>
	 * Return true if this field is a generic whose argument types match {@code expectedTypeArguments}
	 * </p>
	 * 
	 * For example to match a field declared as {@code Collection<String>}
	 * 
	 * <pre>
	 * hasMatchingParameterizedArgTypes(myField, String.class)
	 * </pre>
	 */
	public static boolean hasMatchingParameterizedArgTypes(Field field, Class<?>... expectedTypeArguments) {
		if (!isParameterizedType(field)) {
			return false;
		}
		ParameterizedType parameterizedType = (ParameterizedType) field.getGenericType();
		return hasMatchingActualTypeArguments(parameterizedType, expectedTypeArguments);
	}

	protected static boolean hasMatchingActualTypeArguments(ParameterizedType type, Class<?>... expectedTypeArguments) {
		Type[] actualTypeArguments = type.getActualTypeArguments();
		for (int i = 0; i < expectedTypeArguments.length; i++) {
			Class<?> expectedTypeArgument = expectedTypeArguments[i];
			if (i >= actualTypeArguments.length) {
				return false;
			}
			Class<?> actualTypeArgument = (Class<?>) actualTypeArguments[i];
			if (actualTypeArgument != expectedTypeArgument) {
				return false;
			}
		}
		return true;
	}

	public static boolean isParameterizedType(Field field) {
		Type genericType = field.getGenericType();
		return genericType instanceof ParameterizedType;
	}

	/**
	 * <p>
	 * Throw an exception unless {@code child} is the same as {@code parent} <b>OR</b> descends from {@code parent}. If {@code child} is a primitive type, throw an exception unless
	 * both {@code child} and {@code parent} are the exact same primitive type.
	 * </p>
	 * 
	 * @see equalsOrDescendsFrom
	 * 
	 * @throws IllegalArgumentException
	 *             if {@code equalsOrDescendsFrom(child,parent)} returns {@code false}
	 */
	public static void validateEqualsOrDescendsFrom(Class<?> child, Class<?> parent) {
		boolean expression = equalsOrDescendsFrom(child, parent);
		Preconditions.checkArgument(expression, "[%s] must descend from (or be) [%s]", child.getCanonicalName(), parent.getCanonicalName());
	}

	/**
	 * <p>
	 * Return true if {@code child} is the same class as {@code parent} <b>OR</b> descends from {@code parent}. If {@code child} is a primitive type, return {@code true} only if
	 * both {@code child} and {@code parent} are the exact same primitive type.
	 * </p>
	 */
	public static boolean equalsOrDescendsFrom(Class<?> child, Class<?> parent) {
		return parent.isAssignableFrom(child);
	}

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

	public static Map<String, Field> getNameMap(Set<Field> fields) {
		Map<String, Field> map = new HashMap<String, Field>();
		for (Field field : fields) {
			map.put(field.getName(), field);
		}
		return map;
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
			Set<Field> set = getFields(c);
			fields.addAll(set);
		}
		return ImmutableSet.copyOf(fields);
	}

	/**
	 * Return true if the fields in this set can be uniquely represented by field name alone
	 */
	public static boolean hasUniqueFieldNames(Set<Field> fields) {
		Map<String, Field> map = new HashMap<String, Field>();
		for (Field field : fields) {
			map.put(field.getName(), field);
		}
		return map.size() == fields.size();
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
