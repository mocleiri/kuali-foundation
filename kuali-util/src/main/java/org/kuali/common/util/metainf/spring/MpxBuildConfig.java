package org.kuali.common.util.metainf.spring;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.kuali.common.util.metainf.MetaInfContext;
import org.kuali.common.util.project.Project;
import org.kuali.common.util.project.ProjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ MetaInfCommonConfig.class })
public class MpxBuildConfig implements MetaInfContextsConfig {

	public static final List<String> RECURSIVE_MPX_INCLUDES = Arrays.asList("**/*.mpx");

	@Autowired
	MetaInfCommonConfig metaInfCommonConfig;

	@Autowired
	Project project;

	@Override
	@Bean
	public List<MetaInfContext> metaInfContexts() {
		MetaInfContext context = new MetaInfContext();
		context.setOutputFile(new File(getResourceBase() + MetaInfCommonConfig.DATA_FILENAME));
		context.setIncludes(RECURSIVE_MPX_INCLUDES);
		return Arrays.asList(context);
	}

	protected File getResourceBase() {
		File outputDir = ProjectUtils.getBuildOutputDirectory(project);
		String subDirectory = metaInfCommonConfig.getResourcePrefix();
		return new File(outputDir, subDirectory);
	}

	protected File getOutputFile() {
		File dir = getResourceBase();
		return new File(dir, MetaInfCommonConfig.DATA_FILENAME);
	}

}
