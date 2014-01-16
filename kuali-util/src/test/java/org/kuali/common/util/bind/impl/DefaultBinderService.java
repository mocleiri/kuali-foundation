package org.kuali.common.util.bind.impl;

import static com.google.common.base.Preconditions.checkState;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.ReflectionUtils;
import org.kuali.common.util.bind.api.BindMapping;
import org.kuali.common.util.bind.api.BinderService;
import org.kuali.common.util.bind.api.Bound;
import org.kuali.common.util.spring.binder.BytesFormatAnnotationFormatterFactory;
import org.kuali.common.util.spring.binder.TimeFormatAnnotationFormatterFactory;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.core.convert.ConversionService;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public class DefaultBinderService implements BinderService {

	private final ImmutableMap<String, String> global = getMap(PropertyUtils.getGlobalProperties());
	private final ConversionService service = getConversionService();

	@Override
	public <T> Optional<BindingResult> bind(T object) {
		Optional<Bound> bind = ReflectionUtils.getAnnotation(object.getClass(), Bound.class);
		if (bind.isPresent()) {
			Optional<String> prefix = getPrefix(bind.get(), object.getClass());
			ImmutableMap<String, String> map = getMap(prefix, global);
			MutablePropertyValues values = new MutablePropertyValues(map);
			DataBinder binder = new DataBinder(object);
			binder.setConversionService(service);
			binder.bind(values);
			return Optional.of(binder.getBindingResult());
		} else {
			return Optional.absent();
		}

	}

	protected Set<String> getKeys(Class<?> type, Bound annotation) {
		Optional<String> prefix = getPrefix(annotation, type);
		Set<Field> fields = ReflectionUtils.getFields(type);
		Set<String> keys = Sets.newTreeSet();
		for (Field field : fields) {
			keys.addAll(getKeys(prefix, field));
		}
		return ImmutableSet.copyOf(keys);
	}

	protected Set<String> getKeys(Optional<String> prefix, Field field) {
		Optional<BindMapping> annotation = ReflectionUtils.getAnnotation(field.getType(), BindMapping.class);
		if (annotation.isPresent()) {
			String[] mappings = annotation.get().value();
			return getKeys(prefix, ImmutableSet.copyOf(mappings));
		} else {
			return getKeys(prefix, ImmutableSet.of(field.getName()));
		}
	}

	protected Set<String> getKeys(Optional<String> prefix, Set<String> keys) {
		if (prefix.isPresent()) {
			Set<String> newKeys = Sets.newHashSet();
			for (String key : keys) {
				String newKey = getKey(prefix.get(), key);
				keys.add(newKey);
			}
			return newKeys;
		} else {
			return keys;
		}
	}

	protected String getKey(String prefix, String key) {
		return prefix + "." + key;
	}

	protected String getKey(Optional<String> prefix, String key) {
		if (prefix.isPresent()) {
			return getKey(prefix.get(), key);
		} else {
			return key;
		}
	}

	protected Set<String> getFieldNames(Class<?> type) {
		Set<Field> fields = ReflectionUtils.getFields(type);
		Set<String> names = Sets.newTreeSet();
		for (Field field : fields) {
			names.add(field.getName());
		}
		return ImmutableSet.copyOf(names);
	}

	protected Optional<String> getPrefix(Bound bound, Class<?> type) {
		// Do not use a prefix of any kind
		if (!bound.prefix()) {
			return Optional.absent();
		}

		// Use the name of the class as a prefix if the value is the empty string (the default)
		if (bound.value().equals("")) {
			return Optional.of(StringUtils.uncapitalize(type.getSimpleName()));
		}

		// If they supplied a value, it cannot be blank
		checkState(!StringUtils.isBlank(bound.value()), "'value' cannot be blank");

		// Return an optional containing the value
		return Optional.of(bound.value());
	}

	protected ImmutableMap<String, String> getMap(Optional<String> prefix, Map<String, String> provided) {
		if (!prefix.isPresent()) {
			return ImmutableMap.copyOf(provided);
		} else {
			return getMap(prefix.get() + ".", provided);
		}
	}

	protected ImmutableMap<String, String> getMap(String prefix, Map<String, String> map) {
		Map<String, String> newMap = Maps.newHashMap();
		for (String key : map.keySet()) {
			if (key.startsWith(prefix)) {
				String value = map.get(key);
				String newKey = key.substring(prefix.length());
				newMap.put(newKey, value);
			}
		}
		return ImmutableMap.copyOf(newMap);
	}

	protected ConversionService getConversionService() {
		DefaultFormattingConversionService service = new DefaultFormattingConversionService();
		service.addFormatterForFieldAnnotation(new BytesFormatAnnotationFormatterFactory());
		service.addFormatterForFieldAnnotation(new TimeFormatAnnotationFormatterFactory());
		return service;
	}

	protected ImmutableMap<String, String> getMap(Properties properties) {
		Map<String, String> map = Maps.newHashMap();
		for (String key : properties.stringPropertyNames()) {
			map.put(key, properties.getProperty(key));
		}
		return ImmutableMap.copyOf(map);
	}

}
