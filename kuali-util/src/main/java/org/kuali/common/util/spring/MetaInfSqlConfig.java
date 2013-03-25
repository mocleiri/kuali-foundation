package org.kuali.common.util.spring;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.MetaInfContext;
import org.kuali.common.util.MetaInfUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class MetaInfSqlConfig {

	@Autowired
	Environment env;

	@Bean
	public Object invokeMethod() {
		List<MetaInfContext> contexts = new ArrayList<MetaInfContext>();
		contexts.add(getMetaInfContext("metainf.output.schema", "metainf.includes.schema"));
		contexts.add(getMetaInfContext("metainf.output.data", "metainf.includes.data"));
		contexts.add(getMetaInfContext("metainf.output.constraints", "metainf.includes.constraints"));
		contexts.add(getMetaInfContext("metainf.output.other", "metainf.includes.other"));

		Class<MetaInfUtils> targetClass = MetaInfUtils.class;
		String targetMethod = "scanAndCreateFiles";
		Object[] arguments = { contexts };
		return SpringUtils.invokeMethod(targetClass, targetMethod, arguments);
	}

	protected MetaInfContext getMetaInfContext(String outputFileKey, String includesKey) {
		String csv = SpringUtils.getProperty(env, includesKey);
		List<String> includes = CollectionUtils.getTrimmedListFromCSV(csv);
		File outputFile = new File(SpringUtils.getProperty(env, outputFileKey));
		File baseDir = new File(SpringUtils.getProperty(env, "project.build.outputDirectory"));

		MetaInfContext context = new MetaInfContext();
		context.setBaseDir(baseDir);
		context.setOutputFile(outputFile);
		context.setIncludes(includes);
		return context;
	}
}
