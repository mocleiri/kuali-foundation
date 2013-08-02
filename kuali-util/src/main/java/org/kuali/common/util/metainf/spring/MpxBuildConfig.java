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
@Import({ MpxCommonConfig.class })
public class MpxBuildConfig {

	@Autowired
	MpxCommonConfig mpxCommonConfig;

	@Autowired
	Project project;

	protected File getResourceBase() {
		File outputDir = ProjectUtils.getBuildOutputDirectory(project);
		String subDirectory = mpxCommonConfig.getResourcePrefix();
		return new File(outputDir, subDirectory);
	}

	@Bean
	public List<MetaInfContext> mpxMetaInfContexts() {
		MetaInfContext context = new MetaInfContext();
		context.setOutputFile(new File(getResourceBase() + MpxCommonConfig.DATA_FILENAME));
		context.setIncludes(MpxCommonConfig.RECURSIVE_MPX_INCLUDES);
		return Arrays.asList(context);
	}

}
