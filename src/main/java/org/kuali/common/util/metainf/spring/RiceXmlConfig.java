package org.kuali.common.util.metainf.spring;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.kuali.common.util.metainf.model.MetaInfContext;
import org.kuali.common.util.metainf.model.MetaInfResource;
import org.kuali.common.util.metainf.model.MetaInfResourceLocationComparator;
import org.kuali.common.util.metainf.model.MetaInfResourcePathComparator;
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

import com.google.common.collect.Maps;

/**
 * TODO Should not be a class called {@code RiceXmlConfig} down here in kuali-util. Create a rice-util and move this there? Main issue preventing this from living in the rice-xml
 * module itself is that it gets tricky having software used very early in the build lifecycle reside in the same project that makes use of it.
 */
@Configuration
@Import({ AutowiredProjectConfig.class, MetaInfExecutableConfig.class, SpringServiceConfig.class })
public class RiceXmlConfig implements MetaInfContextsConfig {

	private static final boolean DEFAULT_GENERATE_RELATIVE_PATHS = true;
	private static final String RELATIVE_KEY = MetaInfUtils.PROPERTY_PREFIX + ".xml.relative";
	private static final String PREFIX = "xml";

	// This is used in the rice-xml module to help locate the correct .resources file containing the XML to ingest
	public static final String INGEST_FILENAME = "ingest";

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
		MetaInfContext context = getMetaInfContext(MetaInfGroup.OTHER);
		contexts.add(context);
		return contexts;
	}

	protected MetaInfContext getMetaInfContext(MetaInfGroup group) {
		Map<MetaInfGroup, String> defaultIncludes = getDefaultIncludes(project);
		Map<MetaInfGroup, String> defaultExcludes = getDefaultExcludes();
		boolean relativePaths = env.getBoolean(RELATIVE_KEY, DEFAULT_GENERATE_RELATIVE_PATHS);
		File outputFile = MetaInfUtils.getOutputFile(project, build, INGEST_FILENAME);
		String includesKey = MetaInfConfigUtils.getIncludesKey(group, PREFIX);
		String excludesKey = MetaInfConfigUtils.getExcludesKey(group, PREFIX);
		List<String> includes = SpringUtils.getNoneSensitiveListFromCSV(env, includesKey, defaultIncludes.get(group));
		List<String> excludes = SpringUtils.getNoneSensitiveListFromCSV(env, excludesKey, defaultExcludes.get(group));
		File scanDir = build.getOutputDir();
		String encoding = build.getEncoding();
		Comparator<MetaInfResource> comparator = getComparator(group);
		return new MetaInfContext.Builder(outputFile, encoding, scanDir).comparator(comparator).includes(includes).excludes(excludes).relativePaths(relativePaths).build();
	}

	protected Comparator<MetaInfResource> getComparator(MetaInfGroup group) {
		if (MetaInfGroup.OTHER.equals(group)) {
			// The upgrades folder for Rice has a nested directory structure - [bootstrap|demo|test] and also [files...|sub-directories] inside each area
			// The sorting of XML located inside the "upgrades" folder for Rice sorts by the directory structure first, and then by filenames in each directory.
			// All sorting is done lexicographically.
			// Files in any given directory come first, followed by any files in sub-directories.
			return new MetaInfResourcePathComparator();
		} else {
			return new MetaInfResourceLocationComparator();
		}
	}

	protected Map<MetaInfGroup, String> getDefaultIncludes(Project project) {
		String resourcePath = ProjectUtils.getResourcePath(project.getGroupId(), project.getArtifactId());
		Map<MetaInfGroup, String> map = Maps.newHashMap();
		map.put(MetaInfGroup.OTHER, resourcePath + "/upgrades/**/*.xml");
		return map;
	}

	protected Map<MetaInfGroup, String> getDefaultExcludes() {
		Map<MetaInfGroup, String> map = Maps.newHashMap();
		// No need to exclude any of the "upgrades" XML
		map.put(MetaInfGroup.OTHER, NullUtils.NONE);
		return map;
	}

}
