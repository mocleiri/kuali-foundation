package org.kuali.common.util.bind.impl;

import static com.google.common.base.Preconditions.checkNotNull;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Set;

import org.kohsuke.MetaInfServices;
import org.kuali.common.util.ReflectionUtils;
import org.kuali.common.util.bind.api.Bind;
import org.kuali.common.util.bind.api.BinderService;
import org.kuali.common.util.spring.convert.Conversion;
import org.kuali.common.util.spring.env.Environments;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.env.Environment;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.MapBindingResult;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

@MetaInfServices(BinderService.class)
public final class DefaultBinderService implements BinderService {

	private final Environment environment;
	private final ConversionService service;

	@Override
	public <T> BindingResult bind(T target) {
		if (!target.getClass().isAnnotationPresent(Bind.class)) {
			return new MapBindingResult(Maps.newHashMap(), DataBinder.DEFAULT_OBJECT_NAME);
		}

		Set<Field> fields = getBindFields(target.getClass());
		for (Field field : fields) {
			Class<?> fieldType = field.getType();
			Class<? extends org.kuali.common.util.build.Builder<?>> builderType = getBuilder(fieldType);
			Bind bind = field.getAnnotation(Bind.class);
			org.kuali.common.util.build.Builder<?> builder = (org.kuali.common.util.build.Builder<?>) ReflectionUtils.newInstance(builderType);
			EnvironmentDataBinder binder = new EnvironmentDataBinder(builder, bind);
			binder.setConversionService(service);
			binder.bind(environment);
			Object value = builder.build();
			ReflectionUtils.set(target, field, value);
		}
		Bind bind = target.getClass().getAnnotation(Bind.class);
		EnvironmentDataBinder binder = new EnvironmentDataBinder(target, bind);
		binder.setConversionService(service);
		binder.bind(environment);
		return binder.getBindingResult();
	}

	@SuppressWarnings("unchecked")
	protected Class<? extends org.kuali.common.util.build.Builder<?>> getBuilder(Class<?> type) {
		Class<?>[] declaredClasses = type.getDeclaredClasses();
		for (Class<?> declaredClass : declaredClasses) {
			String name = declaredClass.getCanonicalName();
			if (name.endsWith("Builder")) {
				return (Class<? extends org.kuali.common.util.build.Builder<?>>) declaredClass;
			}
		}
		return null;
	}

	protected Set<Field> getBindFields(Class<?> type) {
		Set<Field> fields = Sets.newHashSet(ReflectionUtils.getAllFields(type));
		Iterator<Field> itr = fields.iterator();
		while (itr.hasNext()) {
			Field field = itr.next();
			if (!field.isAnnotationPresent(Bind.class)) {
				itr.remove();
			}
		}
		return fields;
	}

	public DefaultBinderService() {
		this(builder());
		Builder.validate(this);
	}

	private DefaultBinderService(Builder builder) {
		this.environment = builder.environment;
		this.service = builder.service;
	}

	public static DefaultBinderService create() {
		return builder().build();
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder implements org.kuali.common.util.build.Builder<DefaultBinderService> {

		private Environment environment = Environments.getDefaultEnvironment();
		private ConversionService service = Conversion.getDefaultConversionService();

		public Builder environment(Environment environment) {
			this.environment = environment;
			return this;
		}

		public Builder service(ConversionService service) {
			this.service = service;
			return this;
		}

		@Override
		public DefaultBinderService build() {
			DefaultBinderService instance = new DefaultBinderService(this);
			validate(instance);
			return instance;
		}

		private static void validate(DefaultBinderService instance) {
			checkNotNull(instance.environment, "'environment' cannot be null");
			checkNotNull(instance.service, "'service' cannot be null");
		}

	}

}
