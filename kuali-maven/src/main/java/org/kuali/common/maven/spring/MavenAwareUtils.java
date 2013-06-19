/**
 * Copyright 2004-2013 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.common.maven.spring;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.maven.project.MavenProject;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.Dependency;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.RepositoryUtils;
import org.kuali.common.util.property.Constants;

/**
 * Maven related utilities that are aware of the Maven model objects eg MavenProject
 */
public class MavenAwareUtils {

	public static Properties getInternalProperties(MavenProject project) {
		Properties properties = new Properties();
		nullSafeSet(properties, "project.id", project.getId());
		nullSafeSet(properties, "project.groupId", project.getGroupId());
		nullSafeSet(properties, "project.artifactId", project.getArtifactId());
		nullSafeSet(properties, "project.version", project.getVersion());
		nullSafeSet(properties, "project.packaging", project.getPackaging());
		nullSafeSet(properties, "project.name", project.getName());
		nullSafeSet(properties, "project.description", project.getDescription());
		nullSafeSet(properties, "project.inceptionYear", project.getInceptionYear());
		nullSafeSet(properties, "project.basedir", LocationUtils.getCanonicalPath(project.getBasedir()));
		if (project.getCiManagement() != null) {
			nullSafeSet(properties, "project.ciManagement.system", project.getCiManagement().getSystem());
			nullSafeSet(properties, "project.ciManagement.url", project.getCiManagement().getUrl());
		}
		if (project.getIssueManagement() != null) {
			nullSafeSet(properties, "project.issueManagement.system", project.getIssueManagement().getSystem());
			nullSafeSet(properties, "project.issueManagement.url", project.getIssueManagement().getUrl());
		}
		if (project.getBuild() != null) {
			nullSafeSet(properties, "project.build.directory", project.getBuild().getDirectory());
			nullSafeSet(properties, "project.build.outputDirectory", project.getBuild().getOutputDirectory());
			nullSafeSet(properties, "project.build.testOutputDirectory", project.getBuild().getTestOutputDirectory());
			nullSafeSet(properties, "project.build.sourceDirectory", project.getBuild().getSourceDirectory());
			nullSafeSet(properties, "project.build.scriptSourceDirectory", project.getBuild().getScriptSourceDirectory());
			nullSafeSet(properties, "project.build.testSourceDirectory", project.getBuild().getTestSourceDirectory());
		}
		if (project.getScm() != null) {
			nullSafeSet(properties, "project.scm.connection", project.getScm().getConnection());
			nullSafeSet(properties, "project.scm.developerConnection", project.getScm().getDeveloperConnection());
			nullSafeSet(properties, "project.scm.url", project.getScm().getDeveloperConnection());
		}
		nullSafeSet(properties, "project.pom.location", getPomLocation(project));
		if (project.getDependencies() != null) {
			List<Dependency> pojos = convertToSimplePojos(project.getDependencies());
			if (pojos.size() == 0) {
				nullSafeSet(properties, "project.dependencies", Constants.NONE);
			} else {
				nullSafeSet(properties, "project.dependencies", getDependenciesCSV(pojos));
			}
		} else {
			nullSafeSet(properties, "project.dependencies", Constants.NONE);
		}
		return properties;
	}

	/**
	 * Don't call setProperty() if value is null
	 */
	public static void nullSafeSet(Properties properties, String key, String value) {
		if (value != null) {
			properties.setProperty(key, value);
		}
	}

	// Maven automatically stores the pom to this location
	public static String getPomLocation(MavenProject project) {
		StringBuilder sb = new StringBuilder();
		sb.append("classpath:");
		sb.append("META-INF");
		sb.append("/");
		sb.append("maven");
		sb.append("/");
		sb.append(project.getGroupId());
		sb.append("/");
		sb.append(project.getArtifactId());
		sb.append("/");
		sb.append("pom.xml");
		return sb.toString();
	}

	/**
	 * Convert the formal Maven dependency objects into vanilla pojo objects
	 */
	public static List<Dependency> convertToSimplePojos(List<org.apache.maven.model.Dependency> dependencies) {
		List<Dependency> pojos = new ArrayList<Dependency>();
		for (org.apache.maven.model.Dependency d : dependencies) {
			Dependency pojo = new Dependency();
			pojo.setGroupId(d.getGroupId());
			pojo.setArtifactId(d.getArtifactId());
			pojo.setVersion(d.getVersion());
			pojo.setClassifier(d.getClassifier());
			pojo.setType(d.getType());
			pojo.setScope(d.getScope());
			pojos.add(pojo);
		}
		return pojos;

	}

	/**
	 * Convert the list of dependencies into a CSV string
	 */
	public static String getDependenciesCSV(List<Dependency> dependencies) {
		if (CollectionUtils.isEmpty(dependencies)) {
			return Constants.NONE;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < dependencies.size(); i++) {
			if (i != 0) {
				sb.append(",");
			}
			Dependency dependency = dependencies.get(i);
			sb.append(RepositoryUtils.toString(dependency));
		}
		return sb.toString();
	}
}
