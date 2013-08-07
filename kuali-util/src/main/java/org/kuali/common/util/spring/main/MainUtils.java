package org.kuali.common.util.spring.main;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.spring.SpringExecutable;
import org.kuali.common.util.spring.service.SpringContext;

public class MainUtils {

	public static final String MAIN_CONTEXT_BEAN_NAME = "mainContext";
	public static final String MAIN_PROFILE_NAME = "main";

	public static void runAndExit(Class<? extends MainConfig> main, String[] args) {
		try {
			MainContext mainContext = new MainContext(main, args);
			Map<String, Object> beans = Collections.singletonMap(MAIN_CONTEXT_BEAN_NAME, (Object) mainContext);
			List<Class<?>> annotatedClasses = CollectionUtils.asList(main, MainExecutableConfig.class);
			SpringContext context = new SpringContext(beans, annotatedClasses, MAIN_PROFILE_NAME);
			SpringExecutable exec = new SpringExecutable(context);
			exec.execute();
			System.exit(0);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

}
