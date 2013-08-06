package org.kuali.common.util.metainf.spring;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.kuali.common.util.metainf.model.MetaInfContext;
import org.kuali.common.util.project.model.Build;
import org.kuali.common.util.project.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ MetaInfCommonConfig.class })
public class MpxContextsConfig implements MetaInfContextsConfig {

	public static final List<String> RECURSIVE_MPX_INCLUDES = Arrays.asList("**/*.mpx");

	@Autowired
	Project project;

	@Autowired
	Build build;

	@Override
	@Bean
	public List<MetaInfContext> metaInfContexts() {
		String outputPath = MetaInfCommonConfig.getResourcePrefix(project) + MetaInfCommonConfig.DATA_FILENAME;
		File outputFile = new File(build.getOutputDir(), outputPath);
		MetaInfContext context = new MetaInfContext(outputFile, build.getEncoding(), build.getOutputDir(), RECURSIVE_MPX_INCLUDES);
		return Collections.singletonList(context);
	}

}
