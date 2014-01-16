package org.kuali.common.util.bind.impl;

import static com.google.common.base.Preconditions.checkState;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.ListUtils;
import org.kuali.common.util.ReflectionUtils;
import org.kuali.common.util.bind.api.BindAlias;
import org.kuali.common.util.bind.api.Bound;
import org.kuali.common.util.bind.model.BoundFieldDescriptor;
import org.kuali.common.util.bind.model.BoundTypeDescriptor;
import org.kuali.common.util.builder.Builder;
import org.springframework.core.env.Environment;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public class BindUtils {

	private static final ConcurrentMap<Class<?>, BoundTypeDescriptor> CACHE = Maps.newConcurrentMap();

	public static ImmutableMap<String, String> getMap(Class<?> type, Environment env) {
		BoundTypeDescriptor descriptor = getDescriptor(type);
		return getMap(descriptor, env);
	}

	protected static ImmutableMap<String, String> getMap(BoundTypeDescriptor descriptor, Environment env) {
		Map<Field, BoundFieldDescriptor> fields = descriptor.getFields();
		Map<String, String> map = Maps.newHashMap();
		for (BoundFieldDescriptor bfd : fields.values()) {
			Optional<String> value = getValue(bfd, env);
			if (value.isPresent()) {
				map.put(bfd.getField().getName(), value.get());
			}
		}
		return ImmutableMap.copyOf(map);
	}

	protected static Optional<String> getValue(BoundFieldDescriptor descriptor, Environment env) {
		for (String key : descriptor.getKeys()) {
			if (env.containsProperty(key)) {
				return Optional.of(env.getProperty(key));
			}
		}
		return Optional.absent();
	}

	protected static BoundTypeDescriptor getDescriptor(Class<?> type) {
		if (!CACHE.containsKey(type)) {
			Bound bound = type.getAnnotation(Bound.class);
			Optional<String> prefix = getPrefix(bound, type);
			Map<Field, BoundFieldDescriptor> fields = getFields(type, prefix);
			CACHE.putIfAbsent(type, BoundTypeDescriptor.builder(type).fields(fields).build());
		}
		return CACHE.get(type);
	}

	protected static Optional<String> getPrefix(Bound bound, Class<?> type) {
		// Do not use a prefix of any kind
		if (!bound.prefix()) {
			return Optional.absent();
		}

		// There can be 2 reasons why value() is the empty string
		// 1 - They didn't supply value() and so it is still at the default
		// 2 - They did supply value() but they supplied it as ""
		// In either case, we ignore value() and use the uncapitalized class name instead
		// They can use prefix=false to skip using a prefix entirely
		if (bound.value().equals(Bound.DEFAULT)) {
			Class<?> prefixClass = getPrefixClass(type);
			return Optional.of(StringUtils.uncapitalize(prefixClass.getSimpleName()));
		}

		// If they supplied a value, it cannot be blank
		checkState(!StringUtils.isBlank(bound.value()), "'value' cannot be blank");

		// Return an optional containing the value
		return Optional.of(bound.value());
	}

	protected static Class<?> getPrefixClass(Class<?> type) {
		Optional<Class<?>> declaringClass = Optional.<Class<?>> of(type.getDeclaringClass());
		if (isBuilder(type)) {
			checkState(declaringClass.isPresent(), "[%s] is a builder but has no declaring class", type.getCanonicalName());
			return declaringClass.get();
		} else {
			return type;
		}
	}

	protected static boolean isBuilder(Class<?> type) {
		return Builder.class.isAssignableFrom(type);
	}

	protected static ImmutableMap<Field, BoundFieldDescriptor> getFields(Class<?> type, Optional<String> prefix) {
		Map<Field, BoundFieldDescriptor> map = Maps.newHashMap();
		Set<Field> fields = ReflectionUtils.getFields(type);
		for (Field field : fields) {
			map.put(field, getFieldKeys(field, prefix));
		}
		return ImmutableMap.copyOf(map);
	}

	protected static BoundFieldDescriptor getFieldKeys(Field field, Optional<String> prefix) {
		List<String> keys = getKeys(prefix, ImmutableList.of(field.getName()));
		Optional<BindAlias> mapping = ReflectionUtils.getAnnotation(field, BindAlias.class);
		if (mapping.isPresent()) {
			keys = getKeys(prefix, getKeys(field, mapping.get()));
		}
		// Ensure the keys are unique
		keys = Lists.newArrayList(Sets.newLinkedHashSet(keys));
		return BoundFieldDescriptor.builder(field).mapping(mapping).keys(keys).build();
	}

	protected static List<String> getKeys(Field field, BindAlias annotation) {
		List<String> mappings = ImmutableList.copyOf(annotation.value());
		int blanks = CollectionUtils.getBlanks(mappings).size();
		checkState(blanks == 0, "[%s.%s] contains %s bind mappings that are blank", field.getDeclaringClass().getCanonicalName(), field.getName(), blanks);
		List<String> keys = Lists.newArrayList();
		keys.addAll(mappings);
		keys.add(field.getName());
		return keys;
	}

	protected static List<String> getKeys(Optional<String> prefix, List<String> keys) {
		if (prefix.isPresent()) {
			return ListUtils.prefix(prefix.get(), ".", keys);
		} else {
			return keys;
		}
	}
}
