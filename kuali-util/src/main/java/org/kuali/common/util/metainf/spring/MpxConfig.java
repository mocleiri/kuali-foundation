package org.kuali.common.util.metainf.spring;

import java.io.File;
import java.util.Collections;
import java.util.List;

import org.kuali.common.util.metainf.model.MetaInfContext;
import org.kuali.common.util.metainf.model.ScanContext;
import org.kuali.common.util.metainf.service.MetaInfUtils;
import org.kuali.common.util.project.model.Build;
import org.kuali.common.util.project.model.Project;
import org.kuali.common.util.project.spring.AutowiredProjectConfig;
import org.kuali.common.util.property.Constants;
import org.kuali.common.util.spring.SpringUtils;
import org.kuali.common.util.spring.env.EnvironmentService;
import org.kuali.common.util.spring.service.SpringServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ AutowiredProjectConfig.class, MetaInfExecutableConfig.class, SpringServiceConfig.class })
public class MpxConfig implements MetaInfContextsConfig {

	private static final String PREFIX = "mpx";
	private static final String DEFAULT_INCLUDES = "**/*.mpx";
	private static final String DEFAULT_EXCLUDES = Constants.NONE;
	private static final String INCLUDES_KEY = MetaInfConfigUtils.getIncludesKey(PREFIX);
	private static final String EXCLUDES_KEY = MetaInfConfigUtils.getExcludesKey(PREFIX);

	private static final boolean DEFAULT_GENERATE_RELATIVE_PATHS = true;
	private static final String RELATIVE_KEY = MetaInfUtils.PROPERTY_PREFIX + ".mpx.relative";

	@Autowired
	EnvironmentService env;

	@Autowired
	Project project;

	@Autowired
	Build build;

	@Override
	@Bean
	public List<MetaInfContext> metaInfContexts() {
		boolean relativePathing = env.getBoolean(RELATIVE_KEY, DEFAULT_GENERATE_RELATIVE_PATHS);
		ScanContext scanContext = getScanContext();
		File outputFile = MetaInfUtils.getOutputFile(project, build, MetaInfGroup.DATA);
		MetaInfContext context = new MetaInfContext(outputFile, build.getEncoding(), build.getOutputDir(), scanContext, relativePathing);
		return Collections.singletonList(context);
	}

	protected ScanContext getScanContext() {
		List<String> includes = SpringUtils.getNoneSensitiveListFromCSV(env, INCLUDES_KEY, DEFAULT_INCLUDES);
		List<String> excludes = SpringUtils.getNoneSensitiveListFromCSV(env, EXCLUDES_KEY, DEFAULT_EXCLUDES);
		return new ScanContext(build.getOutputDir(), includes, excludes);
	}
}
