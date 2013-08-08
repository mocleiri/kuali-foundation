package org.kuali.common.util.spring.main;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

import org.kuali.common.util.spring.SpringExecutable;
import org.kuali.common.util.spring.service.PropertySourceConfig;
import org.kuali.common.util.spring.service.PropertySourceService;
import org.kuali.common.util.spring.service.SpringContext;
import org.springframework.core.env.PropertySource;

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
			SpringExecutable exec = new SpringExecutable(context);
			exec.execute();
			System.exit(Status.SUCCESS.getValue());
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(Status.FAILURE.getValue());
		}
	}

	public static PropertySource<?> getPropertySource(PropertySourceService service, MainContext context, Class<? extends PropertySourceConfig> config) {
		Map<String, Object> beans = Collections.singletonMap(MAIN_CONTEXT_BEAN_NAME, (Object) context);
		return service.getPropertySource(beans, null, Arrays.asList(MAIN_PROFILE_NAME), config);
	}

}
