package org.kuali.common.util.metainf.spring;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.common.util.metainf.model.MetaInfContext;
import org.kuali.common.util.metainf.service.MetaInfUtils;
import org.kuali.common.util.nullify.NullUtils;
import org.kuali.common.util.project.model.Build;
import org.kuali.common.util.project.model.Project;
import org.kuali.common.util.project.spring.AutowiredProjectConfig;
import org.kuali.common.util.spring.SpringUtils;
import org.kuali.common.util.spring.env.EnvironmentService;
import org.kuali.common.util.spring.service.SpringServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ AutowiredProjectConfig.class, MetaInfExecutableConfig.class, SpringServiceConfig.class })
public class SqlConfig implements MetaInfContextsConfig {

	private static final boolean DEFAULT_GENERATE_RELATIVE_PATHS = true;
	private static final String RELATIVE_KEY = MetaInfUtils.PROPERTY_PREFIX + ".sql.relative";
	private static final String DB_VENDOR_KEY = "db.vendor";
	private static final String PREFIX = "sql";

	@Autowired
	EnvironmentService env;

	@Autowired
	Project project;

	@Autowired
	Build build;

	@Override
	@Bean
	public List<MetaInfContext> metaInfContexts() {
		Map<MetaInfGroup, String> defaultIncludes = getDefaultIncludes();
		List<MetaInfContext> contexts = new ArrayList<MetaInfContext>();
		for (MetaInfGroup group : MetaInfGroup.values()) {
			MetaInfContext context = getMetaInfContext(group, defaultIncludes);
			contexts.add(context);
		}
		return contexts;
	}

	protected MetaInfContext getMetaInfContext(MetaInfGroup group, Map<MetaInfGroup, String> defaultIncludes) {
		String databaseVendor = env.getString(DB_VENDOR_KEY);
		boolean relativePaths = env.getBoolean(RELATIVE_KEY, DEFAULT_GENERATE_RELATIVE_PATHS);
		File outputFile = MetaInfUtils.getOutputFile(project, build, databaseVendor, group);
		String includesKey = MetaInfConfigUtils.getIncludesKey(group, PREFIX);
		String excludesKey = MetaInfConfigUtils.getExcludesKey(group, PREFIX);
		List<String> includes = SpringUtils.getNoneSensitiveListFromCSV(env, includesKey, defaultIncludes.get(group));
		List<String> excludes = SpringUtils.getNoneSensitiveListFromCSV(env, excludesKey, NullUtils.NONE);
		File scanDir = build.getOutputDir();
		String encoding = build.getEncoding();
		return new MetaInfContext.Builder(outputFile, encoding, scanDir).includes(includes).excludes(excludes).relativePaths(relativePaths).build();
	}

	protected Map<MetaInfGroup, String> getDefaultIncludes() {
		Map<MetaInfGroup, String> map = new HashMap<MetaInfGroup, String>();
		map.put(MetaInfGroup.SCHEMA, "**/initial-db/**/*create-schema.sql");
		map.put(MetaInfGroup.DATA, "**/initial-db/**/data/*.sql");
		map.put(MetaInfGroup.CONSTRAINTS, "**/initial-db/**/*constraints.sql");
		map.put(MetaInfGroup.OTHER, "**/upgrades/**/*.sql");
		return map;
	}
}
