package org.kuali.common.util.bind.impl;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.ListUtils;
import org.kuali.common.util.ReflectionUtils;
import org.kuali.common.util.bind.api.Alias;
import org.kuali.common.util.bind.api.Bind;
import org.kuali.common.util.bind.model.BoundFieldDescriptor;
import org.kuali.common.util.bind.model.BoundTypeDescriptor;
import org.kuali.common.util.build.Builder;
import org.springframework.core.env.Environment;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public class BindUtils {

	public static ImmutableMap<String, String> getMap(Class<?> type, Bind bind, Environment env) {
		checkNotNull(type, "'type' cannot be null");
		checkNotNull(bind, "'bind' cannot be null");
		checkNotNull(env, "'env' cannot be null");
		BoundTypeDescriptor descriptor = getDescriptor(type, bind);
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

	protected static BoundTypeDescriptor getDescriptor(Class<?> type, Bind bind) {
		Optional<String> prefix = getPrefix(bind, type);
		Map<Field, BoundFieldDescriptor> fields = getFields(type, prefix);
		return BoundTypeDescriptor.builder(type).fields(fields).build();
	}

	protected static Optional<String> getPrefix(Bind bound, Class<?> type) {
		if (bound.noPrefix()) {
			return Optional.absent();
		}

		if (!bound.prefix().equals(void.class)) {
			return Optional.of(StringUtils.uncapitalize(bound.prefix().getSimpleName()));
		}

		if (bound.value().equals("")) {
			Class<?> prefixClass = getPrefixClass(type, bound);
			return Optional.of(StringUtils.uncapitalize(prefixClass.getSimpleName()));
		} else {
			// If they supplied a value, it cannot be blank
			checkState(!StringUtils.isBlank(bound.value()), "'value' cannot be blank.");
			return Optional.of(bound.value());
		}
	}

	protected static Class<?> getPrefixClass(Class<?> type, Bind bound) {
		if (isBuilder(type) && type.getDeclaringClass() != null) {
			return type.getDeclaringClass();
		} else {
			return type;
		}
	}

	protected static boolean isBuilder(Class<?> type) {
		return Builder.class.isAssignableFrom(type);
	}

	protected static ImmutableMap<Field, BoundFieldDescriptor> getFields(Class<?> type, Optional<String> prefix) {
		Map<Field, BoundFieldDescriptor> map = Maps.newHashMap();
		Set<Field> fields = ReflectionUtils.getAllFields(type);
		for (Field field : fields) {
			map.put(field, getFieldKeys(field, prefix));
		}
		return ImmutableMap.copyOf(map);
	}

	protected static BoundFieldDescriptor getFieldKeys(Field field, Optional<String> prefix) {
		List<String> keys = getKeys(prefix, ImmutableList.of(field.getName()));
		Optional<Alias> mapping = ReflectionUtils.getAnnotation(field, Alias.class);
		if (mapping.isPresent()) {
			keys = getKeys(prefix, getKeys(field, mapping.get()));
		}
		// Ensure the keys are unique
		keys = Lists.newArrayList(Sets.newLinkedHashSet(keys));
		return BoundFieldDescriptor.builder(field).mapping(mapping).keys(keys).build();
	}

	protected static List<String> getKeys(Field field, Alias annotation) {
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
