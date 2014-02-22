package org.kuali.common.util.build;

import static com.google.common.base.Optional.absent;
import static java.lang.reflect.Modifier.isPublic;
import static java.lang.reflect.Modifier.isStatic;
import static org.kuali.common.util.ReflectionUtils.newInstance;
import static org.kuali.common.util.base.Exceptions.illegalArgument;

import java.util.List;

import org.apache.commons.lang3.builder.Builder;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

public class BuilderUtils {

	/**
	 * Locates a public static class declared in type that implements {@code Builder<T>} and creates a new instance of it.
	 * 
	 * @throws IllegalArgumentException
	 *             if there is no public static class declared in type that implements {@code Builder<T>}
	 */
	public static <T> Builder<T> newBuilderInstance(Class<T> type) {
		Optional<Class<Builder<T>>> builderClass = findPublicStaticBuilderClass(type);
		if (!builderClass.isPresent()) {
			throw illegalArgument("no public [%s] declared in [%s]", Builder.class.getCanonicalName(), type.getCanonicalName());
		} else {
			return newInstance(builderClass.get());
		}
	}

	public static boolean declaresPublicStaticBuilderClass(Class<?> type) {
		return findPublicStaticBuilderClass(type).isPresent();
	}

	@SuppressWarnings("unchecked")
	public static <T> Optional<Class<Builder<T>>> findPublicStaticBuilderClass(Class<T> type) {
		List<Class<?>> declaredClasses = ImmutableList.copyOf(type.getDeclaredClasses());
		for (Class<?> declaredClass : declaredClasses) {
			boolean assignable = Builder.class.isAssignableFrom(declaredClass);
			if (assignable && isPublicStatic(declaredClass)) {
				return Optional.of((Class<Builder<T>>) declaredClass);
			}
		}
		return absent();
	}

	protected static boolean isPublicStatic(Class<?> type) {
		return isPublic(type.getModifiers()) && isStatic(type.getModifiers());
	}

}
