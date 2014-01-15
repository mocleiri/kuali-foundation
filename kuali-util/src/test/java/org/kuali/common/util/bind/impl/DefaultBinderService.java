package org.kuali.common.util.bind.impl;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.ReflectionUtils;
import org.kuali.common.util.bind.api.Bind;
import org.kuali.common.util.bind.api.BindMappings;
import org.kuali.common.util.bind.api.BinderService;
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
		Optional<Bind> bind = ReflectionUtils.getAnnotation(object.getClass(), Bind.class);
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

	protected Set<String> getKeys(Class<?> type, Bind annotation) {
		Optional<String> prefix = getPrefix(annotation, type);
		Set<Field> fields = ReflectionUtils.getFields(type);
		Set<String> keys = Sets.newTreeSet();
		for (Field field : fields) {
			keys.addAll(getKeys(prefix, field));
		}
		return ImmutableSet.copyOf(keys);
	}

	protected Set<String> getKeys(Optional<String> prefix, Field field) {
		Optional<BindMappings> annotation = ReflectionUtils.getAnnotation(field.getType(), BindMappings.class);
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
				keys.add(getKey(prefix.get(), key));
			}
			return newKeys;
		} else {
			return keys;
		}
	}

	protected String getKey(String prefix, String key) {
		return prefix + "." + key;
	}

	protected Set<String> getFieldNames(Class<?> type) {
		Set<Field> fields = ReflectionUtils.getFields(type);
		Set<String> names = Sets.newTreeSet();
		for (Field field : fields) {
			names.add(field.getName());
		}
		return ImmutableSet.copyOf(names);
	}

	protected Optional<String> getPrefix(Bind bind, Class<?> type) {
		if (!bind.prefix()) {
			return Optional.absent();
		}
		Optional<String> prefix = Optional.fromNullable(StringUtils.trimToNull(bind.value()));
		if (prefix.isPresent()) {
			return prefix;
		} else {
			return Optional.of(StringUtils.uncapitalize(type.getSimpleName()));
		}
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
