package org.kuali.common.util.metainf.spring;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.metainf.model.*;
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
        List<MetaInfContext> metaInfContexts = new ArrayList<MetaInfContext>();
        List<String> includeStrings = Lists.newArrayList("/initial-xml/", "/upgrades/*/");
        for (String includeString : includeStrings) {
            for (MetaInfDataType type : getTypes()) {
                List<MetaInfContext> contexts = getMetaInfContexts(MetaInfGroup.OTHER, includeString, type);
                metaInfContexts.addAll(contexts);
            }
        }
		return metaInfContexts;
	}

	protected List<MetaInfContext> getMetaInfContexts(MetaInfGroup group, String includeString, MetaInfDataType type) {
		List<MetaInfContext> metaInfContexts = Lists.newArrayList();
		String includesKey = MetaInfConfigUtils.getIncludesKey(group, PREFIX);
		String excludesKey = MetaInfConfigUtils.getExcludesKey(group, PREFIX);
		File scanDir = build.getOutputDir();
		String encoding = build.getEncoding();
		Comparator<MetaInfResource> comparator = getComparator();
        boolean relativePaths = env.getBoolean(RELATIVE_KEY, DEFAULT_GENERATE_RELATIVE_PATHS);
        List<String> qualifiers = MetaInfUtils.getQualifiers(scanDir, project, Lists.<String>newArrayList(includeString), Lists.<String>newArrayList());
        for (String qualifier : qualifiers) {
            File outputFile = MetaInfUtils.getOutputFile(project, build, Optional.of(qualifier), Optional.<MetaInfDataLocation> absent(), Optional.of(type), INGEST_FILENAME);
            Map<MetaInfGroup, String> defaultIncludes = getDefaultIncludes(project, qualifier, type);
            Map<MetaInfGroup, String> defaultExcludes = getDefaultExcludes();
            List<String> includes = SpringUtils.getNoneSensitiveListFromCSV(env, includesKey, defaultIncludes.get(group));
            List<String> excludes = SpringUtils.getNoneSensitiveListFromCSV(env, excludesKey, defaultExcludes.get(group));
            MetaInfContext context = new MetaInfContext.Builder(outputFile, encoding, scanDir).comparator(comparator).includes(includes).excludes(excludes).relativePaths(relativePaths).build();
            metaInfContexts.add(context);
        }
        return metaInfContexts;
	}

    protected List<MetaInfDataType> getTypes() {
        return Lists.newArrayList(MetaInfDataType.BOOTSTRAP, MetaInfDataType.DEMO, MetaInfDataType.TEST);
    }

	protected Comparator<MetaInfResource> getComparator() {
		return new MetaInfResourcePathComparator();
	}

	protected Map<MetaInfGroup, String> getDefaultIncludes(Project project, String qualifier, MetaInfDataType type) {
		String resourcePath = ProjectUtils.getResourcePath(project.getGroupId(), project.getArtifactId());
		Map<MetaInfGroup, String> map = Maps.newHashMap();
        List<String> paths = Lists.newArrayList(resourcePath, qualifier, type.name().toLowerCase(), "**/*.xml");
		map.put(MetaInfGroup.OTHER, StringUtils.join(paths, "/"));
		return map;
	}

	protected Map<MetaInfGroup, String> getDefaultExcludes() {
		Map<MetaInfGroup, String> map = Maps.newHashMap();
		map.put(MetaInfGroup.OTHER, NullUtils.NONE);
		return map;
	}

}
