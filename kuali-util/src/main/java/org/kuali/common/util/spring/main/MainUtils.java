package org.kuali.common.util.spring.main;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.common.util.Str;
import org.kuali.common.util.spring.SpringExecutable;
import org.kuali.common.util.spring.service.SpringContext;

public class MainUtils {

	public static final String ARGS_BEAN_NAME = "mainArgs";
	public static final String MAIN_CLASS_BEAN_NAME = "mainClass";
	public static final String MAIN_PROFILE_NAME = "main";

	public static void runAndExit(Class<?> mainClass, String[] args) {
		runAndExit(mainClass, args, MainRunnerExecutableConfig.class);
	}

	public static void runAndExit(Class<?> mainClass, String[] args, Class<?> config) {
		try {
			List<String> mainArgs = Arrays.asList(Str.toEmptyArray(args));
			Map<String, Object> beans = new HashMap<String, Object>();
			beans.put(ARGS_BEAN_NAME, mainArgs);
			beans.put(MAIN_CLASS_BEAN_NAME, mainClass);
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
