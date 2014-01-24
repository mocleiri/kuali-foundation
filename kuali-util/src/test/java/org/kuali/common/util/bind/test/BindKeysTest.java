package org.kuali.common.util.bind.test;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;

import org.junit.Test;
import org.kuali.common.util.Annotations;
import org.kuali.common.util.ReflectionUtils;
import org.kuali.common.util.bind.api.Bind;
import org.kuali.common.util.bind.api.BindingAlias;
import org.kuali.common.util.bind.api.BindingPrefix;
import org.kuali.common.util.system.SystemProperties;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class BindKeysTest {

	@Test
	public void test() {
		try {
			Set<String> keys = getKeys(SystemProperties.class);
			System.out.println(String.format("----- %s Keys -----", keys.size()));
			for (String key : keys) {
				System.out.println(key);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Set<String> getKeys(Class<?> type) {
		return getKeys(Optional.<String> absent(), type);
	}

	public Set<String> getKeys(Optional<String> prefix, Class<?> type) {
		SortedSet<String> keys = Sets.newTreeSet();
		Optional<BindingPrefix> annotation = Annotations.get(type, BindingPrefix.class);
		Optional<String> actualPrefix = Prefixer.get(prefix, type, annotation);
		Set<Field> fields = ReflectionUtils.getAllFields(type);
		for (Field field : fields) {
			Set<String> fieldKeys = getKeys(field, actualPrefix);
			keys.addAll(fieldKeys);
		}
		return keys;
	}

	protected Set<String> getKeys(Field field, Optional<String> prefix) {
		// If the Bind annotation is present we'll need to recurse
		if (field.isAnnotationPresent(Bind.class)) {
			Optional<BindingPrefix> annotation = Annotations.get(field, BindingPrefix.class);
			Optional<String> fieldPrefix = Prefixer.get(field.getType(), annotation);
			Optional<String> newPrefix = combine(prefix, fieldPrefix, ".");
			// Recurse to acquire more keys
			return getKeys(newPrefix, field.getType());
		} else {
			// Otherwise just get the keys for this field (including any aliases)
			List<String> fieldKeys = getKeys(field);
			return Sets.newHashSet(transform(fieldKeys, prefix, "."));
		}
	}

	protected Optional<String> combine(Optional<String> s1, Optional<String> s2, String separator) {
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
			return Optional.fromNullable(sb.toString());
		}
	}

	protected List<String> transform(List<String> original, Optional<String> prefix, String separator) {
		if (prefix.isPresent()) {
			Function<String, String> prefixer = PrefixFunction.make(prefix.get(), separator);
			return Lists.transform(original, prefixer);
		} else {
			return original;
		}
	}

	protected List<String> getKeys(Field field) {
		Optional<BindingAlias> bindingAlias = Annotations.get(field, BindingAlias.class);
		if (!bindingAlias.isPresent()) {
			return ImmutableList.of(field.getName());
		} else {
			List<String> keys = Lists.newArrayList();
			keys.addAll(ImmutableList.copyOf(bindingAlias.get().value()));
			if (bindingAlias.get().includeFieldName()) {
				keys.add(field.getName());
			}
			return keys;
		}
	}

}
