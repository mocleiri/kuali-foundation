package org.kuali.common.util.metainf.service;

import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.execute.HelloWorldExecutable;
import org.kuali.common.util.main.MainContext;
import org.kuali.common.util.main.MainUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SimpleMain {

	public static void main(String[] args) {
		MainUtils.runAndExit(SimpleMain.class, args);
	}

	@Autowired
	MainContext context;

	@Bean(initMethod = "execute")
	public Executable executable() {
		System.out.println(context);
		return new HelloWorldExecutable();
	}
}
