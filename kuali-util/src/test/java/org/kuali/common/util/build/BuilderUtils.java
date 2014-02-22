package org.kuali.common.util.build;

import static java.lang.reflect.Modifier.isPublic;
import static org.kuali.common.util.ReflectionUtils.newInstance;
import static org.kuali.common.util.base.Exceptions.illegalArgument;

import java.util.List;

import org.apache.commons.lang3.builder.Builder;

import com.google.common.collect.ImmutableList;

public class BuilderUtils {

	public static <T> Builder<T> newBuilderInstance(Class<T> type) {
		Class<Builder<T>> builderClass = findPublicBuilderClass(type);
		return newInstance(builderClass);
	}

	@SuppressWarnings("unchecked")
	public static <T> Class<Builder<T>> findPublicBuilderClass(Class<T> type) {
		List<Class<?>> declaredClasses = ImmutableList.copyOf(type.getDeclaredClasses());
		for (Class<?> declaredClass : declaredClasses) {
			boolean assignable = Builder.class.isAssignableFrom(declaredClass);
			if (assignable && isPublic(declaredClass.getModifiers())) {
				return (Class<Builder<T>>) declaredClass;
			}
		}
		throw illegalArgument("no public [%s] declared in [%s]", Builder.class.getCanonicalName(), type.getCanonicalName());
	}

}
