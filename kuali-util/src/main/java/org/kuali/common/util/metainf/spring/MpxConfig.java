package org.kuali.common.util.metainf.spring;

import java.io.File;
import java.util.Collections;
import java.util.List;

import org.kuali.common.util.metainf.model.MetaInfContext;
import org.kuali.common.util.metainf.service.MetaInfUtils;
import org.kuali.common.util.project.model.Build;
import org.kuali.common.util.project.model.Project;
import org.kuali.common.util.project.spring.AutowiredProjectConfig;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

@Configuration
@Import({ AutowiredProjectConfig.class, MetaInfExecutableConfig.class })
public class MpxConfig implements MetaInfContextsConfig {

	private static final String DEFAULT_INCLUDES = "**/*.mpx";
	private static final String INCLUDES_KEY = MetaInfUtils.PROPERTY_PREFIX + ".mpx.includes";

	private static final boolean DEFAULT_GENERATE_RELATIVE_PATHS = true;
	private static final String RELATIVE_KEY = MetaInfUtils.PROPERTY_PREFIX + ".mpx.relative";

	@Autowired
	Environment env;

	@Autowired
	Project project;

	@Autowired
	Build build;

	@Override
	@Bean
	public List<MetaInfContext> metaInfContexts() {
		boolean generateRelativePaths = SpringUtils.getBoolean(env, RELATIVE_KEY, DEFAULT_GENERATE_RELATIVE_PATHS);
		String includes = SpringUtils.getProperty(env, INCLUDES_KEY, DEFAULT_INCLUDES);
		File outputFile = MetaInfUtils.getOutputFile(project, build, MetaInfGroup.DATA);
		MetaInfContext context = new MetaInfContext(outputFile, build.getEncoding(), build.getOutputDir(), includes, generateRelativePaths);
		return Collections.singletonList(context);
	}

}
