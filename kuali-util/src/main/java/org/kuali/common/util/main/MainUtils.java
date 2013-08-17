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
		try {
			// Preserve the context info for the class where main(String[] args) was invoked
			MainContext mainContext = new MainContext(mainClass, args);

			// Create beans for the Spring context
			Map<String, Object> beans = Collections.singletonMap(MAIN_CONTEXT_BEAN_NAME, (Object) mainContext);

			// Create a SpringContext with the beans and an active profile called "main"
			SpringContext context = new SpringContext(beans, mainClass, MAIN_PROFILE_NAME);

			// DefaultSpringService will do what we need it to do
			SpringService service = SpringExecutable.DEFAULT_SPRING_SERVICE;

			// SpringExecutable will load the @Configuration annotated main class
			new SpringExecutable(service, context).execute();

			// As long as no exception was thrown we assume everything finished ok
			System.exit(Status.SUCCESS.getValue());
		} catch (Exception e) {
			// Otherwise print the message from the exception and return a non-zero exit code
			System.err.print(e.getMessage());
			System.exit(Status.FAILURE.getValue());
		}
	}

}
