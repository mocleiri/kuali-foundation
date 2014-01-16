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
import org.kuali.common.util.bind.model.BoundFieldDescriptor;
import org.kuali.common.util.bind.model.BoundTypeDescriptor;
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
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public class DefaultBinderService implements BinderService {

	private final ImmutableMap<String, String> global = getMap(PropertyUtils.getGlobalProperties());
	private final ConversionService service = getConversionService();

	@Override
	public <T> Optional<BindingResult> bind(T object) {
		if (object.getClass().isAnnotationPresent(Bound.class)) {
			BoundTypeDescriptor descriptor = getBindingDescriptor(object.getClass());
			ImmutableMap<String, String> map = getMap(descriptor, global);
			MutablePropertyValues values = new MutablePropertyValues(map);
			DataBinder binder = new DataBinder(object);
			binder.setConversionService(service);
			binder.bind(values);
			return Optional.of(binder.getBindingResult());
		} else {
			return Optional.absent();
		}

	}

	protected BoundTypeDescriptor getBindingDescriptor(Class<?> type) {
		Bound bound = type.getAnnotation(Bound.class);
		Optional<String> prefix = getPrefix(bound, type);
		Map<Field, BoundFieldDescriptor> fields = getFields(type, prefix);
		return BoundTypeDescriptor.builder(type).fields(fields).build();
	}

	protected ImmutableMap<Field, BoundFieldDescriptor> getFields(Class<?> type, Optional<String> prefix) {
		Map<Field, BoundFieldDescriptor> map = Maps.newHashMap();
		Set<Field> fields = ReflectionUtils.getFields(type);
		for (Field field : fields) {
			map.put(field, getFieldKeys(field, prefix));
		}
		return ImmutableMap.copyOf(map);
	}

	protected BoundFieldDescriptor getFieldKeys(Field field, Optional<String> prefix) {
		List<String> keys = getKeys(prefix, ImmutableList.of(field.getName()));
		Optional<BindMapping> mapping = ReflectionUtils.getAnnotation(field.getType(), BindMapping.class);
		if (mapping.isPresent()) {
			keys = getKeys(prefix, getKeys(field, mapping.get()));
		}
		// Ensure the keys are unique
		keys = Lists.newArrayList(Sets.newLinkedHashSet(keys));
		return BoundFieldDescriptor.builder(field).mapping(mapping).keys(keys).build();
	}

	protected List<String> getKeys(Field field, BindMapping annotation) {
		List<String> mappings = ImmutableList.copyOf(annotation.value());
		int blanks = CollectionUtils.getBlanks(mappings).size();
		checkState(blanks == 0, "[%s.%s] contains %s bind mappings that are blank", field.getDeclaringClass().getCanonicalName(), field.getName(), blanks);
		List<String> keys = Lists.newArrayList();
		keys.addAll(mappings);
		keys.add(field.getName());
		return keys;
	}

	protected List<String> getKeys(Optional<String> prefix, List<String> keys) {
		if (prefix.isPresent()) {
			return ListUtils.prefix(prefix.get(), ".", keys);
		} else {
			return keys;
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

	protected ImmutableMap<String, String> getMap(BoundTypeDescriptor descriptor, Map<String, String> provided) {
		Map<Field, BoundFieldDescriptor> fields = descriptor.getFields();
		Map<String, String> map = Maps.newHashMap();
		for (BoundFieldDescriptor bfd : fields.values()) {
			Optional<String> value = getValue(bfd, provided);
			if (value.isPresent()) {
				map.put(bfd.getField().getName(), value.get());
			}
		}
		return ImmutableMap.copyOf(map);
	}

	protected Optional<String> getValue(BoundFieldDescriptor descriptor, Map<String, String> provided) {
		List<String> keys = descriptor.getKeys();
		for (String key : keys) {
			Optional<String> value = Optional.of(provided.get(key));
			if (value.isPresent()) {
				return value;
			}
		}
		return Optional.absent();

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
