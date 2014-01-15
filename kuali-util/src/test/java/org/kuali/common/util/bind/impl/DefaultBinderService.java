package org.kuali.common.util.bind.impl;

import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.ReflectionUtils;
import org.kuali.common.util.bind.api.Bind;
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
import com.google.common.collect.Maps;

public class DefaultBinderService implements BinderService {

	private final ImmutableMap<String, String> global = getMap(PropertyUtils.getGlobalProperties());
	private final ConversionService service = getConversionService();

	@Override
	public <T> Optional<BindingResult> bind(T object) {
		Optional<Bind> bind = ReflectionUtils.getAnnotation(object.getClass(), Bind.class);
		if (bind.isPresent()) {
			Optional<String> prefix = Optional.fromNullable(StringUtils.trimToNull(bind.get().prefix()));
			ImmutableMap<String, String> map = prefix.isPresent() ? getMap(prefix.get() + ".", global) : global;
			DataBinder binder = new DataBinder(object);
			MutablePropertyValues pvs = new MutablePropertyValues(map);
			binder.setConversionService(service);
			binder.bind(pvs);
			return Optional.of(binder.getBindingResult());
		} else {
			return Optional.absent();
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
