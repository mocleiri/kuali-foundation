package org.kuali.common.impex.spring;

import java.io.File;
import java.util.List;

import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.MetaInfContext;
import org.kuali.common.util.MetaInfUtils;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class MetaInfMpxConfig {

	@Autowired
	Environment env;

	@Bean
	public Object invokeMethod() {
		// Extract the CSV patterns and convert to a list
		String csv = SpringUtils.getProperty(env, "impex.metainf.includes", "**/*.mpx");
		List<String> includes = CollectionUtils.getTrimmedListFromCSV(csv);

		// Setup some directories
		File baseDir = new File(SpringUtils.getProperty(env, "project.build.outputDirectory"));
		File outputFile = new File(SpringUtils.getProperty(env, "impex.metainf.outputFile"));

		// Setup the context
		MetaInfContext context = new MetaInfContext();
		context.setBaseDir(baseDir);
		context.setOutputFile(outputFile);
		context.setIncludes(includes);

		// Invoke MetaInfUtils to get the desired result
		MethodInvokingFactoryBean mifb = new MethodInvokingFactoryBean();
		mifb.setTargetClass(MetaInfUtils.class);
		mifb.setTargetMethod("scanAndCreateFiles");
		mifb.setArguments(new Object[] { context });
		try {
			return mifb.getObject();
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}
}
