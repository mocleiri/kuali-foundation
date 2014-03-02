package org.kuali.common.core.build;

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
	 *             if there is no public static class declared in {@code type} that implements {@code Builder<T>}
	 */
	public static <T> Builder<T> newBuilderInstance(Class<T> type) {
		Optional<Class<Builder<T>>> builderClass = findPublicStaticBuilderClass(type);
		if (builderClass.isPresent()) {
			return newInstance(builderClass.get());
		} else {
			throw illegalArgument("no public [%s] declared in [%s]", Builder.class.getCanonicalName(), type.getCanonicalName());
		}
	}

	public static boolean declaresPublicStaticBuilderClass(Class<?> type) {
		return findPublicStaticBuilderClass(type).isPresent();
	}

	/**
	 * Examine {@code type} to see if it declares a public static inner class that implements the Builder interface.
	 * 
	 * The assumption implied by the {@code SuppressWarnings} annotation is that any class declaring a public static inner class implementing the {@code Builder} interface will
	 * produce instances of the class it is declared in.
	 */
	@SuppressWarnings("unchecked")
	public static <T> Optional<Class<Builder<T>>> findPublicStaticBuilderClass(Class<T> type) {
		List<Class<?>> declaredClasses = ImmutableList.copyOf(type.getDeclaredClasses());
		for (Class<?> declaredClass : declaredClasses) {
			if (isBuilder(declaredClass) && isPublicStatic(declaredClass)) {
				return Optional.of((Class<Builder<T>>) declaredClass);
			}
		}
		return absent();
	}

	protected static boolean isBuilder(Class<?> type) {
		return Builder.class.isAssignableFrom(type);
	}

	protected static boolean isPublicStatic(Class<?> type) {
		return isPublic(type.getModifiers()) && isStatic(type.getModifiers());
	}

}
