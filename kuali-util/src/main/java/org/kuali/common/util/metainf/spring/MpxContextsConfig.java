package org.kuali.common.util.metainf.spring;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.kuali.common.util.metainf.model.MetaInfContext;
import org.kuali.common.util.metainf.service.MetaInfUtils;
import org.kuali.common.util.project.ProjectUtils;
import org.kuali.common.util.project.model.Project;
import org.kuali.common.util.project.model.ProjectDirectories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ MetaInfCommonConfig.class })
public class MpxContextsConfig implements MetaInfContextsConfig {

	public static final List<String> RECURSIVE_MPX_INCLUDES = Arrays.asList("**/*.mpx");

	@Autowired
	MetaInfCommonConfig metaInfCommonConfig;

	@Autowired
	Project project;

	@Autowired
	ProjectDirectories projectDirectories;

	@Override
	@Bean
	public List<MetaInfContext> metaInfContexts() {
		File buildOutputDir = projectDirectories.getBuildOutput();
		String outputPath = MetaInfUtils.getResourcePrefix(project) + MetaInfCommonConfig.DATA_FILENAME;
		File outputFile = new File(buildOutputDir, outputPath);
		MetaInfContext context = new MetaInfContext(outputFile, ProjectUtils.getEncoding(project), buildOutputDir, RECURSIVE_MPX_INCLUDES);
		return Collections.singletonList(context);
	}

}
