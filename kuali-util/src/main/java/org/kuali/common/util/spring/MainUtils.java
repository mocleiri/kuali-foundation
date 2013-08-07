package org.kuali.common.util.spring;

import java.util.Collections;
import java.util.Map;

import org.kuali.common.util.Str;
import org.kuali.common.util.spring.service.SpringContext;

public class MainUtils {

	public static final String ARGS_BEAN_NAME = "mainArgs";

	public static void runAndExit(Class<?> mainClass, String[] args) {
		try {
			Map<String, Object> beans = Collections.singletonMap(ARGS_BEAN_NAME, (Object) Str.toEmptyArray(args));
			SpringContext context = new SpringContext(beans, mainClass);
			SpringExecutable exec = new SpringExecutable(context);
			exec.execute();
			System.exit(0);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}
