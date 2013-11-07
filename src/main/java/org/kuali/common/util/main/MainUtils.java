package org.kuali.common.util.main;

import java.util.Collections;
import java.util.Map;

import org.kuali.common.util.spring.SpringExecutable;
import org.kuali.common.util.spring.service.SpringContext;
import org.kuali.common.util.spring.service.SpringService;

public class MainUtils {

	public static final String MAIN_CONTEXT_BEAN_NAME = "mainContext";
	public static final String MAIN_PROFILE_NAME = "main";

	/**
	 * Load the @Configuration <code>mainClass</code> using Spring and then terminate the JVM.
	 */
	public static void runAndExit(Class<?> mainClass, String[] args) {
		runAndExit(mainClass, args, false);
	}

	/**
	 * Load the @Configuration <code>mainClass</code> using Spring and then terminate the JVM.
	 */
	public static void runAndExit(Class<?> mainClass, String[] args, boolean stacktrace) {
		try {
			// Preserve the context info from the class where main(String[] args) was invoked
			MainContext mainContext = new MainContext(mainClass, args);

			// Create a map containing the context so we can register it with Spring
			Map<String, Object> beans = Collections.singletonMap(MAIN_CONTEXT_BEAN_NAME, (Object) mainContext);

			// Create a SpringContext using the map and main class, with 1 active profile called "main"
			SpringContext context = new SpringContext(beans, mainClass, MAIN_PROFILE_NAME);

			// DefaultSpringService does what we need
			SpringService service = SpringExecutable.DEFAULT_SPRING_SERVICE;

			// This causes Spring to load the @Configuration annotated main class
			new SpringExecutable(service, context).execute();

			// Exit with zero if there is no exception
			System.exit(Status.SUCCESS.getValue());
		} catch (Exception e) {
			handleException(e, stacktrace);
		}
	}

	protected static void handleException(Exception e, boolean stacktrace) {
		if (stacktrace) {
			e.printStackTrace();
		} else {
			System.err.print(e.getMessage());
		}
		System.exit(Status.FAILURE.getValue());
	}

}
