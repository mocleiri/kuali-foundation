package org.kuali.common.impex.spring;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.MetaInfContext;
import org.kuali.common.util.MetaInfUtils;
import org.kuali.common.util.ReflectionUtils;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class MetaInfMpxConfig {

	@Autowired
	Environment env;

	@Bean
	public Object invokeMethod() {
		// Extract the CSV include patterns and convert to a list
		String csv = SpringUtils.getProperty(env, "impex.metainf.includes", "**/*.mpx");
		List<String> includes = CollectionUtils.getTrimmedListFromCSV(csv);

		// This is the base directory to scan
		File baseDir = new File(SpringUtils.getProperty(env, "project.build.outputDirectory"));

		// Output file contains one line of text for each file that gets located
		// Each line is an entry similar to this "classpath:MYCONTENT.mpx"
		File outputFile = new File(SpringUtils.getProperty(env, "impex.metainf.outputFile"));

		// Setup the context
		MetaInfContext context = new MetaInfContext();
		context.setBaseDir(baseDir);
		context.setOutputFile(outputFile);
		context.setIncludes(includes);

		// Make a list of one
		List<MetaInfContext> contexts = Arrays.asList(context);

		// Setup the reflection config
		Class<MetaInfUtils> targetClass = MetaInfUtils.class;
		String targetMethod = "scanAndCreateFiles";
		Object[] arguments = { contexts };

		return ReflectionUtils.invokeMethod(targetClass, targetMethod, arguments);
	}
}
