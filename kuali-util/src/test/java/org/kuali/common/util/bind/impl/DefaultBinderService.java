package org.kuali.common.util.bind.impl;

import static com.google.common.base.Preconditions.checkState;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.ListUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.ReflectionUtils;
import org.kuali.common.util.bind.api.BindMapping;
import org.kuali.common.util.bind.api.BinderService;
import org.kuali.common.util.bind.api.Bound;
import org.kuali.common.util.bind.model.FieldKeys;
import org.kuali.common.util.spring.binder.BytesFormatAnnotationFormatterFactory;
import org.kuali.common.util.spring.binder.TimeFormatAnnotationFormatterFactory;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.core.convert.ConversionService;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

public class DefaultBinderService implements BinderService {

	private final ImmutableMap<String, String> global = getMap(PropertyUtils.getGlobalProperties());
	private final ConversionService service = getConversionService();

	@Override
	public <T> Optional<BindingResult> bind(T object) {
		Optional<Bound> bound = ReflectionUtils.getAnnotation(object.getClass(), Bound.class);
		if (bound.isPresent()) {
			Optional<String> prefix = getPrefix(bound.get(), object.getClass());
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

	protected ImmutableMap<Field, FieldKeys> getFieldKeys(Class<?> type, Optional<String> prefix) {
		Map<Field, FieldKeys> map = Maps.newHashMap();
		Set<Field> fields = ReflectionUtils.getFields(type);
		for (Field field : fields) {
			FieldKeys fk = getFieldKeys(field, prefix);
			map.put(field, fk);
		}
		return ImmutableMap.copyOf(map);
	}

	protected FieldKeys getFieldKeys(Field field, Optional<String> prefix) {
		String actualPrefix = prefix.isPresent() ? prefix.get() + "." : "";
		Optional<BindMapping> annotation = Optional.fromNullable(field.getAnnotation(BindMapping.class));
		if (annotation.isPresent()) {
			List<String> mappings = ImmutableList.copyOf(annotation.get().value());
			int blanks = CollectionUtils.getBlanks(mappings).size();
			checkState(blanks == 0, "[%s.%s] contains %s bind mappings that are blank", field.getDeclaringClass().getSimpleName(), field.getName(), blanks);
			List<String> prefixed = ListUtils.prefix(actualPrefix, mappings);
			return FieldKeys.builder(field).keys(prefixed).build();
		} else {
			return FieldKeys.builder(field).key(actualPrefix + field.getName()).build();
		}
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
