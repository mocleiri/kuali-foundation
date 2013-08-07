package org.kuali.common.util.spring.main;

import java.util.Collections;
import java.util.Map;

import org.kuali.common.util.spring.SpringExecutable;
import org.kuali.common.util.spring.service.SpringContext;

public class MainUtils {

	public static final String MAIN_CONTEXT_BEAN_NAME = "mainContext";
	public static final String MAIN_PROFILE_NAME = "main";

	public static void runAndExit(Class<?> mainClass, String[] args) {
		runAndExit(mainClass, args, MainExecutableConfig.class);
	}

	public static void runAndExit(Class<?> mainClass, String[] args, Class<?> config) {
		try {
			MainContext main = new MainContext(mainClass, args);
			Map<String, Object> beans = Collections.singletonMap(MAIN_CONTEXT_BEAN_NAME, (Object) main);
			SpringContext context = new SpringContext(beans, config, MAIN_PROFILE_NAME);
			SpringExecutable exec = new SpringExecutable(context);
			exec.execute();
			System.exit(0);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}
