package org.kuali.common.util.bind.impl;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;
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
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

@MetaInfServices(BinderService.class)
public final class DefaultBinderService implements BinderService {

	private final Environment environment;
	private final ConversionService service;

	@Override
	public <T> List<String> bind(T target) {
		if (!target.getClass().isAnnotationPresent(Bind.class)) {
			return ImmutableList.of();
		}

		List<String> errors = Lists.newArrayList();
		Set<Field> fields = getBindFields(target.getClass());
		for (Field field : fields) {
			Class<?> fieldType = field.getType();
			Class<? extends org.kuali.common.util.build.Builder<?>> builderType = getBuilder(fieldType);
			Bind bind = field.getAnnotation(Bind.class);
			org.kuali.common.util.build.Builder<?> builder = (org.kuali.common.util.build.Builder<?>) ReflectionUtils.newInstance(builderType);
			EnvironmentDataBinder binder = new EnvironmentDataBinder(builder, bind);
			binder.setConversionService(service);
			binder.bind(environment);
			BindingResult result = binder.getBindingResult();
			errors.addAll(getErrors(result));
			Object value = builder.build();
			ReflectionUtils.set(target, field, value);
		}
		Bind bind = target.getClass().getAnnotation(Bind.class);
		EnvironmentDataBinder binder = new EnvironmentDataBinder(target, bind);
		binder.setConversionService(service);
		binder.bind(environment);
		errors.addAll(getErrors(binder.getBindingResult()));
		return ImmutableList.copyOf(errors);
	}

	protected List<String> getErrors(BindingResult result) {
		List<String> errors = Lists.newArrayList();
		errors.addAll(getGlobalErrors(result.getGlobalErrors()));
		errors.addAll(getFieldErrors(result.getFieldErrors()));
		return errors;
	}

	protected List<String> getGlobalErrors(List<ObjectError> globalErrors) {
		List<String> errors = Lists.newArrayList();
		for (ObjectError globalError : globalErrors) {
			Optional<String> message = Optional.fromNullable(globalError.getDefaultMessage());
			if (message.isPresent()) {
				errors.add(message.get());
			} else {
				errors.add("Global binding error.  No default message was supplied.");
			}
		}
		return errors;
	}

	protected List<String> getFieldErrors(List<FieldError> fieldErrors) {
		List<String> errors = Lists.newArrayList();
		for (FieldError fieldError : fieldErrors) {
			String name = fieldError.getField();
			Object rejectedValue = fieldError.getRejectedValue();
			errors.add(String.format("Field level binding error.  Could not set [%s] to [%s]", name, rejectedValue));
		}
		return errors;
	}

	@SuppressWarnings("unchecked")
	protected Class<? extends org.kuali.common.util.build.Builder<?>> getBuilder(Class<?> type) {
		Class<?>[] declaredClasses = type.getDeclaredClasses();
		for (Class<?> declaredClass : declaredClasses) {
			if (org.kuali.common.util.build.Builder.class.isAssignableFrom(declaredClass)) {
				return (Class<? extends org.kuali.common.util.build.Builder<?>>) declaredClass;
			}
		}
		checkState(false, "No builder declared in [%s]", type.getCanonicalName());
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
