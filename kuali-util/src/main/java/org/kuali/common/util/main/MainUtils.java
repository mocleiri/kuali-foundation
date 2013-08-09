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
			MainContext mainContext = new MainContext(mainClass, args);
			Map<String, Object> beans = Collections.singletonMap(MAIN_CONTEXT_BEAN_NAME, (Object) mainContext);
			SpringContext context = new SpringContext(beans, mainClass, MAIN_PROFILE_NAME);
			SpringService service = SpringExecutable.DEFAULT_SPRING_SERVICE;
			SpringExecutable exec = new SpringExecutable(service, context);
			exec.execute();
			System.exit(Status.SUCCESS.getValue());
		} catch (Exception e) {
			System.err.print(e.getMessage());
			System.exit(Status.FAILURE.getValue());
		}
	}

}
