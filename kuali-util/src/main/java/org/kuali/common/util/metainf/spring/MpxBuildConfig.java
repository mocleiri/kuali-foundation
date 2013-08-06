package org.kuali.common.util.metainf.spring;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.kuali.common.util.metainf.model.MetaInfContext;
import org.kuali.common.util.project.ProjectUtils;
import org.kuali.common.util.project.model.Project;
import org.kuali.common.util.project.model.Build;
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

	@Autowired
	Build projectDirectories;

	@Override
	@Bean
	public List<MetaInfContext> metaInfContexts() {
		File outputFile = new File(getResourceBase() + MetaInfCommonConfig.DATA_FILENAME);
		String encoding = ProjectUtils.getEncoding(project);
		File scanDir = projectDirectories.getOutputDir();
		return Collections.singletonList(new MetaInfContext(outputFile, encoding, scanDir, RECURSIVE_MPX_INCLUDES));
	}

	protected File getResourceBase() {
		File outputDir = ProjectUtils.getBuildOutputDirectory(project);
		String subDirectory = null;// metaInfCommonConfig.getResourcePrefix();
		return new File(outputDir, subDirectory);
	}

	protected File getOutputFile() {
		File dir = getResourceBase();
		return new File(dir, MetaInfCommonConfig.DATA_FILENAME);
	}

}
