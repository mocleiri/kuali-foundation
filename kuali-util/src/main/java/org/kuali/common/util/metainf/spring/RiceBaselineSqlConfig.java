package org.kuali.common.util.metainf.spring;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.common.util.metainf.model.MetaInfContext;
import org.kuali.common.util.metainf.service.MetaInfUtils;
import org.kuali.common.util.nullify.NullUtils;
import org.kuali.common.util.project.ProjectUtils;
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
public class RiceBaselineSqlConfig implements MetaInfContextsConfig {

	private static final boolean DEFAULT_GENERATE_RELATIVE_PATHS = true;
	private static final String RELATIVE_KEY = MetaInfUtils.PROPERTY_PREFIX + ".sql.relative";
	private static final String PREFIX = "sql";
	private static final String[] VENDORS = { "mysql", "oracle" };
	private static final String IMPEX_ARTIFACT_ID = "rice-impex-master";

	@Autowired
	EnvironmentService env;

	@Autowired
	Project project;

	@Autowired
	Build build;

	@Override
	@Bean
	public List<MetaInfContext> metaInfContexts() {
		List<MetaInfContext> contexts = new ArrayList<MetaInfContext>();
		for (String vendor : VENDORS) {
			Map<MetaInfGroup, String> defaultIncludes = getDefaultIncludes(project, IMPEX_ARTIFACT_ID, vendor);
			Map<MetaInfGroup, String> defaultExcludes = getDefaultExcludes(defaultIncludes);
			for (MetaInfGroup group : MetaInfGroup.values()) {
				MetaInfContext context = getMetaInfContext(group, defaultIncludes, defaultExcludes, vendor);
				contexts.add(context);
			}
		}
		return contexts;
	}

	protected MetaInfContext getMetaInfContext(MetaInfGroup group, Map<MetaInfGroup, String> defaultIncludes, Map<MetaInfGroup, String> defaultExcludes, String vendor) {
		boolean relativePaths = env.getBoolean(RELATIVE_KEY, DEFAULT_GENERATE_RELATIVE_PATHS);
		File outputFile = MetaInfUtils.getOutputFile(project, build, vendor, group);
		String includesKey = MetaInfConfigUtils.getIncludesKey(group, PREFIX) + "." + vendor;
		String excludesKey = MetaInfConfigUtils.getExcludesKey(group, PREFIX) + "." + vendor;
		List<String> includes = SpringUtils.getNoneSensitiveListFromCSV(env, includesKey, defaultIncludes.get(group));
		List<String> excludes = SpringUtils.getNoneSensitiveListFromCSV(env, excludesKey, defaultExcludes.get(group));
		File scanDir = build.getOutputDir();
		String encoding = build.getEncoding();
		return new MetaInfContext.Builder(outputFile, encoding, scanDir).includes(includes).excludes(excludes).relativePaths(relativePaths).build();
	}

	protected Map<MetaInfGroup, String> getDefaultIncludes(Project project, String impexArtifactId, String vendor) {
		String resourcePath = ProjectUtils.getResourcePath(project.getGroupId(), project.getArtifactId());
		Map<MetaInfGroup, String> map = new HashMap<MetaInfGroup, String>();
		map.put(MetaInfGroup.SCHEMA, resourcePath + "/initial-db/sql/" + vendor + "/" + impexArtifactId + ".sql");
		map.put(MetaInfGroup.DATA, resourcePath + "/initial-db/sql/" + vendor + "/*.sql");
		map.put(MetaInfGroup.CONSTRAINTS, resourcePath + "/initial-db/sql/" + vendor + "/" + impexArtifactId + "-constraints.sql");
		map.put(MetaInfGroup.OTHER, resourcePath + "/upgrades/**/" + vendor + "/**/*.sql");
		return map;
	}

	protected Map<MetaInfGroup, String> getDefaultExcludes(Map<MetaInfGroup, String> defaultIncludes) {
		Map<MetaInfGroup, String> map = new HashMap<MetaInfGroup, String>();
		// The schema includes specifies exactly one file, no need to exclude anything
		map.put(MetaInfGroup.SCHEMA, NullUtils.NONE);
		// Include all of the SQL except for the schema + constraints SQL
		map.put(MetaInfGroup.DATA, defaultIncludes.get(MetaInfGroup.SCHEMA) + "," + defaultIncludes.get(MetaInfGroup.CONSTRAINTS));
		// The constraints includes specifies exactly one file, no need to exclude anything
		map.put(MetaInfGroup.CONSTRAINTS, NullUtils.NONE);
		// No need to exclude any of the "upgrades" SQL
		map.put(MetaInfGroup.OTHER, NullUtils.NONE);
		return map;
	}

}
