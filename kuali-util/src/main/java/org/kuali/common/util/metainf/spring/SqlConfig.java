package org.kuali.common.util.metainf.spring;

import java.io.File;
import java.util.ArrayList;
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
public class SqlConfig implements MetaInfContextsConfig {

	private static final boolean DEFAULT_GENERATE_RELATIVE_PATHS = true;
	private static final String RELATIVE_KEY = MetaInfUtils.PROPERTY_PREFIX + ".sql.relative";
	private static final String PROPERTY_PREFIX = MetaInfUtils.FEATURE_ID.getFeatureId();
	private static final String DB_VENDOR_KEY = "db.vendor";

	@Autowired
	Environment env;

	@Autowired
	Project project;

	@Autowired
	Build build;

	@Override
	@Bean
	public List<MetaInfContext> metaInfContexts() {
		List<MetaInfContext> contexts = new ArrayList<MetaInfContext>();
		for (MetaInfGroup group : MetaInfGroup.values()) {
			MetaInfContext context = getMetaInfContext(group);
			contexts.add(context);
		}
		return contexts;
	}

	protected MetaInfContext getMetaInfContext(MetaInfGroup group) {
		String filename = MetaInfUtils.getFilename(group);
		String includesKey = PROPERTY_PREFIX + ".sql." + group.name().toLowerCase() + ".includes";
		String databaseVendor = SpringUtils.getProperty(env, DB_VENDOR_KEY);
		boolean generateRelativePaths = SpringUtils.getBoolean(env, RELATIVE_KEY, DEFAULT_GENERATE_RELATIVE_PATHS);
		List<String> includes = SpringUtils.getNoneSensitiveListFromCSV(env, includesKey);
		String outputPath = MetaInfUtils.getResourcePrefix(project) + "/" + databaseVendor + "/" + filename;
		File outputFile = new File(build.getOutputDir(), outputPath);
		return new MetaInfContext(outputFile, build.getEncoding(), build.getOutputDir(), includes, generateRelativePaths);
	}
}
