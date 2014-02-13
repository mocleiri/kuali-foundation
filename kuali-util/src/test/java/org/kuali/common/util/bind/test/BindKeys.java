package org.kuali.common.util.bind.test;

import static com.google.common.collect.Lists.newArrayList;
import static org.kuali.common.util.Annotations.extractAnnotationFromField;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;

import org.kuali.common.util.ReflectionUtils;
import org.kuali.common.util.bind.api.Alias;
import org.kuali.common.util.bind.api.Bind;
import org.kuali.common.util.function.PrefixFunction;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class BindKeys {

	public static Set<String> get(Class<?> type) {
		return get(Optional.<String> absent(), type);
	}

	public static Set<String> get(Optional<String> prefix, Class<?> type) {
		SortedSet<String> keys = Sets.newTreeSet();
		// Optional<BindingPrefix> annotation = Annotations.get(type, BindingPrefix.class);
		Optional<String> actualPrefix = null; // Prefixes.get(prefix, type, annotation);
		Set<Field> fields = ReflectionUtils.getAllFields(type);
		for (Field field : fields) {
			Set<String> fieldKeys = getKeys(field, actualPrefix);
			keys.addAll(fieldKeys);
		}
		return keys;
	}

	protected static Set<String> getKeys(Field field, Optional<String> prefix) {
		// If the Bind annotation is present we'll need to recurse
		if (field.isAnnotationPresent(Bind.class)) {
			// Optional<BindingPrefix> annotation = Annotations.get(field, BindingPrefix.class);
			Optional<String> fieldPrefix = null; // Prefixes.get(field.getType(), annotation);
			Optional<String> newPrefix = combine(prefix, fieldPrefix, ".");
			// Recurse to acquire more keys
			return get(newPrefix, field.getType());
		} else {
			// Otherwise just get the keys for this field (including any aliases)
			List<String> fieldKeys = getKeys(field);
			return Sets.newHashSet(transform(fieldKeys, prefix, "."));
		}
	}

	protected static Optional<String> combine(Optional<String> s1, Optional<String> s2, String separator) {
		StringBuilder sb = new StringBuilder();
		if (s1.isPresent()) {
			sb.append(s1.get());
		}
		if (s1.isPresent() && s2.isPresent()) {
			sb.append(separator);
		}
		if (s2.isPresent()) {
			sb.append(s2.get());
		}
		if (sb.length() == 0) {
			return Optional.absent();
		} else {
			return Optional.of(sb.toString());
		}
	}

	protected static List<String> transform(List<String> original, Optional<String> prefix, String separator) {
		if (prefix.isPresent()) {
			Function<String, String> prefixer = PrefixFunction.of(prefix.get(), separator);
			return Lists.transform(original, prefixer);
		} else {
			return original;
		}
	}

	protected static List<String> getKeys(Field field) {
		Optional<Alias> optional = extractAnnotationFromField(field, Alias.class);
		if (!optional.isPresent()) {
			return ImmutableList.of(field.getName());
		} else {
			Alias annotation = optional.get();
			List<String> keys = newArrayList();
			keys.addAll(ImmutableList.copyOf(annotation.value()));
			keys.add(field.getName());
			return keys;
		}
	}

}
